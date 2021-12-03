package com.example.medrecroomdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class UpdateUserActivity extends AppCompatActivity {
    String strId;
    Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        strId = intent.getStringExtra("userId");
        id = Integer.parseInt(strId);

        Toast.makeText(getApplicationContext(), strId, Toast.LENGTH_SHORT).show();
    }
}