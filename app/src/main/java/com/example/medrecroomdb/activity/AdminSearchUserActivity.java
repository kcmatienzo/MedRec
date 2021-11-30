package com.example.medrecroomdb.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.medrecroomdb.R;
import com.example.medrecroomdb.model.Admin;
import com.example.medrecroomdb.model.Doctor;
import com.example.medrecroomdb.model.Patient;
import com.example.medrecroomdb.viewmodel.AdminViewModel;
import com.example.medrecroomdb.viewmodel.DoctorViewModel;
import com.example.medrecroomdb.viewmodel.PatientViewModel;


public class AdminSearchUserActivity extends AppCompatActivity {
    // Declare variables
    SharedPreferences pref;
    private PatientViewModel patientViewModel;
    private DoctorViewModel doctorViewModel;
    private Button btnSearchUser;
    private EditText editText_searchUserId;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_searchuser);

        // Set up references
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel.class);
        btnSearchUser = findViewById(R.id.button_searchUser);
        editText_searchUserId = findViewById(R.id.editText_searchUserId);

        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer userID = Integer.parseInt(editText_searchUserId.getText().toString());

                Patient patient = patientViewModel.findByPatientId(userID);
                Doctor doctor = doctorViewModel.findByDoctorId(userID);

                if(patient != null && doctor != null  && patient.getPatientId() == userID ||
                        doctor.getDoctorId() == userID){

                    Intent intent = new Intent();
                    intent = new Intent(view.getContext(), AdminSearchResultsActivity.class);
                    startActivity(intent);
                }

                else
                    {
                        Toast.makeText(view.getContext(), "No user found", Toast.LENGTH_SHORT).show();
                }
            }


        });


    }
}