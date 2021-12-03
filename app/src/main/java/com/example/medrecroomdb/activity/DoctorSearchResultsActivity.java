package com.example.medrecroomdb.activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.medrecroomdb.R;
import com.example.medrecroomdb.dao.PatientDao;
import com.example.medrecroomdb.model.Patient;
import com.example.medrecroomdb.viewmodel.PatientViewModel;
import java.util.ArrayList;
import java.util.List;
public class DoctorSearchResultsActivity extends AppCompatActivity {

    private PatientViewModel patientViewModel;
    private TextView fNameTxtView, lNameTxtView, addrTxtView, healthcardTxtView, phoneTxtView, emailTxtView, medicalTxtView;
    Patient patient;
    String healthcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_searchresults);
        try{
            Intent intent=getIntent();
            healthcard = intent.getStringExtra("healthcardNumber");
            patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
            patient = patientViewModel.findByHealthcardNumber(healthcard);
            if (patient != null)
            {
                fNameTxtView = findViewById(R.id.fNameTextView);
                fNameTxtView.setText(patient.getFirstName());
                lNameTxtView = findViewById(R.id.lNameTextView);
                lNameTxtView.setText(patient.getLastName());
                addrTxtView = findViewById(R.id.addrTextView);
                addrTxtView.setText(patient.getEmail());
                healthcardTxtView = findViewById(R.id.healthcardTextView);
                healthcardTxtView.setText(patient.getHealthcardNumber());
                phoneTxtView = findViewById(R.id.phoneTextView);
                phoneTxtView.setText(patient.getEmail());
                emailTxtView = findViewById(R.id.emailTextView);
                emailTxtView.setText(patient.getEmail());
                medicalTxtView = findViewById(R.id.medicalTextView);
                medicalTxtView.setText(patient.getEmail());
            }
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}