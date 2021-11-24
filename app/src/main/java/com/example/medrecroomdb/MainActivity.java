package com.example.medrecroomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medrecroomdb.activity.AdminActivity;
import com.example.medrecroomdb.activity.DoctorActivity;
import com.example.medrecroomdb.activity.PatientActivity;
import com.example.medrecroomdb.activity.SearchPatientActivity;
import com.example.medrecroomdb.model.Admin;
import com.example.medrecroomdb.model.Doctor;
import com.example.medrecroomdb.model.Patient;
import com.example.medrecroomdb.viewmodel.AdminViewModel;
import com.example.medrecroomdb.viewmodel.DoctorViewModel;
import com.example.medrecroomdb.viewmodel.PatientViewModel;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Declare variables
    private DoctorViewModel doctorViewModel;
    private PatientViewModel patientViewModel;
    private AdminViewModel adminViewModel;
    EditText editTextId, editTextPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up references
        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel.class);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        adminViewModel = ViewModelProviders.of(this).get(AdminViewModel.class);

        editTextId = findViewById(R.id.txtLoginId);
        editTextPassword = findViewById(R.id.txtLoginPassword);

        buttonLogin = findViewById(R.id.btnLogin);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        // Set up click listener for buttonLogin
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // get doctorId, password from EditText
                    int userId = Integer.parseInt(editTextId.getText().toString());
                    String password = editTextPassword.getText().toString();


                    // Set up variable doctor references of Type Doctor to find doctor by doctorId by findByDoctorId()
                    Doctor doctor = doctorViewModel.findByDoctorId(userId);

                    // Set up variable doctor references of Type Doctor to find doctor by doctorId by findByDoctorId()
                    Patient patient = patientViewModel.findByPatientId(userId);

                    // Set up variable doctor references of Type Doctor to find doctor by doctorId by findByDoctorId()
                    Admin admin = adminViewModel.findByAdminId(userId);

                    // Validate if nurseId and Password match the info in AppDatabase and if both are filled, return successful result
                    if (doctor != null && doctor.getDoctorId() == userId && doctor.getPassword().equals(password)) {
                        Intent intentDoctor = new Intent(v.getContext(), SearchPatientActivity.class);
                        startActivity(intentDoctor);

                    } else if (patient != null && patient.getPatientId() == userId && patient.getPassword().equals(password)) {
                        Intent intentPatient = new Intent(v.getContext(), SearchPatientActivity.class);
                        startActivity(intentPatient);
                    } else if (admin != null && admin.getAdminId() == userId && admin.getPassword().equals(password)) {
                        Intent intentAdmin = new Intent(v.getContext(), SearchPatientActivity.class);
                        startActivity(intentAdmin);
                    } else {
                        // Otherwise, show error message
                        Toast.makeText(getApplicationContext(), "Invalid username/password", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.print(e.getMessage());
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        parent.getItemAtPosition(pos);
        Button registerButton = (Button)findViewById(R.id.btnRegister);

        switch(parent.getItemAtPosition(pos).toString())
        {
            case "Patient":
                registerButton.setOnClickListener(new View.OnClickListener() {
                    //Implement the event handler method
                    public void onClick(View v) {
                        Intent intentPatient = new Intent(v.getContext(), PatientActivity.class);
                        startActivity(intentPatient);
                    }
                });

                break;
            case "Doctor":

                registerButton.setOnClickListener(new View.OnClickListener() {
                    //Implement the event handler method
                    public void onClick(View v) {
                        Intent intentDoctor = new Intent(v.getContext(), DoctorActivity.class);
                        startActivity(intentDoctor);
                    }
                });
                break;
            case "Admin":
                registerButton = (Button) findViewById(R.id.btnRegister);
                registerButton.setOnClickListener(new View.OnClickListener() {
                    //Implement the event handler method
                    public void onClick(View v) {
                        Intent intentAdmin = new Intent(v.getContext(), AdminActivity.class);
                        startActivity(intentAdmin);
                    }
                });
                break;
            case "":
                registerButton.setOnClickListener(new View.OnClickListener() {
                    //Implement the event handler method
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Please select your Role", Toast.LENGTH_SHORT).show();
                    }
                });

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}