package com.example.librarymanagement;

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

import androidx.appcompat.app.AppCompatActivity;

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

public class placeorder_new extends ListeningActivity {

    Button b1;
    SharedPreferences sh;
    EditText e1;
    ListView li;
    String name,url,bid;
    ArrayList<String>pr,na,uid;

    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder_new);
        b1=findViewById(R.id.f1);
        e1=findViewById(R.id.f3);
        li=findViewById(R.id.f2);
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

        bid=getIntent().getStringExtra("bid");
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("biiiiid", bid);
        edp.commit();


        url ="http://"+sh.getString("ip", "") + ":5000/placeorder_new";
        RequestQueue queue = Volley.newRequestQueue(placeorder_new.this);

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

                    li.setAdapter(new custom_placeorder_new(placeorder_new.this,pr,na,uid));
//                    l1.setOnItemClickListener(chats.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(placeorder_new.this, "err"+error, Toast.LENGTH_SHORT).show();
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
                  url = "http://" + sh.getString("ip", "") + ":5000/placeorder_new_search";
                  RequestQueue queue = Volley.newRequestQueue(placeorder_new.this);

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

                              li.setAdapter(new custom_placeorder_new(placeorder_new.this, pr, na, uid));
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

                          Toast.makeText(placeorder_new.this, "err" + error, Toast.LENGTH_SHORT).show();
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
    public void processVoiceCommands(String... voiceCommands) {

        String  text = voiceCommands[0];
        if(selected.equalsIgnoreCase("e1"))
        {
            e1.setText(text);
        }
    }

    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),libbooks.class);
        startActivity(ii);

    }
}