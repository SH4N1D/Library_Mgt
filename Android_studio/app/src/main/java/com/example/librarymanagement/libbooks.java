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

public class libbooks extends ListeningActivity {

    Button b1,b2;
    SharedPreferences sh;
    EditText e1;
    ListView li;
    String bname,url,url1;
    ArrayList<String> bn,bimg,dtls,stk,auth,rvw,cat,ty,bkid;


    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libbooks);
        b1=findViewById(R.id.libt1);
        b2=findViewById(R.id.button5);
        e1=findViewById(R.id.editTextTextPersonName14);

        li=findViewById(R.id.list6);
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
                Toast.makeText(context, "Please say the Name", Toast.LENGTH_SHORT).show();
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addbook.class);
                startActivity(i);
            }
        });



        url ="http://"+sh.getString("ip", "") + ":5000/view_book";
        RequestQueue queue = Volley.newRequestQueue(libbooks.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
//                    Toast.makeText(libbooks.this, response+"", Toa`st.LENGTH_SHORT).show();

                    JSONArray ar=new JSONArray(response);
                    bn= new ArrayList<>();
                    bimg= new ArrayList<>();
                    dtls= new ArrayList<>();
                    stk=new ArrayList<>();
                    auth=new ArrayList<>();
                    rvw=new ArrayList<>();
                    cat=new ArrayList<>();
                    ty=new ArrayList<>();
                    bkid=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        bn.add(jo.getString("bookname"));
                        bimg.add(jo.getString("image"));
                        dtls.add(jo.getString("details"));
                        stk.add(jo.getString("stock"));
                        auth.add(jo.getString("author"));
                        rvw.add(jo.getString("review"));
                        cat.add(jo.getString("category"));
                        ty.add(jo.getString("type"));
                        bkid.add(jo.getString("bkid"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    li.setAdapter(new custom_libbooks_new(libbooks.this,bimg,bn,auth,cat,ty,bkid));
//                    l1.setOnItemClickListener(libbooks.this);

                } catch (Exception e) {
                    Toast.makeText(libbooks.this, "-----"+e, Toast.LENGTH_SHORT).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(libbooks.this, "err"+error, Toast.LENGTH_SHORT).show();
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

                bname=e1.getText().toString();
                if(bname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter Text");
                }
                else {
                    url1 = "http://" + sh.getString("ip", "") + ":5000/view_book_search";
                    RequestQueue queue = Volley.newRequestQueue(libbooks.this);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {

                                JSONArray ar = new JSONArray(response);
                                bn = new ArrayList<>();
                                bimg = new ArrayList<>();
                                dtls = new ArrayList<>();
                                stk = new ArrayList<>();
                                auth = new ArrayList<>();
                                rvw = new ArrayList<>();
                                cat = new ArrayList<>();
                                ty = new ArrayList<>();
                                bkid = new ArrayList<>();

                                for (int i = 0; i < ar.length(); i++) {
                                    JSONObject jo = ar.getJSONObject(i);
                                    bn.add(jo.getString("bookname"));
                                    bimg.add(jo.getString("image"));
                                    dtls.add(jo.getString("details"));
                                    stk.add(jo.getString("stock"));
                                    auth.add(jo.getString("author"));
                                    rvw.add(jo.getString("review"));
                                    cat.add(jo.getString("category"));
                                    ty.add(jo.getString("type"));
                                    bkid.add(jo.getString("bkid"));


                                }

                                // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                                //lv.setAdapter(ad);

                                li.setAdapter(new custom_libbooks_new(libbooks.this, bimg, bn, auth, cat, ty, bkid));
//                    l1.setOnItemClickListener(libbooks.this);

                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                            }


                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(libbooks.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("bname", bname);
//                        params.put("aname",author);
                            params.put("lid", sh.getString("lid", ""));


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