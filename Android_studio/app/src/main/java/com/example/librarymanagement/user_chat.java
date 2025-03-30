package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class user_chat extends AppCompatActivity {

    SharedPreferences sh;
    ListView li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat);

        li=findViewById(R.id.ul6);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


    }
    @Override
    public void onBackPressed() {
        Intent ii =new Intent(getApplicationContext(),user_viewauthorboard.class);
        startActivity(ii);

    }
}