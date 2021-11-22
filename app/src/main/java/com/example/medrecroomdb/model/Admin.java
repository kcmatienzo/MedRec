package com.example.medrecroomdb.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Admin {
    @PrimaryKey
    private int adminId;

    private String firstName;
    private String lastName;
    private String email;
    private String employeeId;
    private String password;

    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {return password; }
    public void setPassword(String password) { this.password = password; }
}