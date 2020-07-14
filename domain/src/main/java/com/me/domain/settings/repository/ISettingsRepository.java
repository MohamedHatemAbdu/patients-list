package com.me.domain.settings.repository;

public interface ISettingsRepository {
    public void setMaxNumberPatients(int maxNum);

    public void setCurrUserName(String userName);

    public void incrementPatientsCount();

    public int getMaxNumberPatients();

    public String getCurrUserName();

    public int getPatientsCount();

    public void setPatientsList(String patientList);

    public String getPatientsList();
    public boolean hasReachedTheMaxNumberPatients();

    public void initNumberPatients() ;

    public void clearAllData() ;
}
