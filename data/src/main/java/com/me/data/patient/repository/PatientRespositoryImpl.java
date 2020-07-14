package com.me.data.patient.repository;

import com.me.data.patient.datasource.IPatientCacheDataSource;
import com.me.data.patient.model.mapper.PatientDataEntityMapper;
import com.me.data.patient.model.mapper.PatientEntityDataMapper;
import com.me.domain.patient.entity.PatientEntity;
import com.me.domain.patient.repository.IPatientRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class PatientRespositoryImpl implements IPatientRepository {

    private PatientEntityDataMapper patientEntityDataMapper;

    private PatientDataEntityMapper patientDataEntityMapper;
    private IPatientCacheDataSource patientCacheDataSource;

    public PatientRespositoryImpl(PatientEntityDataMapper patientEntityDataMapper, PatientDataEntityMapper patientDataEntityMapper, IPatientCacheDataSource patientCacheDataSource) {
        this.patientEntityDataMapper = patientEntityDataMapper;
        this.patientDataEntityMapper = patientDataEntityMapper;
        this.patientCacheDataSource = patientCacheDataSource;
    }

    @Override
    public Flowable<List<PatientEntity>> getPatients() {
        return patientCacheDataSource.getPatients()
                .map(patientDataEntityMapper::transform);
    }

    @Override
    public Single<PatientEntity> getCachedPatient(Long patientId) {
        return patientCacheDataSource.getPatient(patientId)
                .map(patientDataEntityMapper::transform);
    }

    @Override
    public Completable addPatient(PatientEntity patient) {
        return patientCacheDataSource.addPatient(patientEntityDataMapper.transform(patient));
    }

    @Override
    public Completable deletePatient(Long patientId) {
        return patientCacheDataSource.deletePatient(patientId);
    }

    @Override
    public Completable clearAllPatient() {
        return patientCacheDataSource.clearAllPatient();
    }
}
