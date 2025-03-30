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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class user_sentcomplaint extends ListeningActivity {

    Button b1;
    SharedPreferences sh;
    EditText e1;
    String complaint;

    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sentcomplaint);

        b1=findViewById(R.id.us1);
        e1=findViewById(R.id.editTextTextPersonName50);
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
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                complaint=e1.getText().toString();
                if(complaint.equalsIgnoreCase(""))
                {
                    e1.setError("Enter Text");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(user_sentcomplaint.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/user_sendcomplaint";

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
                                    Toast.makeText(user_sentcomplaint.this, "Complaint sent successfully", Toast.LENGTH_SHORT).show();

                                    Intent ik = new Intent(getApplicationContext(), user_home.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(user_sentcomplaint.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("cmplnt", complaint);

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