package com.me.data.settings.datasource;

import com.me.data.patient.model.PatientData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface ISettingsCacheDataSource {


    public void setMaxNumberPatients(int maxNum);

    public void setCurrUserName(String userName);

    public void incrementPatientsCount();

    public int getMaxNumberPatients();

    public String getCurrUserName();

    public int getPatientsCount();

    public void setPatientsList(String patientList);

    public String getPatientsList();

    public void initNumberPatients();

    public void clearAllData();


}
