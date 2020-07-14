package com.me.data.settings.repository;

import com.me.data.base.SharedPreferenceHelper;
import com.me.data.settings.datasource.ISettingsCacheDataSource;
import com.me.data.settings.model.Constants;
import com.me.domain.settings.repository.ISettingsRepository;

import static com.me.data.settings.model.Constants.DEFAULT_MAX_PATIENTS;

public class SettingsRepositoryImpl implements ISettingsRepository {

    private ISettingsCacheDataSource settingsCacheDataSource;

    public SettingsRepositoryImpl(ISettingsCacheDataSource settingsCacheDataSource) {
        this.settingsCacheDataSource = settingsCacheDataSource;
    }

    @Override
    public void setMaxNumberPatients(int maxNum) {
        settingsCacheDataSource.setMaxNumberPatients(maxNum);
    }

    @Override
    public void setCurrUserName(String userName) {
        settingsCacheDataSource.setCurrUserName(userName);

    }

    @Override
    public void incrementPatientsCount() {
        settingsCacheDataSource.incrementPatientsCount();

    }

    @Override
    public int getMaxNumberPatients() {
        return settingsCacheDataSource.getMaxNumberPatients();

    }

    @Override
    public String getCurrUserName() {
        return settingsCacheDataSource.getCurrUserName();
    }

    @Override
    public int getPatientsCount() {
        return settingsCacheDataSource.getPatientsCount();
    }

    public void setPatientsList(String patientList) {
        settingsCacheDataSource.setPatientsList(patientList);
    }

    public String getPatientsList() {
        return settingsCacheDataSource.getPatientsList();
    }

    @Override
    public boolean hasReachedTheMaxNumberPatients() {
        return getPatientsCount() == getMaxNumberPatients();
    }

    @Override
    public void initNumberPatients() {
        settingsCacheDataSource.initNumberPatients();

    }

    @Override
    public void clearAllData() {
        settingsCacheDataSource.clearAllData();
    }

}
