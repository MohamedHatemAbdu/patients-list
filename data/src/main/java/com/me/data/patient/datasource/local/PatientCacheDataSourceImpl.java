package com.me.data.patient.datasource.local;

import com.me.data.base.AppDatabase;
import com.me.data.patient.datasource.IPatientCacheDataSource;
import com.me.data.patient.datasource.local.dao.IPatientDao;
import com.me.data.patient.model.PatientData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class PatientCacheDataSourceImpl implements
        IPatientCacheDataSource {

    private AppDatabase database;
    private IPatientDao dao;


    public PatientCacheDataSourceImpl(AppDatabase database) {
        this.database = database;
        dao = database.getPatientDao();
    }

    @Override
    public Flowable<List<PatientData>> getPatients() {
        return dao.getAllPatients();
    }

    @Override
    public Flowable<List<PatientData>> setPatients(List<PatientData> patientList) {
        dao.clear();
        dao.saveAllPatients(patientList);
        return dao.getAllPatients();
    }

    @Override
    public Single<PatientData> getPatient(Long patientId) {
        return dao.getPatient(patientId);
    }

    @Override
    public Completable addPatient(PatientData patient) {
       return dao.savePatient(patient);
    }

    @Override
    public Completable editPatient(PatientData patient) {
        return dao.updatePatient(patient);
    }

    @Override
    public Completable deletePatient(Long patientId) {
        return dao.deletePatient(patientId);
    }

    @Override
    public Completable clearAllPatient() {
        return dao.clear();
    }
}
