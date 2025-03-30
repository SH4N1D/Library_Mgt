package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class lorder extends ListeningActivity implements AdapterView.OnItemSelectedListener {

    Button b1;
    SharedPreferences sh;
    EditText e1;
    Spinner s1;
    String dudte,url,uidn;
    ArrayList<String>uid,un;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lorder);

        b1=findViewById(R.id.lorbt1);
        e1=findViewById(R.id.editTextTextPersonName2);
        s1=findViewById(R.id.spinner);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        lorder.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                String d=year+"-";
                                monthOfYear=monthOfYear+1;

                                if (monthOfYear<10)
                                {
                                    d=d+"0"+monthOfYear+"-";
                                }else
                                {
                                    d=d+monthOfYear+"-";
                                }
                                if(dayOfMonth<10)
                                {
                                    d=d+"0"+dayOfMonth;
                                }
                                else
                                {
                                    d=d+dayOfMonth;
                                }
                                e1.setText(d);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            }
        });

        url ="http://"+sh.getString("ip", "") + ":5000/ordspin";
        RequestQueue queue = Volley.newRequestQueue(lorder.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    uid= new ArrayList<>();
                    un= new ArrayList<>();
//                    ul= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        uid.add(jo.getString("uid"));
                        un.add(jo.getString("userf")+" "+jo.getString("userl"));
//                        ul.add(jo.getString("ln"));

                    }

                     ArrayAdapter<String> ad=new ArrayAdapter<>(lorder.this,android.R.layout.simple_list_item_1,un);
                    s1.setAdapter(ad);

//                    l1.setAdapter(new Custom(lorder.this,name,place));
                    s1.setOnItemSelectedListener(lorder.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(lorder.this, "err"+error, Toast.LENGTH_SHORT).show();
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

                dudte=e1.getText().toString();
                if(dudte.equalsIgnoreCase(""))
                {
                    e1.setError("Select Date");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(lorder.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/orderbook";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("success")) {
                                    Toast.makeText(lorder.this, "succesfully ordered", Toast.LENGTH_SHORT).show();

//
                                    Intent ik = new Intent(getApplicationContext(), libbooks.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(lorder.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("uid", uidn);
                            params.put("ddt", dudte);
                            params.put("lid", sh.getString("lid", ""));
                            params.put("bkid", getIntent().getStringExtra("bid"));


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

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        uidn=uid.get(i);





    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}