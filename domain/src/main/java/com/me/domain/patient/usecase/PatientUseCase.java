package com.me.domain.patient.usecase;

import com.me.domain.patient.entity.PatientEntity;
import com.me.domain.patient.repository.IPatientRepository;
import com.me.domain.settings.repository.ISettingsRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class PatientUseCase {

    private IPatientRepository patientRepository;
    private ISettingsRepository settingsRepository;

    public PatientUseCase(IPatientRepository patientRepository, ISettingsRepository settingsRepository) {
        this.patientRepository = patientRepository;
        this.settingsRepository = settingsRepository;
    }

    public Flowable<List<PatientEntity>> getPatients() {
        return patientRepository.getPatients();
    }

    public Single<PatientEntity> getCachedPatient(Long dataCreated) {
        return patientRepository.getCachedPatient(dataCreated);
    }


    public Flowable<List<PatientEntity>> addPatient(PatientEntity patient) {

        if (settingsRepository.hasReachedTheMaxNumberPatients())
            return Flowable.error(new Exception("Patient not added because of Number of Patients has reached the max"));
        else {
            settingsRepository.incrementPatientsCount();
            return patientRepository.addPatient(patient).andThen(getPatients()).map(new Function<List<PatientEntity>, List<PatientEntity>>() {
                @Override
                public List<PatientEntity> apply(List<PatientEntity> patientEntities) throws Exception {
                    settingsRepository.setPatientsList(patientEntities.toString());
                    return patientEntities;
                }
            });
        }
    }

    public Completable deletePatient(Long patient) {
        return patientRepository.deletePatient(patient);
    }

    public Completable clearAllPatient() {
        return patientRepository.clearAllPatient();
    }

}
