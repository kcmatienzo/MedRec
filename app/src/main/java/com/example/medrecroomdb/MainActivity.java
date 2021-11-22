package com.example.medrecroomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.medrecroomdb.activity.AdminActivity;
import com.example.medrecroomdb.activity.DoctorActivity;
import com.example.medrecroomdb.activity.PatientActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
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
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}