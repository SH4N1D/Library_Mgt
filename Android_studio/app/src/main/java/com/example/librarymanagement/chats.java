package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class chats extends AppCompatActivity {

    Button b1;
    SharedPreferences sh;
    EditText e1;
    ListView li;
    String name,url;
    ArrayList<String>pr,na,uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        b1=findViewById(R.id.f1);
        e1=findViewById(R.id.f3);
        li=findViewById(R.id.f2);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url ="http://"+sh.getString("ip", "") + ":5000/view_chat";
        RequestQueue queue = Volley.newRequestQueue(chats.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    pr= new ArrayList<>();
                    na= new ArrayList<>();
                    uid=new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        pr.add(jo.getString("pr"));
                        na.add(jo.getString("fn")+" "+jo.getString("ln"));
                        uid.add(jo.getString("uid"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    li.setAdapter(new custom_chats(chats.this,pr,na,uid));
//                    l1.setOnItemClickListener(chats.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(chats.this, "err"+error, Toast.LENGTH_SHORT).show();
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

              name=e1.getText().toString();
              if(name.equalsIgnoreCase(""))
              {
                  e1.setError("Enter Name");
              }
              else {
                  url = "http://" + sh.getString("ip", "") + ":5000/view_chat_search";
                  RequestQueue queue = Volley.newRequestQueue(chats.this);

                  StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {
                          // Display the response string.
                          Log.d("+++++++++++++++++", response);
                          try {

                              JSONArray ar = new JSONArray(response);
                              pr = new ArrayList<>();
                              na = new ArrayList<>();
                              uid = new ArrayList<>();


                              for (int i = 0; i < ar.length(); i++) {
                                  JSONObject jo = ar.getJSONObject(i);
                                  pr.add(jo.getString("pr"));
                                  na.add(jo.getString("fn") + " " + jo.getString("ln"));
                                  uid.add(jo.getString("uid"));

                              }

                              li.setAdapter(new custom_chats(chats.this, pr, na, uid));
                              //lv.setAdapter(ad);

//                    l1.setAdapter(new Custom(chats.this,name,place));
//                    l1.setOnItemClickListener(chats.this);

                          } catch (Exception e) {
                              Log.d("=========", e.toString());
                          }


                      }

                  }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {

                          Toast.makeText(chats.this, "err" + error, Toast.LENGTH_SHORT).show();
                      }
                  }) {
                      @Override
                      protected Map<String, String> getParams() {
                          Map<String, String> params = new HashMap<>();
                          params.put("name", name);
                          return params;
                      }
                  };
                  queue.add(stringRequest);
              }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),libhome.class);
        startActivity(ii);

    }

}