package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class suggestions extends ListeningActivity {

    Button b1;
    SharedPreferences sh;
    EditText e1;
    ListView li;
    String date,url;
    ArrayList<String>un,bn,im,da,st,de,au,re,ca,sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        b1=findViewById(R.id.subt1);
        e1=findViewById(R.id.editTextTextPersonName22);
        li=findViewById(R.id.list10);
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
                        suggestions.this,
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
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            }
        });
        url ="http://"+sh.getString("ip", "") + ":5000/view_suggestbk";
        RequestQueue queue = Volley.newRequestQueue(suggestions.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

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
                    sid=new ArrayList<>();


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
                        sid.add(jo.getString("id"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    li.setAdapter(new custom_suggestions(suggestions.this,im,un,bn,au,sid));
//                    l1.setOnItemClickListener(suggestions.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(suggestions.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid",sh.getString("lid",""));
                return params;
            }
        };
        queue.add(stringRequest);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                date=e1.getText().toString();
                if(date.equalsIgnoreCase(""))
                {
                    e1.setError("Choose Date");
                }
                else {
                    url = "http://" + sh.getString("ip", "") + ":5000/view_suggestbk_search";
                    RequestQueue queue = Volley.newRequestQueue(suggestions.this);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {

                                JSONArray ar = new JSONArray(response);
                                un = new ArrayList<>();
                                bn = new ArrayList<>();
                                im = new ArrayList<>();
                                da = new ArrayList<>();
                                st = new ArrayList<>();
                                de = new ArrayList<>();
                                au = new ArrayList<>();
                                re = new ArrayList<>();
                                ca = new ArrayList<>();
                                sid = new ArrayList<>();


                                for (int i = 0; i < ar.length(); i++) {
                                    JSONObject jo = ar.getJSONObject(i);
                                    un.add(jo.getString("userf") + "" + jo.getString("userl"));
                                    bn.add(jo.getString("bookname"));
                                    im.add(jo.getString("image"));
                                    da.add(jo.getString("date"));
                                    st.add(jo.getString("stock"));
                                    de.add(jo.getString("details"));
                                    au.add(jo.getString("author"));
                                    re.add(jo.getString("review"));
                                    ca.add(jo.getString("category"));
                                    sid.add(jo.getString("id"));

                                }

                                // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                                //lv.setAdapter(ad);

                                li.setAdapter(new custom_suggestions(suggestions.this,im,un,bn,au,sid));
//                    l1.setOnItemClickListener(suggestions.this);

                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                            }


                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(suggestions.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("lid", sh.getString("lid", ""));
                            params.put("date", date);
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
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),libhome.class);
        startActivity(ii);

    }
}