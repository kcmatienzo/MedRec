package com.example.medrecroomdb.activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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
    private TextView fNameTextView, lNameTextView, addrTextView, healthcardTextView, phoneTextView, emailTextView, medicalTextView;
    Patient patient;
    String healthcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_searchresults);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        healthcard = getIntent().getStringExtra("healthcard");
        patient = patientViewModel.findByHealthcardNumber(healthcard);
        if (patient != null)
        {
            fNameTextView = findViewById(R.id.fNameTextView);
            fNameTextView.setText(patient.getFirstName());
            lNameTextView = findViewById(R.id.lNameTextView);
            lNameTextView.setText(patient.getLastName());
            addrTextView = findViewById(R.id.addrTextView);
            addrTextView.setText(patient.getEmail());
            healthcardTextView = findViewById(R.id.healthcardTextView);
            healthcardTextView.setText(patient.getHealthcardNumber());
            phoneTextView = findViewById(R.id.phoneTextView);
            phoneTextView.setText(patient.getEmail());
            emailTextView = findViewById(R.id.emailTextView);
            emailTextView.setText(patient.getEmail());
            medicalTextView = findViewById(R.id.medicalTextView);
            medicalTextView.setText(patient.getEmail());
        }
    }
}