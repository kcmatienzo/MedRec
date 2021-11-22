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
import com.example.medrecroomdb.model.Doctor;
import com.example.medrecroomdb.viewmodel.DoctorViewModel;

import java.util.List;
import java.util.Random;

public class DoctorActivity extends AppCompatActivity {

    private DoctorViewModel doctorViewModel;
    private Button btnAddDoctor;
    private EditText txtDoctorFirstName, txtDoctorLastName, txtDoctorEmail, txtDoctorLicense, txtPassword;
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        TableLayout displayTable = (TableLayout) findViewById(R.id.displayTable);
        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel.class);
        //
        doctor = new Doctor();
        //
        doctorViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(DoctorActivity.this, "Doctor successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DoctorActivity.this, "Error saving doctor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // if the LiveData already has data it will delivered
        // to the observer
        doctorViewModel.getAllDoctors().observe(this, new Observer<List<Doctor>>() {
            @Override
            public void onChanged(@Nullable List<Doctor> result) {
                String output = "";
                displayTable.removeViews(1, Math.max(0, displayTable.getChildCount() - 1));
                for (Doctor doctor : result) {
                    TableRow row = new TableRow(getApplicationContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    lp.weight = 1;
                    lp.setMargins(10,5,10,5);
                    row.setLayoutParams(lp);

                    TextView id = new TextView(getApplicationContext());
                    id.setLayoutParams(lp);
                    id.setText(String.valueOf(doctor.getDoctorId()));
                    row.addView(id);

                    TextView firstName = new TextView(getApplicationContext());
                    firstName.setLayoutParams(lp);
                    firstName.setText(doctor.getFirstName());
                    row.addView(firstName);

                    TextView lastName = new TextView(getApplicationContext());
                    lastName.setLayoutParams(lp);
                    lastName.setText(doctor.getLastName());
                    row.addView(lastName);

                    TextView department = new TextView(getApplicationContext());
                    department.setLayoutParams(lp);
                    department.setText(doctor.getEmail());
                    row.addView(department);

                    TextView professorId = new TextView(getApplicationContext());
                    professorId.setLayoutParams(lp);
                    professorId.setText(String.valueOf(doctor.getDoctorLicenseNumber()));
                    row.addView(professorId);

                    TextView classroom = new TextView(getApplicationContext());
                    classroom.setLayoutParams(lp);
                    classroom.setText(String.valueOf(doctor.getPassword()));
                    row.addView(classroom);

                    displayTable.addView(row);
                }
            }
        });


        btnAddDoctor = findViewById(R.id.btnAddDoctor);
        btnAddDoctor.setOnClickListener(new View.OnClickListener() {
            //Implement the event handler method
            public void onClick(View v) {
                final int random = new Random().nextInt(100000-1) + 100000;
                doctor.setDoctorId(Integer.parseInt(Integer.toString(random)));

                txtDoctorFirstName = findViewById(R.id.txtDoctorFirstName);
                doctor.setFirstName(txtDoctorFirstName.getText().toString());

                txtDoctorLastName = findViewById(R.id.txtDoctorLastName);
                doctor.setLastName(txtDoctorLastName.getText().toString());

                txtDoctorEmail = findViewById(R.id.txtDoctorEmail);
                doctor.setEmail(txtDoctorEmail.getText().toString());

                txtDoctorLicense = findViewById(R.id.txtDoctorLicense);
                doctor.setDoctorLicenseNumber(txtDoctorLicense.getText().toString());

                txtPassword = findViewById(R.id.txtPassword);
                doctor.setPassword(txtPassword.getText().toString());

                doctorViewModel.insert(doctor);
            }
        });
    }
}