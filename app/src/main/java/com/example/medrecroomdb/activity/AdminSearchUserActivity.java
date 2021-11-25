package com.example.medrecroomdb.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medrecroomdb.R;
import com.example.medrecroomdb.model.Admin;
import com.example.medrecroomdb.viewmodel.AdminViewModel;


public class AdminSearchUserActivity extends AppCompatActivity {
    // Declare variables
    SharedPreferences pref;
    private AdminViewModel adminViewModel;
    private Button btnSearchUser;
    private EditText editText_searchUserId;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_searchuser);

        // Set up references
        pref = getSharedPreferences("nurse_information", MODE_PRIVATE);
    }
}