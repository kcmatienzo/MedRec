package com.example.medrecroomdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medrecroomdb.R;
import com.example.medrecroomdb.model.Patient;
import com.example.medrecroomdb.viewmodel.AdminViewModel;

public class PatientNavActivity extends AppCompatActivity {
    // Declare variables
    private AdminViewModel adminViewModel;
    private Button button_viewMedRec, button_updateInfo, button_bookAppt;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_nav);

        button_viewMedRec = findViewById(R.id.btn_viewMedicalRecord);

        button_viewMedRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentViewMedRec = new Intent(v.getContext(), PatientViewMedRecActivity.class);
                startActivity(intentViewMedRec);
            }
        });

    }

}
