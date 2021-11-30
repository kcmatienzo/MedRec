package com.example.medrecroomdb.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medrecroomdb.R;

public class AdminSearchResultsActivity extends AppCompatActivity {

    EditText editTextUserId, editTextUserFirstName, editTextUserLastName,
            editTextUserAddress, editTextUserEmail, editTextPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_searchresults);

        editTextUserId = findViewById(R.id.editText_UserId);
        editTextUserFirstName = findViewById(R.id.editText_userFirstName);
        editTextUserLastName = findViewById(R.id.editText_userLastName);
        editTextUserAddress = findViewById(R.id.editText_Address);
        editTextUserEmail = findViewById(R.id.editText_userEmail);
        editTextPhoneNumber = findViewById(R.id.editText_userPhone);








    }
}
