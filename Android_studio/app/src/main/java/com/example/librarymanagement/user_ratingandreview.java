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
import android.widget.RatingBar;
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

public class user_ratingandreview extends ListeningActivity {


    Button b1;
    SharedPreferences sh;
    EditText e2;
    String rating,review;
    RatingBar r1;

    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ratingandreview);

        b1=findViewById(R.id.brr1);
        r1=findViewById(R.id.ratingBar);
        e2=findViewById(R.id.editTextTextPersonName48);
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
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e2";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Speak", Toast.LENGTH_SHORT).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rating=String.valueOf(r1.getRating());
                review=e2.getText().toString();
               if(review.equalsIgnoreCase(""))
                {
                    e2.setError("Enter Review");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(user_ratingandreview.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/user_addratrvw";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("success")) {
//
                                    Toast.makeText(user_ratingandreview.this, "Feedback sent successfully", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), user_home.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(user_ratingandreview.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("lid", sh.getString("lid", ""));
                            params.put("rtng", rating);
                            params.put("rvw", review);

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
        if(selected.equalsIgnoreCase("e2"))
        {
            e2.setText(text);
        }
    }

    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),user_home.class);
        startActivity(ii);

    }
}