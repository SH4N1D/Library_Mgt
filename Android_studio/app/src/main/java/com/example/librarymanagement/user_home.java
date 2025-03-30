package com.example.librarymanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class user_home extends AppCompatActivity {
    ImageView b1,b2,b3,b4,b6,b7,b8;
    SharedPreferences sh;
    RelativeLayout rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomedesign);

        b1 =findViewById(R.id.se1);
        b2=findViewById(R.id.button3);
        b3=findViewById(R.id.button4);
        b4=findViewById(R.id.button5);
//        b5=findViewById(R.id.uh5);
        b6=findViewById(R.id.button6);
        b7=findViewById(R.id.button7);
        b8=findViewById(R.id.button8);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    rv=findViewById(R.id.topArea);


        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        java.net.URL thumb_u;
        try {
//
//            thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");
//
            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+sh.getString("img",""));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");

            rv.setBackground(thumb_d);
        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_viewlibraries.class);
                startActivity(ii);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_customlibrary.class);
                startActivity(ii);


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_duedatenotify.class);
                startActivity(ii);


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_adminnotify.class);
                startActivity(ii);


            }
        });

//        b5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent ii =new Intent(getApplicationContext(),user_feedback.class);
//                startActivity(ii);
//
//
//            }
//        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_ratingandreview.class);
                startActivity(ii);


            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_complaint.class);
                startActivity(ii);


            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),login.class);
                startActivity(ii);
                AlertDialog.Builder ald = new AlertDialog.Builder(user_home.this);
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


        });


    }
    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),user_home.class);
        startActivity(ii);

    }

}