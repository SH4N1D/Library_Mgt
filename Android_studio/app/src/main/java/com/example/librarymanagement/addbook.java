package com.example.librarymanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addbook extends ListeningActivity{

    Button b1,b2,b11;
    SharedPreferences sh;
    TextView tv32;
    EditText e1,e2,e3,e4,e5,e6,e7,e20;
    String bkname,author,detail,stock,review,category,title,url;
    String PathHolder="";
    String PathHolder1="";
    byte[] filedt=null;
    byte[] filedt1=null;
    RadioButton r1,r2;
    RadioGroup rg;
    String type="",selected="";
    boolean isValidInput = true;
    long result = 0;
    long finalResult = 0;

    private static final int REQUEST_IMAGE_CAPTURE = 1;


    private Bitmap capturedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        b1 = findViewById(R.id.bkbt1);
        b11 = findViewById(R.id.button11);
        b2 = findViewById(R.id.bkbt2);
//
        e1 = findViewById(R.id.editTextTextPersonName3);
        e2 = findViewById(R.id.editTextTextPersonName28);
        e3 = findViewById(R.id.editTextTextPersonName6);
        e4 = findViewById(R.id.editTextTextPersonName11);
        e5 = findViewById(R.id.editTextTextPersonName12);
        e6 = findViewById(R.id.editTextTextPersonName13);
        e7 = findViewById(R.id.editTextTextPersonName29);
        e20 = findViewById(R.id.editTextTextPersonName20);
        tv32 = findViewById(R.id.textView32);
        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        rg = findViewById(R.id.rg);
        tv32.setVisibility(View.GONE);
        b11.setVisibility(View.GONE);
        e20.setVisibility(View.GONE);

        r1.setChecked(true);
        context = getApplicationContext();
        try {
            VoiceRecognitionListener.getInstance().setListener(this);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage() + "++++++++++++", Toast.LENGTH_LONG).show();
        }


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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
                Toast.makeText(context, "Please say book name", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say author name", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say Details", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say No.Stock", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say Review", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Please say Category", Toast.LENGTH_SHORT).show();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");
////            intent.setType("application/pdf");
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent, 7);


                AlertDialog.Builder ald = new AlertDialog.Builder(addbook.this);
                ald.setTitle("File")
                        .setPositiveButton(" Browse ", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                try {

                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("*/*");
                                    //            intent.setType("application/pdf");
                                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                                    startActivityForResult(intent, 7);


                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNegativeButton(" Capture ", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                                }
                            }
                        });

                AlertDialog al = ald.create();
                al.show();


            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
//            intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 6);


            }
        });


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.radioButton) {
                    tv32.setVisibility(View.GONE);
                    b11.setVisibility(View.GONE);
                    e20.setVisibility(View.GONE);

                } else {
                    tv32.setVisibility(View.VISIBLE);
                    b11.setVisibility(View.VISIBLE);
                    e20.setVisibility(View.VISIBLE);
                }
            }
        });
//        url = "http://" + sh.getString("ip","")+ ":5000/addbook";


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(addbook.this, "---------------"+filedt, Toast.LENGTH_SHORT).show();
                bkname = e1.getText().toString();
                author = e3.getText().toString();
                detail = e4.getText().toString();
                stock = e5.getText().toString();
                review = e6.getText().toString();
                category = e7.getText().toString();

                if (bkname.equalsIgnoreCase("")) {
                    e1.setError("Enter Book Name");
                } else if (author.equalsIgnoreCase("")) {
                    e3.setError("Enter Author Name");
                } else if (detail.equalsIgnoreCase("")) {
                    e4.setError("Enter Details");
                } else if (stock.equalsIgnoreCase("")) {
                    e5.setError("Enter No of Stocks");
                } else if (review.equalsIgnoreCase("")) {
                    e6.setError("Enter Review");
                } else if (category.equalsIgnoreCase("")) {
                    e7.setError("Enter Category");
                } else if (filedt == null) {
                    e2.setError("Choose Image");
                } else {
                    if (r1.isChecked()) {
                        type = "Hard Copy";
                        if (filedt == null) {
                            e2.setError("Choose Image");
                        } else {
                            uploadBitmap(title);
                        }
                    } else {
                        type = "Soft Copy";
                        if (filedt1 == null) {
                            e20.setError("Choose Image");
                        } else {
                            uploadBitmap1(title);
                        }
                    }
                }
            }
        });

    }

    @Override
    public void processVoiceCommands(String... voiceCommands) {
      String  text = voiceCommands[0];
        Toast.makeText(context, "====+++===+++"+text, Toast.LENGTH_SHORT).show();
        if(selected.equalsIgnoreCase("e1"))
        {
            e1.setText(text);
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
        else if(selected.equalsIgnoreCase("e6"))
        {
            e6.setText(text);
        }
        else if(selected.equalsIgnoreCase("e7"))
        {
            e7.setText(text);
        }



    }


    private void uploadBitmap(final String title) {


         url = "http://" + sh.getString("ip","")+ ":5000/addbook";

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response1) {
                        String x=new String(response1.data);
                        try {
                            JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                            if (obj.getString("task").equalsIgnoreCase("success")) {

                                Toast.makeText(addbook.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),libbooks.class);
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

                params.put("name", bkname);
                params.put("author", author);
                params.put("details", detail);
                params.put("stock", stock);
                params.put("review", review);
                params.put("category", category);
                params.put("type", type);
                params.put("lid",sh.getString("lid",""));

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

    private void uploadBitmap1(final String title) {


         url = "http://" + sh.getString("ip","")+ ":5000/addbook";

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response1) {
                        String x=new String(response1.data);
                        try {
                            JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                            if (obj.getString("task").equalsIgnoreCase("success")) {

                                Toast.makeText(addbook.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),libbooks.class);
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

                params.put("name", bkname);
                params.put("author", author);
                params.put("details", detail);
                params.put("stock", stock);
                params.put("review", review);
                params.put("category", category);
                params.put("type", type);
                params.put("lid",sh.getString("lid",""));

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("file", new DataPart(PathHolder , filedt ));
                params.put("file1", new DataPart(PathHolder1 , filedt1 ));









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

                        filedt = getbyteData(PathHolder,uri);
                        Log.d("filedataaa", filedt + "");
//                        Toast.makeText(this, filedt+"", Toast.LENGTH_SHORT).show();
                        e2.setText(PathHolder);
                    }
                    catch (Exception e){
                        Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
                case 6:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        PathHolder1 = FileUtils.getPathFromURI(this, uri);
//                        PathHolder = data.getData().getPath();
//                        Toast.makeText(this, PathHolder, Toast.LENGTH_SHORT).show();

                        filedt1 = getbyteData(PathHolder1,uri);

//                       getbyteData
//                        Toast.makeText(this, filedt+"", Toast.LENGTH_SHORT).show();
                        e20.setText(PathHolder1);
                    }
                    catch (Exception e){
                        Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                capturedImage = (Bitmap) extras.get("data");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                capturedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                filedt = byteArrayOutputStream.toByteArray();
                PathHolder="sample.jpg";
                e2.setText(PathHolder);
                Toast.makeText(getApplicationContext(),""+filedt,Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_LONG).show();
            }
        }
    }
//
//    private byte[] getbyteData(String pathHolder) {
//        Log.d("path", pathHolder);
//        File fil = new File(pathHolder);
//        int fln = (int) fil.length();
//        byte[] byteArray = null;
//        try {
//            InputStream inputStream = new FileInputStream(fil);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] b = new byte[fln];
//            int bytesRead = 0;
//
//            while ((bytesRead = inputStream.read(b)) != -1) {
//                bos.write(b, 0, bytesRead);
//            }
//            byteArray = bos.toByteArray();
//            inputStream.close();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
//        }
//        return byteArray;
//
//
//    }

    private byte[] getbyteData(String pathHolder,Uri suri) {
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
//            Toast.makeText(getApplicationContext(),"++"+e,Toast.LENGTH_LONG).show();
            File file = new File(pathHolder);
            if (file.exists()) {


            } else {

            }
            try {

                InputStream inputStream = getContentResolver().openInputStream(suri);

//                String ss="com."+pathHolder.split("com.")[1];
//                Uri fileUri = Uri.parse(ss);
//                InputStream inputStream = getContentResolver().openInputStream(fileUri);


                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                return byteArrayOutputStream.toByteArray();



            }
            catch (Exception e1)
            {
                Toast.makeText(getApplicationContext(),"file exist=="+e1,Toast.LENGTH_LONG).show();
//
            }



        }
        return byteArray;


    }
    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),libbooks.class);
        startActivity(ii);

    }


}

