package com.me.data.base;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.me.data.patient.datasource.local.dao.IPatientDao;
import com.me.data.patient.model.PatientData;

@Database(entities = {PatientData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IPatientDao getPatientDao();
}