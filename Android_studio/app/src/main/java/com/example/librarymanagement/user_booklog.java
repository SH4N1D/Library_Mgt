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

public class user_booklog extends ListeningActivity {

    Button b1;
    SharedPreferences sh;
    EditText e1;
    ListView li;
    String bname,url;
    ArrayList<String>bo,im, de, au, re, ca, or, du;

    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booklog);

        b1 = findViewById(R.id.bub1);
        e1 = findViewById(R.id.editTextTextPersonName35);
        li = findViewById(R.id.ul5);
        context = getApplicationContext();
        try {
            VoiceRecognitionListener.getInstance().setListener(this);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage() + "++++++++++++", Toast.LENGTH_LONG).show();
        }
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

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

        url = "http://" + sh.getString("ip", "") + ":5000/user_vbooklog";
        RequestQueue queue = Volley.newRequestQueue(user_booklog.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);

                    bo = new ArrayList<>();
                    im = new ArrayList<>();
                    de = new ArrayList<>();
                    au = new ArrayList<>();
                    re = new ArrayList<>();
                    ca = new ArrayList<>();
                    or = new ArrayList<>();
                    du = new ArrayList<>();


                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);

                        bo.add(jo.getString("bookname"));
                        im.add(jo.getString("image"));
                        de.add(jo.getString("details"));
                        au.add(jo.getString("author"));
                        re.add(jo.getString("review"));
                        ca.add(jo.getString("category"));
                        or.add(jo.getString("orderdat"));
                        du.add(jo.getString("duedat"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);
                    li.setAdapter(new custom_user_booklog(user_booklog.this, im, bo, au, or, du));
//                    l1.setOnItemClickListener(user_booklog.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(user_booklog.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid", ""));
                params.put("libid", sh.getString("libid", ""));
                return params;
            }
        };
        queue.add(stringRequest);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bname = e1.getText().toString();
                if(bname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter Text");
                }
                else {
                    url = "http://" + sh.getString("ip", "") + ":5000/user_vbooklog_search";
                    RequestQueue queue = Volley.newRequestQueue(user_booklog.this);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {

                                JSONArray ar = new JSONArray(response);
                                bo = new ArrayList<>();
                                im = new ArrayList<>();
                                de = new ArrayList<>();
                                au = new ArrayList<>();
                                re = new ArrayList<>();
                                ca = new ArrayList<>();
                                or = new ArrayList<>();
                                du = new ArrayList<>();


                                for (int i = 0; i < ar.length(); i++) {
                                    JSONObject jo = ar.getJSONObject(i);

                                    bo.add(jo.getString("bookname"));
                                    im.add(jo.getString("image"));
                                    de.add(jo.getString("details"));
                                    au.add(jo.getString("author"));
                                    re.add(jo.getString("review"));
                                    ca.add(jo.getString("category"));
                                    or.add(jo.getString("orderdat"));
                                    du.add(jo.getString("duedat"));

                                }

                                // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                                //lv.setAdapter(ad);

                                li.setAdapter(new custom_user_booklog(user_booklog.this, im, bo, au, or, du));
//                    l1.setOnItemClickListener(user_booklog.this);

                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                            }


                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(user_booklog.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("lid", sh.getString("lid", ""));
                            params.put("libid", sh.getString("libid", ""));
                            params.put("bnamo", bname);
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
        Intent ii =new Intent(getApplicationContext(),user_libraryhome.class);
        startActivity(ii);

    }
}