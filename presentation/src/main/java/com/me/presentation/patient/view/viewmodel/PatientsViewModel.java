package com.me.presentation.patient.view.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.me.domain.patient.entity.PatientEntity;
import com.me.domain.patient.usecase.PatientUseCase;
import com.me.presentation.base.model.Resource;
import com.me.presentation.base.utils.LiveDataExtensions;
import com.me.presentation.base.viewmodel.BaseViewModel;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class PatientsViewModel extends BaseViewModel {

    private PatientUseCase patientUseCase;
    private MutableLiveData<Resource<List<PatientEntity>>> ldPatientsList = new MutableLiveData<>();

    public MutableLiveData<Resource<List<PatientEntity>>> getLdPatientsList() {
        return ldPatientsList;
    }


    public PatientsViewModel(PatientUseCase patientUseCase) {
        this.patientUseCase = patientUseCase;
    }

    public void getPatientList() {
        compositeDisposable.add(
                patientUseCase.getPatients()
                        .doOnSubscribe(subscription -> {
                            LiveDataExtensions.setLoading(ldPatientsList);
                        }).subscribeOn(Schedulers.io())
                        .subscribe(patientEntities -> {
                                    LiveDataExtensions.setSuccess(ldPatientsList, patientEntities);
                                },
                                throwable -> {
                                    LiveDataExtensions.setError(ldPatientsList, throwable.getMessage());

                                })
        );

    }

    public void addPatient(PatientEntity patient) {
        compositeDisposable.add(patientUseCase.addPatient(patient)
                .doOnSubscribe(subscription -> {
                    LiveDataExtensions.setLoading(ldPatientsList);
                }).subscribeOn(Schedulers.io())
                .subscribe(patientEntities -> {
                            LiveDataExtensions.setSuccess(ldPatientsList, patientEntities);
                        },
                        throwable -> {
                            LiveDataExtensions.setError(ldPatientsList, throwable.getMessage());

                        }));

    }

    public void clearAll() {
        compositeDisposable.add(
                patientUseCase.clearAllPatient()
                        .doOnSubscribe(subscription -> {
                        }).subscribeOn(Schedulers.io())
                        .subscribe()
        );

    }
}
