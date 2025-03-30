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

public class user_librarybooks extends ListeningActivity {


    Button b1;
    SharedPreferences sh;
    EditText e1;
    ListView li;
    String bname,url;
    ArrayList<String>bn,bimg,dtls,stk,auth,rvw,cat,bkid,ty;

    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_librarybooks);

        b1=findViewById(R.id.bull1);
        e1=findViewById(R.id.editTextTextPersonName32);

        li=findViewById(R.id.ul8);
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
                Toast.makeText(context, "Please say ", Toast.LENGTH_SHORT).show();
            }
        });

        url ="http://"+sh.getString("ip", "") + ":5000/user_vlibbooks";
        RequestQueue queue = Volley.newRequestQueue(user_librarybooks.this);

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
                    bkid=new ArrayList<>();
                    ty=new ArrayList<>();
//

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
                        bkid.add(jo.getString("bkid"));
                        ty.add(jo.getString("type"));
//


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    li.setAdapter(new custom_user_librarybooks_new(user_librarybooks.this,bimg,bn,auth,cat,ty,bkid));
//                    l1.setOnItemClickListener(user_librarybooks.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(user_librarybooks.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("libid",sh.getString("libid",""));

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

                  url = "http://" + sh.getString("ip", "") + ":5000/user_vlibbooks_search";
                  RequestQueue queue = Volley.newRequestQueue(user_librarybooks.this);

                  StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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


                              }

                              // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                              //lv.setAdapter(ad);

                              li.setAdapter(new custom_user_librarybooks_new(user_librarybooks.this, bimg, bn, auth, cat, ty, bkid));
//                    l1.setOnItemClickListener(user_librarybooks.this);

                          } catch (Exception e) {
                              Log.d("=========", e.toString());
                          }


                      }

                  }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {

                          Toast.makeText(user_librarybooks.this, "err" + error, Toast.LENGTH_SHORT).show();
                      }
                  }) {
                      @Override
                      protected Map<String, String> getParams() {
                          Map<String, String> params = new HashMap<>();
                          params.put("libid", sh.getString("libid", ""));
                          params.put("bnama", bname);
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