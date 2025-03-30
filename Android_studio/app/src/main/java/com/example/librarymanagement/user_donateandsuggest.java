package com.example.librarymanagement;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
public class user_donateandsuggest extends ListeningActivity {
    Button b1,b2;
    SharedPreferences sh;
    EditText e1,e2,e3,e4,e5,e6,e7;
    String bname,author,detail,stock,category,review,title,url;
    Spinner s1;
    String PathHolder="";
    byte[] filedt=null;
    String [] type={"Donate","Suggest"};

    String typ="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_donateandsuggest);
        b1=findViewById(R.id.bud1);
        b2=findViewById(R.id.bud2);
        e1=findViewById(R.id.editTextTextPersonName37);
        e2=findViewById(R.id.editTextTextPersonName5);
        e3=findViewById(R.id.editTextTextPersonName38);
        e4=findViewById(R.id.editTextTextPersonName39);
        e5=findViewById(R.id.editTextTextPersonName40);
        e6=findViewById(R.id.editTextTextPersonName7);
        e7=findViewById(R.id.editTextTextPersonName42);
        s1=findViewById(R.id.spinner2);

        context = getApplicationContext();
        try {
            VoiceRecognitionListener.getInstance().setListener(this);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage() + "++++++++++++", Toast.LENGTH_LONG).show();
        }

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         ArrayAdapter<String> ad=new ArrayAdapter<String>(user_donateandsuggest.this,android.R.layout.simple_list_item_1,type);

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
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e3";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Speak", Toast.LENGTH_SHORT).show();
            }
        });
        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e4";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Speak", Toast.LENGTH_SHORT).show();
            }
        });
        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e5";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Speak", Toast.LENGTH_SHORT).show();
            }
        });
        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e6";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Speak", Toast.LENGTH_SHORT).show();
            }
        });
        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e7";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Speak", Toast.LENGTH_SHORT).show();
            }
        });




        s1.setAdapter(ad);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
//            intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 7);

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url = "http://" + sh.getString("ip","")+ ":5000/user_donate";


                bname=e1.getText().toString();
                author=e3.getText().toString();
                detail=e4.getText().toString();
                stock=e5.getText().toString();
                category=e6.getText().toString();
                review=e7.getText().toString();
                if(bname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter Book Name");
                }
                else if (author.equalsIgnoreCase(""))
                {
                    e3.setError("Enter Author Name");
                }
                else if (detail.equalsIgnoreCase(""))
                {
                    e4.setError("Enter Details");
                }
                else if (stock.equalsIgnoreCase(""))
                {
                    e5.setError("Enter No of Stocks");
                }
                else if (category.equalsIgnoreCase(""))
                {
                    e6.setError("Enter Category");
                }
                else if (review.equalsIgnoreCase(""))
                {
                    e7.setError("Enter Review");
                }
                else if (filedt == null)
                {
                    e2.setError("Choose Image");
                }
                else {

                    uploadBitmap(title);
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
        if(selected.equalsIgnoreCase("e3"))
        {
            e3.setText(text);
        }
        if(selected.equalsIgnoreCase("e4"))
        {
            e4.setText(text);
        }
        if(selected.equalsIgnoreCase("e5"))
        {
            e5.setText(text);
        }
        if(selected.equalsIgnoreCase("e6"))
        {
            e6.setText(text);
        }
        if(selected.equalsIgnoreCase("e7"))
        {
            e7.setText(text);
        }


    }


    ProgressDialog pd;
    private void uploadBitmap(final String title) {
        pd=new ProgressDialog(user_donateandsuggest.this);
        pd.setMessage("Uploading....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response1) {
                        pd.dismiss();
                        String x=new String(response1.data);
                        try {
                            JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                            if (obj.getString("task").equalsIgnoreCase("success")) {
                                Toast.makeText(user_donateandsuggest.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),user_libraryhome.class);

                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", s1.getSelectedItem().toString());
                params.put("bknm", bname);
                params.put("authr", author);
                params.put("dtl", detail);
                params.put("stk", stock);
                params.put("ctgry", category);
                params.put("rvw", review);
                params.put("lid",sh.getString("lid",""));
                params.put("libid",sh.getString("libid",""));
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("file", new DataPart(PathHolder , filedt ));
                return params;
            }
        };
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        PathHolder = FileUtils.getPathFromURI(this, uri);
//                        PathHolder = data.getData().getPath();
//                        Toast.makeText(this, PathHolder, Toast.LENGTH_SHORT).show();
                        filedt = getbyteData(PathHolder);
                        Log.d("filedataaa", filedt + "");
//                        Toast.makeText(this, filedt+"", Toast.LENGTH_SHORT).show();
                        e2.setText(PathHolder);
                    }
                    catch (Exception e){
                        Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    private byte[] getbyteData(String pathHolder) {
        Log.d("path", pathHolder);
        File fil = new File(pathHolder);
        int fln = (int) fil.length();
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(fil);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[fln];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            byteArray = bos.toByteArray();
            inputStream.close();
        } catch (Exception e) {
        }
        return byteArray;
    }
    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),user_libraryhome.class);
        startActivity(ii);

    }
}