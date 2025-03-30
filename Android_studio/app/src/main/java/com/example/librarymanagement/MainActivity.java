package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class
MainActivity extends AppCompatActivity {


    Button b1;
    SharedPreferences sh;
    EditText e1;
    String ip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.mnbt1);
        e1=findViewById(R.id.editTextTextPersonName15);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1.setText(sh.getString("ip",""));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ip=e1.getText().toString();
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("ip", ip);
                edp.commit();
                Intent ik = new Intent(getApplicationContext(), login.class);
                startActivity(ik);
            }
        });

    }
}