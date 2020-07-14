package com.me.data.settings.datasource.local;

import android.content.SharedPreferences;

import com.me.data.base.AppDatabase;
import com.me.data.base.SharedPreferenceHelper;
import com.me.data.patient.datasource.IPatientCacheDataSource;
import com.me.data.patient.datasource.local.dao.IPatientDao;
import com.me.data.patient.model.PatientData;
import com.me.data.settings.datasource.ISettingsCacheDataSource;
import com.me.data.settings.model.Constants;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static com.me.data.settings.model.Constants.DEFAULT_MAX_PATIENTS;

public class SettingsCacheDataSourceImpl implements
        ISettingsCacheDataSource {

    private SharedPreferences sharedPrefs;


    public SettingsCacheDataSourceImpl(SharedPreferences sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
    }


    @Override
    public void setMaxNumberPatients(int maxNum) {
        SharedPreferenceHelper.setSharedPreferenceInt(sharedPrefs,
                Constants.MAX_PATIENTS_SHARED_PREF, maxNum);
    }

    @Override
    public void setCurrUserName(String userName) {
        SharedPreferenceHelper.setSharedPreferenceString(sharedPrefs,
                Constants.USER_NAME_SHARED_PREF, userName);

    }

    @Override
    public void incrementPatientsCount() {
        int currNum = SharedPreferenceHelper.getSharedPreferenceInt(sharedPrefs,
                Constants.CURR_PATIENTS_SHARED_PREF, 0);

        SharedPreferenceHelper.setSharedPreferenceInt(sharedPrefs,
                Constants.CURR_PATIENTS_SHARED_PREF, ++currNum);
    }

    @Override
    public int getMaxNumberPatients() {
        return SharedPreferenceHelper.getSharedPreferenceInt(sharedPrefs,
                Constants.MAX_PATIENTS_SHARED_PREF, 0);
    }

    @Override
    public String getCurrUserName( ) {
        return SharedPreferenceHelper.getSharedPreferenceString(sharedPrefs,
                Constants.USER_NAME_SHARED_PREF, "");
    }

    @Override
    public int getPatientsCount() {
        return SharedPreferenceHelper.getSharedPreferenceInt(sharedPrefs,
                Constants.CURR_PATIENTS_SHARED_PREF, 0);
    }

    public void setPatientsList(String patientList){
        SharedPreferenceHelper.setSharedPreferenceString(sharedPrefs,
                Constants.PATIENTS_LIST_SHARED_PREF, patientList);
    }

    public String getPatientsList(){
        return  SharedPreferenceHelper.getSharedPreferenceString(sharedPrefs,
                Constants.PATIENTS_LIST_SHARED_PREF, "");
    }


    @Override
    public void initNumberPatients() {
        int mx = getMaxNumberPatients();
        if (mx == 0)
            setMaxNumberPatients(DEFAULT_MAX_PATIENTS);
    }

    @Override
    public void clearAllData() {
        SharedPreferenceHelper.clearSharedPreference(sharedPrefs);
    }
}
