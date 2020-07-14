package com.me.presentation.setting.view.viewmodel;

import com.me.domain.settings.usecase.SettingsUseCase;
import com.me.presentation.base.viewmodel.BaseViewModel;

public class SettingsViewModel extends BaseViewModel {

    private SettingsUseCase settingsUseCase;

    public SettingsViewModel(SettingsUseCase settingsUseCase) {
        this.settingsUseCase = settingsUseCase;
    }

    public void setCurrUserName(String userName) {
        settingsUseCase.setCurrUserName(userName);
    }

    public void setMaxNumberPatients(int maxNumberPatients) {
        settingsUseCase.setMaxNumberPatients(maxNumberPatients);
    }


    public int getPatientsCount() {
        return settingsUseCase.getPatientsCount();
    }

    public int getMaxNumberPatients() {
        return settingsUseCase.getMaxNumberPatients();
    }

    public String getCurrUserName() {
        return settingsUseCase.getCurrUserName();
    }

    public void initNumberPatients() {
        settingsUseCase.initNumberPatients();

    }


    public void clearAllData() {
        settingsUseCase.clearAllData();
    }

    public String getPatientsList() {
        return settingsUseCase.getPatientsList();
    }


}
