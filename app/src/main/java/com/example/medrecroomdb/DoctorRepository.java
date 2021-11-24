package com.example.medrecroomdb;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medrecroomdb.dao.DoctorDao;
import com.example.medrecroomdb.dao.PatientDao;
import com.example.medrecroomdb.db.MedRecDb;
import com.example.medrecroomdb.model.Doctor;
import com.example.medrecroomdb.model.Patient;

import java.util.List;

public class DoctorRepository {
    private final DoctorDao doctorDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Doctor>> doctorList;
    private LiveData<Doctor> doctor;

    public DoctorRepository(Context context) {
        //create a database object
        MedRecDb db = MedRecDb.getInstance(context);
        //create an interface object
        doctorDao = db.doctorDao();
        //call interface method
        doctorList = doctorDao.getAllDoctors();
    }

    // returns query results as LiveData object
    public LiveData<List<Doctor>> getDoctorList() {
        return doctorList;
    }

    // Find doctor, this is called from ViewModel
    public Doctor findByDoctorId(int doctorId) { return doctorDao.findByDoctorId(doctorId); }

    //inserts a person asynchronously
    public void insert(Doctor doctor) {
        insertAsync(doctor);
    }

    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Doctor doctor) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doctorDao.insert(doctor);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}

