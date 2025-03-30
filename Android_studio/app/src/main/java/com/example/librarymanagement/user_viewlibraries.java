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

public class user_viewlibraries extends ListeningActivity {

    Button b1;
    SharedPreferences sh;
    EditText e1;
    ListView li;
    String lname,url;
    ArrayList<String>lib,im,na,pl,ph,logid,loc;

    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_viewlibraries);

        b1=findViewById(R.id.uvl1);
        e1=findViewById(R.id.editTextTextPersonName30);
        li=findViewById(R.id.ul2);
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
                Toast.makeText(context, "Speak ", Toast.LENGTH_SHORT).show();
            }
        });

        url ="http://"+sh.getString("ip", "") + ":5000/user_vlibraries";
        RequestQueue queue = Volley.newRequestQueue(user_viewlibraries.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(user_viewlibraries.this, "jjj"+response, Toast.LENGTH_SHORT).show();
                    lib =new ArrayList<>();
                    im= new ArrayList<>();
                    na= new ArrayList<>();
                    pl= new ArrayList<>();
                    ph= new ArrayList<>();
                    logid= new ArrayList<>();
                    loc= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        lib.add(jo.getString("libid"));
                        im.add(jo.getString("image"));
                        na.add(jo.getString("name"));
                        pl.add(jo.getString("place"));
                        ph.add(jo.getString("phoneno"));
                        logid.add(jo.getString("logid"));
                        loc.add(jo.getString("loc"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    li.setAdapter(new cutsom_lib(user_viewlibraries.this,na,im,pl,ph,lib,logid,loc));
//                    l1.setOnItemClickListener(user_viewlibraries.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(user_viewlibraries.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                return params;
            }
        };
        queue.add(stringRequest);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lname=e1.getText().toString();
                if(lname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter Text");
                }
                else {
                    url = "http://" + sh.getString("ip", "") + ":5000/user_libsearch";
                    RequestQueue queue = Volley.newRequestQueue(user_viewlibraries.this);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {

                                JSONArray ar = new JSONArray(response);
                                im = new ArrayList<>();
                                na = new ArrayList<>();
                                pl = new ArrayList<>();
                                ph = new ArrayList<>();
                                logid = new ArrayList<>();
                                loc= new ArrayList<>();


                                for(int i=0;i<ar.length();i++)
                                {
                                    JSONObject jo=ar.getJSONObject(i);
                                    lib.add(jo.getString("libid"));
                                    im.add(jo.getString("image"));
                                    na.add(jo.getString("name"));
                                    pl.add(jo.getString("place"));
                                    ph.add(jo.getString("phoneno"));
                                    logid.add(jo.getString("logid"));
                                    loc.add(jo.getString("loc"));


                                }

                                // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                                //lv.setAdapter(ad);

                                li.setAdapter(new cutsom_lib(user_viewlibraries.this, na, im, pl, ph, lib, logid,loc));
//                    l1.setOnItemClickListener(user_viewlibraries.this);

                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                            }


                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(user_viewlibraries.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("lname", lname);

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
        Intent ii =new Intent(getApplicationContext(),user_home.class);
        startActivity(ii);

    }
}