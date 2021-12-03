package com.example.medrecroomdb.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    private Button btnSearchUser;
    private EditText editText_searchUserId;
    PatientViewModel patientViewModel;
    DoctorViewModel doctorViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_searchuser);
        // Set up references
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel.class);
        btnSearchUser = findViewById(R.id.button_searchUser);
        editText_searchUserId = findViewById(R.id.editText_searchUserId);
        // Set up click listener for Search Button
        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strUserId = editText_searchUserId.getText().toString();
                Log.d("MYVALUE", "onClick: " + strUserId);
                if(strUserId.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Valid User ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer userId = Integer.parseInt(editText_searchUserId.getText().toString());
                try {


                    // Set up variable doctor references of Type Doctor to find doctor by doctorId by findByHealthcardNumber()
                    Patient patient = patientViewModel.findByPatientId(userId);
                    Doctor doctor = doctorViewModel.findByDoctorId(userId);
                    // Validate if nurseId and Password match the info in AppDatabase and if both are filled, return successful result
                    if (patient != null){
                        Intent intentSearchResult = new Intent(v.getContext(), AdminSearchResultsActivity.class);
                        intentSearchResult.putExtra("userId", userId.toString());
                        Toast.makeText(getApplicationContext(), userId.toString(), Toast.LENGTH_SHORT).show();
                        startActivity(intentSearchResult);
                    }
                    else if (doctor != null && doctor.getDoctorId() == userId)
                    {
                        Intent intentSearchResult = new Intent(v.getContext(), AdminSearchResultsActivity.class);
                        intentSearchResult.putExtra("userId", userId.toString());
                        startActivity(intentSearchResult);
                    }
                    else {
                        // Otherwise, show error message
                        Toast.makeText(getApplicationContext(), "Invalid User ID.", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "User Id", Toast.LENGTH_SHORT).show();
                    System.out.print(e.getMessage());
                }
            }
        });
    }
}