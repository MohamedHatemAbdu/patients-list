package com.me.domain.patient.repository;

import com.me.domain.patient.entity.PatientEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IPatientRepository {
    public Flowable<List<PatientEntity>> getPatients();
    
    public Single<PatientEntity> getCachedPatient(Long patientId);

    public Completable addPatient(PatientEntity patient);

    public Completable deletePatient(Long dateCreated);

    public Completable clearAllPatient();
}
