package com.example.medrecroomdb.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medrecroomdb.R;
import com.example.medrecroomdb.model.Patient;
import com.example.medrecroomdb.viewmodel.PatientViewModel;

import java.util.List;
import java.util.Random;

public class PatientActivity extends AppCompatActivity {

    private PatientViewModel patientViewModel;
    private Button btnAddPatient;
    private EditText txtPatientFirstName, txtPatientLastName, txtPatientEmail, txtPatientHealthcardNumber, txtPassword;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        TableLayout displayTable = (TableLayout) findViewById(R.id.displayTable);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        //
        patient = new Patient();
        //
        patientViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(PatientActivity.this, "Patient successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PatientActivity.this, "Error saving patient", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // if the LiveData already has data it will delivered
        // to the observer
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@Nullable List<Patient> result) {
                String output = "";
                displayTable.removeViews(1, Math.max(0, displayTable.getChildCount() - 1));
                for (Patient patient : result) {
                    TableRow row = new TableRow(getApplicationContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    lp.weight = 1;
                    lp.setMargins(10,5,10,5);
                    row.setLayoutParams(lp);

                    TextView id = new TextView(getApplicationContext());
                    id.setLayoutParams(lp);
                    id.setText(String.valueOf(patient.getPatientId()));
                    row.addView(id);

                    TextView firstName = new TextView(getApplicationContext());
                    firstName.setLayoutParams(lp);
                    firstName.setText(patient.getFirstName());
                    row.addView(firstName);

                    TextView lastName = new TextView(getApplicationContext());
                    lastName.setLayoutParams(lp);
                    lastName.setText(patient.getLastName());
                    row.addView(lastName);

                    TextView department = new TextView(getApplicationContext());
                    department.setLayoutParams(lp);
                    department.setText(patient.getEmail());
                    row.addView(department);

                    TextView professorId = new TextView(getApplicationContext());
                    professorId.setLayoutParams(lp);
                    professorId.setText(String.valueOf(patient.getHealthcardNumber()));
                    row.addView(professorId);

                    TextView classroom = new TextView(getApplicationContext());
                    classroom.setLayoutParams(lp);
                    classroom.setText(String.valueOf(patient.getPassword()));
                    row.addView(classroom);

                    displayTable.addView(row);
                }
            }
        });


        btnAddPatient = findViewById(R.id.btnAddPatient);
        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            //Implement the event handler method
            public void onClick(View v) {
                final int random = new Random().nextInt(100000-1) + 100000;
                patient.setPatientId(Integer.parseInt(Integer.toString(random)));

                txtPatientFirstName = findViewById(R.id.txtPatientFirstName);
                patient.setFirstName(txtPatientFirstName.getText().toString());

                txtPatientLastName = findViewById(R.id.txtPatientLastName);
                patient.setLastName(txtPatientLastName.getText().toString());

                txtPatientEmail = findViewById(R.id.txtPatientEmail);
                patient.setEmail(txtPatientEmail.getText().toString());

                txtPatientHealthcardNumber = findViewById(R.id.txtpatientHealthcard);
                patient.setHealthcardNumber(txtPatientHealthcardNumber.getText().toString());

                txtPassword = findViewById(R.id.txtPassword);
                patient.setPassword(txtPassword.getText().toString());

                patientViewModel.insert(patient);
            }
        });
    }
}