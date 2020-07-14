package com.me.data.patient.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patientData")
public class PatientData {

    @PrimaryKey(autoGenerate = true)
    private Long patientId;

    private String fullName;
    private String email;
    private int age;
    private char gender;

    public PatientData(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
