package com.example.medrecroomdb.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.medrecroomdb.model.Admin;

import java.util.List;

@Dao
public interface AdminDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Admin Admin);

    @Query("SELECT * FROM Admin WHERE adminId = :adminId")
    Admin findByAdminId(int adminId); // find patient based on adminId

    @Query("select * from Admin order by adminId")
    LiveData<List<Admin>> getAllAdministrators();
}