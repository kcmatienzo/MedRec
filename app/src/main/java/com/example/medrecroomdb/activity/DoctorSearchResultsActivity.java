package com.example.medrecroomdb.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medrecroomdb.R;

public class DoctorSearchResultsActivity extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName, editTextAddress, editTextHCNumber
            , editTextPhone, editTextEmail, editTextMedicalRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_searchresults);



        editTextFirstName = findViewById(R.id.editText_patientFirstName);
        editTextLastName = findViewById(R.id.editText_patientLastName);
        editTextAddress = findViewById(R.id.editText_patientAddress);
        editTextHCNumber = findViewById(R.id.editText_patientHCNumber);
        editTextPhone = findViewById(R.id.editText_patientPhone);
        editTextEmail = findViewById(R.id.editText_patientEmail);
        editTextMedicalRecord = findViewById(R.id.editText_patientMedicalRecord);



    }

}
