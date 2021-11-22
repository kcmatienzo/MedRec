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
import com.example.medrecroomdb.model.Admin;
import com.example.medrecroomdb.model.Doctor;
import com.example.medrecroomdb.model.Patient;
import com.example.medrecroomdb.viewmodel.AdminViewModel;
import com.example.medrecroomdb.viewmodel.DoctorViewModel;
import com.example.medrecroomdb.viewmodel.PatientViewModel;

import java.util.List;
import java.util.Random;

public class AdminActivity extends AppCompatActivity {

    private AdminViewModel adminViewModel;
    private Button btnAddAdmin;
    private EditText txtAdminFirstName, txtAdminLastName, txtAdminEmail, txtAdminEmployeeNumber, txtPassword;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        TableLayout displayTable = (TableLayout) findViewById(R.id.displayTable);
        adminViewModel = ViewModelProviders.of(this).get(AdminViewModel.class);
        //
        admin = new Admin();
        //
        adminViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(AdminActivity.this, "Admin successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminActivity.this, "Error saving admin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // if the LiveData already has data it will delivered
        // to the observer
        adminViewModel.getAllAdmins().observe(this, new Observer<List<Admin>>() {
            @Override
            public void onChanged(@Nullable List<Admin> result) {
                String output = "";
                displayTable.removeViews(1, Math.max(0, displayTable.getChildCount() - 1));
                for (Admin admin : result) {
                    TableRow row = new TableRow(getApplicationContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    lp.weight = 1;
                    lp.setMargins(10,5,10,5);
                    row.setLayoutParams(lp);

                    TextView id = new TextView(getApplicationContext());
                    id.setLayoutParams(lp);
                    id.setText(String.valueOf(admin.getAdminId()));
                    row.addView(id);

                    TextView firstName = new TextView(getApplicationContext());
                    firstName.setLayoutParams(lp);
                    firstName.setText(admin.getFirstName());
                    row.addView(firstName);

                    TextView lastName = new TextView(getApplicationContext());
                    lastName.setLayoutParams(lp);
                    lastName.setText(admin.getLastName());
                    row.addView(lastName);

                    TextView department = new TextView(getApplicationContext());
                    department.setLayoutParams(lp);
                    department.setText(admin.getEmail());
                    row.addView(department);

                    TextView professorId = new TextView(getApplicationContext());
                    professorId.setLayoutParams(lp);
                    professorId.setText(String.valueOf(admin.getEmployeeId()));
                    row.addView(professorId);

                    TextView classroom = new TextView(getApplicationContext());
                    classroom.setLayoutParams(lp);
                    classroom.setText(String.valueOf(admin.getPassword()));
                    row.addView(classroom);

                    displayTable.addView(row);
                }
            }
        });


        btnAddAdmin = findViewById(R.id.btnAddAdmin);
        btnAddAdmin.setOnClickListener(new View.OnClickListener() {
            //Implement the event handler method
            public void onClick(View v) {
                final int random = new Random().nextInt(100000-1) + 100000;
                admin.setAdminId(Integer.parseInt(Integer.toString(random)));

                txtAdminFirstName = findViewById(R.id.txtAdminFirstName);
                admin.setFirstName(txtAdminFirstName.getText().toString());

                txtAdminLastName = findViewById(R.id.txtAdminLastName);
                admin.setLastName(txtAdminLastName.getText().toString());

                txtAdminEmail = findViewById(R.id.txtAdminEmail);
                admin.setEmail(txtAdminEmail.getText().toString());

                txtAdminEmployeeNumber = findViewById(R.id.txtAdminEmployeeNumber);
                admin.setEmployeeId(txtAdminEmployeeNumber.getText().toString());

                txtPassword = findViewById(R.id.txtPassword);
                admin.setPassword(txtPassword.getText().toString());

                adminViewModel.insert(admin);
            }
        });
    }
}