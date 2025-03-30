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

public class donation_more extends ListeningActivity {

    SharedPreferences sh;
    ListView li;
    String url;
    ArrayList<String>un,bn,im,da,st,de,au,re,ca,status,did;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_more);

        li=findViewById(R.id.dl);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor edp = sh.edit();
        edp.putString("did", getIntent().getStringExtra("did"));
        edp.commit();


        url ="http://"+sh.getString("ip", "") + ":5000/view_donatn_more";
        RequestQueue queue = Volley.newRequestQueue(donation_more.this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
//                    Toast.makeText(lib_more.this, response+"", Toa`st.LENGTH_SHORT).show();

                    JSONArray ar=new JSONArray(response);
                    un= new ArrayList<>();
                    bn= new ArrayList<>();
                    im=new ArrayList<>();
                    da=new ArrayList<>();
                    st=new ArrayList<>();
                    de=new ArrayList<>();
                    au=new ArrayList<>();
                    re=new ArrayList<>();
                    ca=new ArrayList<>();
                    status=new ArrayList<>();
                    did=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        un.add(jo.getString("userf")+""+jo.getString("userl"));
                        bn.add(jo.getString("bookname"));
                        im.add(jo.getString("image"));
                        da.add(jo.getString("date"));
                        st.add(jo.getString("stock"));
                        de.add(jo.getString("details"));
                        au.add(jo.getString("author"));
                        re.add(jo.getString("review"));
                        ca.add(jo.getString("category"));
                        status.add(jo.getString("status"));
                        did.add(jo.getString("id"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    li.setAdapter(new custom_donations_more(donation_more.this,im,un,bn,da,st,de,au,re,ca,status,did));
//                    l1.setOnItemClickListener(lib_more.this);

                } catch (Exception e) {
                    Toast.makeText(donation_more.this, "----"+e, Toast.LENGTH_SHORT).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(donation_more.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("did",getIntent().getStringExtra("did"));
                params.put("lid", sh.getString("lid", ""));
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
        Intent ii =new Intent(getApplicationContext(),donations.class);
        startActivity(ii);

    }


}


