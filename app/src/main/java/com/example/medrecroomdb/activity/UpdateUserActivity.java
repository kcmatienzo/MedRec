package com.example.medrecroomdb.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medrecroomdb.R;
import com.example.medrecroomdb.model.Doctor;
import com.example.medrecroomdb.model.Patient;
import com.example.medrecroomdb.viewmodel.DoctorViewModel;
import com.example.medrecroomdb.viewmodel.PatientViewModel;


public class UpdateUserActivity extends AppCompatActivity {
    String strId;
    Integer id;
    String userType;

    PatientViewModel patientViewModel;
    DoctorViewModel doctorViewModel;
    Patient patient;
    Doctor doctor;
    private EditText editText_UserId, editText_firstName, editText_lastName, editText_address, editText_email, editText_phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        Intent intent=getIntent();
        strId = intent.getStringExtra("userId");
        userType = intent.getStringExtra("userType");
        id = Integer.parseInt(strId);

        editText_UserId = findViewById(R.id.editTextUpdateUserUserId);
        editText_firstName = findViewById(R.id.editTextUpdateUserFirstName);
        editText_lastName = findViewById(R.id.editTextUpdateUserLastName);
        editText_address = findViewById(R.id.editTextUpdateUserAddress);
        editText_email = findViewById(R.id.editTextUpdateUserEmail);
        editText_phoneNumber = findViewById(R.id.editTextUpdateUserPhoneNumber);

        if(userType.equals("patient")){
            patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
            patient = patientViewModel.findByPatientId(id);

            editText_UserId.setText(String.valueOf(patient.getPatientId()));
            editText_firstName.setText(patient.getFirstName());
            editText_lastName.setText(patient.getLastName());
            editText_address.setText(patient.getEmail());
            editText_email.setText(patient.getEmail());
            editText_phoneNumber.setText(patient.getEmail());
        }
        else if(userType.equals("doctor")){
            doctorViewModel= ViewModelProviders.of(this).get(DoctorViewModel.class);
            doctor = doctorViewModel.findByDoctorId(id);

            editText_UserId.setText(String.valueOf(doctor.getDoctorId()));
            editText_firstName.setText(doctor.getFirstName());
            editText_lastName.setText(doctor.getLastName());
            editText_address.setText(doctor.getEmail());
            editText_email.setText(doctor.getEmail());
            editText_phoneNumber.setText(doctor.getEmail());
        }else {
            Toast.makeText(getApplicationContext(), "Could Not Find User", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(getApplicationContext(), strId, Toast.LENGTH_SHORT).show();
    }
}