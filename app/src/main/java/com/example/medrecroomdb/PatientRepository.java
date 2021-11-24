package com.example.medrecroomdb;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medrecroomdb.dao.PatientDao;
import com.example.medrecroomdb.db.MedRecDb;
import com.example.medrecroomdb.model.Admin;
import com.example.medrecroomdb.model.Patient;

import java.util.List;

public class PatientRepository {
    private final PatientDao patientDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Patient>> patientList;
    private LiveData<Patient> patient;

    public PatientRepository(Context context) {
        //create a database object
        MedRecDb db = MedRecDb.getInstance(context);
        //create an interface object
        patientDao = db.patientDao();
        //call interface method
        patientList = patientDao.getAllPatients();
    }

    // returns query results as LiveData object
    public LiveData<List<Patient>> getPatientList() {
        return patientList;
    }
    //inserts a person asynchronously
    public void insert(Patient patient) {
        insertAsync(patient);
    }

    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    // Find patient, this is called from ViewModel
    public Patient findByPatientId(int patientId) { return patientDao.findByPatientId(patientId); }

    private void insertAsync(final Patient patient) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.insert(patient);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
