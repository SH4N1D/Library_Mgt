package com.example.librarymanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    Button b1;
    SharedPreferences sh;
    EditText e1,e2;
    String uname,pass;
    TextView btn_register,fgtpswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        b1=findViewById(R.id.login);
        btn_register=findViewById(R.id.btn_register);
        fgtpswd=findViewById(R.id.fgtpswd);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname=e1.getText().toString();
                pass=e2.getText().toString();
                if(uname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter username");
                }
                else if(pass.equalsIgnoreCase(""))
                {
                    e2.setError("Enter password");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(login.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/android_logincode";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("valid")) {
                                    String lid = json.getString("id");
                                    String type = json.getString("type");
                                    String img = json.getString("img");
                                    SharedPreferences.Editor edp = sh.edit();
                                    edp.putString("lid", lid);
                                    edp.putString("img", img);
                                    edp.commit();
                                    if (type.equalsIgnoreCase("library")) {
                                        Intent ik = new Intent(getApplicationContext(), libhome.class);
                                        startActivity(ik);
                                    } else if (type.equalsIgnoreCase("user")) {
                                        Intent ik = new Intent(getApplicationContext(), user_home.class);
                                        startActivity(ik);
                                    }


                                } else {

                                    Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("uname", uname);
                            params.put("pass", pass);

                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ald=new AlertDialog.Builder(login.this);
                ald.setTitle("sign up with")
                        .setPositiveButton(" User ", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                try
                                {

                                    Intent ii =new Intent(getApplicationContext(),user_registration.class);
                                    startActivity(ii);


                                }
                                catch(Exception e)
                                {
                                    Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNegativeButton(" Library ", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent ii =new Intent(getApplicationContext(),library_registration.class);
                startActivity(ii);
                            }
                        });

                AlertDialog al=ald.create();
                al.show();




            }
        });

//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent ii =new Intent(getApplicationContext(),library_registration.class);
//                startActivity(ii);
//
//            }
//        });

        fgtpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii =new Intent(getApplicationContext(),forgotpswd.class);
                startActivity(ii);

            }
        });

    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder ald = new AlertDialog.Builder(login.this);
        ald.setTitle("Do you want to exit ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent in = new Intent(Intent.ACTION_MAIN);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        in.addCategory(Intent.CATEGORY_HOME);
                        startActivity(in);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                });

        AlertDialog al = ald.create();
        al.show();
    }

}