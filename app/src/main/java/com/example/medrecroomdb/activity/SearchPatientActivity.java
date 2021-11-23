package com.example.medrecroomdb.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medrecroomdb.model.Doctor;
import com.example.medrecroomdb.model.Patient;
import com.example.medrecroomdb.viewmodel.DoctorViewModel;
import com.example.medrecroomdb.viewmodel.PatientViewModel;

public class SearchPatientActivity extends AppCompatActivity {
    private PatientViewModel patientViewModel;
    private Button btnSearchPatient;
    private EditText editText_pHealthcardNumber;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    }
}
