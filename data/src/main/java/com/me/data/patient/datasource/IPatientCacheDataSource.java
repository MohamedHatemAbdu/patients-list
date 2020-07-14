package com.me.data.patient.datasource;

import com.me.data.patient.model.PatientData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IPatientCacheDataSource {

    public Flowable<List<PatientData>> getPatients();

    public Flowable<List<PatientData>> setPatients(List<PatientData> patientList);

    public Single<PatientData> getPatient(Long patientId);

    public Completable addPatient(PatientData patient);

    public Completable editPatient(PatientData patient);

    public Completable deletePatient(Long patientId);

    public Completable clearAllPatient();
}
