package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

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

public class user_libraryhome extends AppCompatActivity {

    ImageView b1,b2,b3,b4,b5,b6;
    SharedPreferences sh;
    RelativeLayout rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlibhomdesign);

        b1=findViewById(R.id.se1);
        b2=findViewById(R.id.button3);
        b3=findViewById(R.id.button4);
        b4=findViewById(R.id.button5);
        b5=findViewById(R.id.button6);
        b6=findViewById(R.id.button7);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        rv=findViewById(R.id.topArea);

        SharedPreferences.Editor edp = sh.edit();
        edp.putString("libid", sh.getString("libid",""));
        edp.commit();

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
                Intent ii =new Intent(getApplicationContext(),user_librarybooks.class);
                startActivity(ii);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_booklog.class);
                startActivity(ii);


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_libraryevents.class);
                startActivity(ii);


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_viewauthorboard.class);
                startActivity(ii);


            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),Test.class);
                ii.putExtra("uid",getIntent().getStringExtra("logid"));
                ii.putExtra("na",getIntent().getStringExtra("na"));
                startActivity(ii);


            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii =new Intent(getApplicationContext(),user_donateandsuggest.class);
                startActivity(ii);


            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),user_viewlibraries.class);
        startActivity(ii);

    }
}