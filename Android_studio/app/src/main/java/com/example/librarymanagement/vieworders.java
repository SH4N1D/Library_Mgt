package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class vieworders extends ListeningActivity {

    Button b1;
    SharedPreferences sh;
    EditText e1;
    ListView li;
    String bkname,url;
    ArrayList<String>bo,im,un,au,st,ca,oid;

    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworders);

        b1=findViewById(R.id.vibt1);
        e1=findViewById(R.id.editTextTextPersonName17);

        li=findViewById(R.id.list11);
        context = getApplicationContext();
        try {
            VoiceRecognitionListener.getInstance().setListener(this);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage() + "++++++++++++", Toast.LENGTH_LONG).show();
        }
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e1";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Speak", Toast.LENGTH_SHORT).show();
            }
        });

        url ="http://"+sh.getString("ip", "") + ":5000/view_order";
        RequestQueue queue = Volley.newRequestQueue(vieworders.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    bo= new ArrayList<>();
                    im= new ArrayList<>();
                    un= new ArrayList<>();
                    au=new ArrayList<>();
                    st=new ArrayList<>();
                    ca=new ArrayList<>();
                    oid=new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);

                        bo.add(jo.getString("bookname"));
                        im.add(jo.getString("image"));
                        un.add(jo.getString("userf")+""+jo.getString("userl"));
                        au.add(jo.getString("author"));
                        st.add(jo.getString("stock"));
                        ca.add(jo.getString("category"));
                        oid.add(jo.getString("oid"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    li.setAdapter(new custom_vieworders(vieworders.this,im,bo,un,au,st,ca,oid));
//                    l1.setOnItemClickListener(vieworders.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(vieworders.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid",sh.getString("lid",""));

                return params;
            }
        };
        queue.add(stringRequest);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                uname=e1.getText().toString();
                bkname = e1.getText().toString();
                if (bkname.equalsIgnoreCase("")) {
                    e1.setError("Enter Text");
                } else {
                    url = "http://" + sh.getString("ip", "") + ":5000/view_order_search";
                    RequestQueue queue = Volley.newRequestQueue(vieworders.this);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {

                                JSONArray ar = new JSONArray(response);
                                bo = new ArrayList<>();
                                im = new ArrayList<>();
                                un = new ArrayList<>();
                                au = new ArrayList<>();
                                st = new ArrayList<>();
                                ca = new ArrayList<>();
                                oid = new ArrayList<>();


                                for (int i = 0; i < ar.length(); i++) {
                                    JSONObject jo = ar.getJSONObject(i);

                                    bo.add(jo.getString("bookname"));
                                    im.add(jo.getString("image"));
                                    un.add(jo.getString("userf") + "" + jo.getString("userl"));
                                    au.add(jo.getString("author"));
                                    st.add(jo.getString("stock"));
                                    ca.add(jo.getString("category"));
                                    oid.add(jo.getString("oid"));

                                }
                                // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                                //lv.setAdapter(ad);
                                li.setAdapter(new custom_vieworders(vieworders.this, im, bo, un, au, st, ca, oid));
//                    l1.setOnItemClickListener(vieworders.this);
                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(vieworders.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("lid", sh.getString("lid", ""));
                            params.put("bname", bkname);

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }
            }
        });

    }

    @Override
    public void processVoiceCommands(String... voiceCommands) {
        String  text = voiceCommands[0];
        if(selected.equalsIgnoreCase("e1"))
        {
            e1.setText(text);
        }
    }

    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),libhome.class);
        startActivity(ii);

    }
}