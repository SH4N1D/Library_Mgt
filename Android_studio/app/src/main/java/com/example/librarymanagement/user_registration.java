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
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class user_registration extends ListeningActivity {

    Button b1,b2;
    SharedPreferences sh;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    String fname,lname,place,phoneno,email,gender,uname,pswd,title,url;
    RadioButton r1,r2;
    String PathHolder="";
    byte[] filedt=null;


    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        b1=findViewById(R.id.baur1);
        b2=findViewById(R.id.baur2);
        e1=findViewById(R.id.editTextTextPersonName59);
        e2=findViewById(R.id.editTextTextPersonName61);
        e3=findViewById(R.id.editTextTextPersonName63);
        e4=findViewById(R.id.editTextTextPersonName64);
        e5=findViewById(R.id.editTextTextPersonName65);
        e6=findViewById(R.id.editTextTextPersonName66);
        e7=findViewById(R.id.editTextTextPersonName16);
        e8=findViewById(R.id.editTextTextPersonName18);
        r1=findViewById(R.id.radioButton5);
        r2=findViewById(R.id.radioButton6);

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
                Toast.makeText(context, "Please say First Name", Toast.LENGTH_SHORT).show();
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e2";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Please say Last Name", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say name of Place", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say Phone number", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say Email id", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say User name", Toast.LENGTH_SHORT).show();
            }
        });
        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = "e8";
                try {
                    stopListening();
                } catch (Exception e) {
                }
                startListening();
                Toast.makeText(context, "Please say password", Toast.LENGTH_SHORT).show();
            }
        });


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

        url = "http://" + sh.getString("ip","")+ ":5000/user_reg";


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fname=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                phoneno=e4.getText().toString();
                email=e5.getText().toString();
                uname=e7.getText().toString();
                pswd=e8.getText().toString();
                gender="";
                if(r1.isChecked())
                {
                    gender=r1.getText().toString();
                }
                else if(r2.isChecked())
                {
                    gender=r2.getText().toString();
                }

                if(fname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter Text");
                }
                else if (lname.equalsIgnoreCase(""))
                {
                    e2.setError("Enter Text");
                }
                else if (place.equalsIgnoreCase(""))
                {
                    e3.setError("Enter Text");
                }
                else if (phoneno.equalsIgnoreCase(""))
                {
                    e4.setError("Enter Text");
                }
                else if(phoneno.length()<10)
                {
                    e4.setError("Minimum 10 nos required");
                    e4.requestFocus();
                }
                else if (email.equalsIgnoreCase(""))
                {
                    e5.setError("Enter Text");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    e5.setError("Enter Valid Email");
                    e5.requestFocus();
                }

                else if (uname.equalsIgnoreCase(""))
                {
                    e7.setError("Enter Text");
                }
                else if (pswd.equalsIgnoreCase(""))
                {
                    e8.setError("Enter Text");
                }
                else if (filedt == null)
                {
                    e6.setError("Choose Image");
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
        else if(selected.equalsIgnoreCase("e2"))
        {
            e2.setText(text);
        }
        else if(selected.equalsIgnoreCase("e3"))
        {
            e3.setText(text);
        }
        else if(selected.equalsIgnoreCase("e4"))
        {
            e4.setText(text);
        }
        else if(selected.equalsIgnoreCase("e5"))
        {
            e5.setText(text);
        }
        else if(selected.equalsIgnoreCase("e7"))
        {
            e7.setText(text);
        }
        else if(selected.equalsIgnoreCase("e8"))
        {
            e8.setText(text);
        }


    }


    ProgressDialog pd;
    private void uploadBitmap(final String title) {
        pd=new ProgressDialog(user_registration.this);
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

                                Toast.makeText(user_registration.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),login.class);
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


                params.put("name", fname);
                params.put("lname", lname);
                params.put("place", place);
                params.put("phoneno", phoneno);
                params.put("email", email);
                params.put("gender", gender);
                params.put("uname", uname);
                params.put("pswd", pswd);
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
                        e6.setText(PathHolder);
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
        Intent ii =new Intent(getApplicationContext(),login.class);
        startActivity(ii);

    }


}