package com.me.data.patient.datasource.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.me.data.patient.model.PatientData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface IPatientDao {

    @Query("Select * from patientData")
    public Flowable<List<PatientData>> getAllPatients();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public List<Long> saveAllPatients(List<PatientData> patientList) ;

    @Query("Select * from patientData where patientId = :aPatientId")
    public Single<PatientData> getPatient(Long aPatientId);

    @Update
    public Completable updatePatient(PatientData patient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable savePatient(PatientData patient);

    @Query("DELETE  FROM PatientData WHERE patientId = :aPatientId ")
    public Completable deletePatient(Long aPatientId);

    @Query("DELETE FROM PatientData")
    public Completable clear();
}
