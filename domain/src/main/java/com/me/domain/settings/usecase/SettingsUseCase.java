package com.me.domain.settings.usecase;

import com.me.domain.patient.entity.PatientEntity;
import com.me.domain.patient.repository.IPatientRepository;
import com.me.domain.settings.repository.ISettingsRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class SettingsUseCase {

    private ISettingsRepository settingsRepository;

    public SettingsUseCase(ISettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public void setMaxNumberPatients(int maxNum) {
        settingsRepository.setMaxNumberPatients(maxNum);
    }

    public void setCurrUserName(String userName) {
        settingsRepository.setCurrUserName(userName);

    }

    public void incrementPatientsCount() {
        settingsRepository.incrementPatientsCount();


    }

    public int getMaxNumberPatients() {
        return settingsRepository.getMaxNumberPatients();
    }

    public String getCurrUserName() {
        return settingsRepository.getCurrUserName();
    }

    public int getPatientsCount() {
        return settingsRepository.getPatientsCount();
    }

    public boolean hasReachedTheMaxNumberPatients(){
        return settingsRepository.hasReachedTheMaxNumberPatients();
    }

    public void initNumberPatients() {
        settingsRepository.initNumberPatients();

    }

    public void clearAllData() {
        settingsRepository.clearAllData();
    }
    public String getPatientsList() {
        return settingsRepository.getPatientsList();
    }

}
