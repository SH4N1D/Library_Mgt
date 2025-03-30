package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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

public class forgotpswd extends ListeningActivity {


    Button b1;
    SharedPreferences sh;
    EditText e1,e2;
    String uname,ema;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpswd);

        b1=findViewById(R.id.button18);
        e1=findViewById(R.id.editTextTextPersonName31);
        e2=findViewById(R.id.editTextTextPersonName21);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                forgot_password
                uname=e1.getText().toString();
                ema=e2.getText().toString();
                if(uname.equalsIgnoreCase(""))
                {
                    e1.setError("Type Username");
                }
                else if (ema.equalsIgnoreCase(""))
                {
                    e2.setError("Enter E-mail");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(ema).matches())
                {
                    e2.setError("Enter Valid Email");
                    e2.requestFocus();
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(forgotpswd.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/forgot_password";

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

                                    Toast.makeText(forgotpswd.this, "Check your mail", Toast.LENGTH_SHORT).show();

                                    Intent ik = new Intent(getApplicationContext(), login.class);
                                    startActivity(ik);


                                } else {

                                    Toast.makeText(forgotpswd.this, "Invalid username or email", Toast.LENGTH_SHORT).show();

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
                            params.put("ema", ema);

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            60000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                }
            }
        });


    }

    @Override
    public void processVoiceCommands(String... voiceCommands) {

    }

    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),login.class);
        startActivity(ii);

    }

}