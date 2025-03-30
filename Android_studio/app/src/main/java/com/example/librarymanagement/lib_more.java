package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class lib_more extends ListeningActivity {

    SharedPreferences sh;
    ListView li;
    String url;
    ArrayList<String> bn,bimg,dtls,stk,auth,rvw,cat,ty,bkid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_more);

        li=findViewById(R.id.gi);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url ="http://"+sh.getString("ip", "") + ":5000/lib_more";
        RequestQueue queue = Volley.newRequestQueue(lib_more.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
//                    Toast.makeText(lib_more.this, response+"", Toa`st.LENGTH_SHORT).show();

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

                    li.setAdapter(new custom_lib_more(lib_more.this,bimg,bn,dtls,stk,auth,rvw,cat,ty,bkid));
//                    l1.setOnItemClickListener(lib_more.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(lib_more.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("bkid",getIntent().getStringExtra("bid"));
                return params;
            }
        };
        queue.add(stringRequest);


    }

    @Override
    public void processVoiceCommands(String... voiceCommands) {

    }

    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),libbooks.class);
        startActivity(ii);

    }

}