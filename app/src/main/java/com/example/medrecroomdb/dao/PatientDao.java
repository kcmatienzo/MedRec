package com.example.medrecroomdb.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.medrecroomdb.model.Patient;

import java.util.List;

@Dao
public interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Patient Patient);

    @Query("SELECT * FROM Patient WHERE patientId = :patientId")
    Patient findByPatientId(int patientId); // find patient based on patientId

    //Monitoring Query Result Changes with Live Data
    @Query("select * from Patient order by patientId")
    LiveData<List<Patient>> getAllPatients();
}
