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

public class user_more extends ListeningActivity {


    SharedPreferences sh;
    ListView li;
    String url;
    ArrayList<String>bn,bimg,dtls,stk,auth,rvw,cat,bkid,ty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_more);


        li=findViewById(R.id.hi);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor edp = sh.edit();
        edp.putString("bkid", getIntent().getStringExtra("bkid"));
        edp.commit();


        url ="http://"+sh.getString("ip", "") + ":5000/user_more";
        RequestQueue queue = Volley.newRequestQueue(user_more.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

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

                    li.setAdapter(new custom_user_more(user_more.this,bimg,bn,dtls,stk,auth,rvw,cat,ty));
//                    l1.setOnItemClickListener(chats.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(user_more.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("bkid",sh.getString("bkid",""));
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
        Intent ii =new Intent(getApplicationContext(),user_librarybooks.class);
        startActivity(ii);

    }
}