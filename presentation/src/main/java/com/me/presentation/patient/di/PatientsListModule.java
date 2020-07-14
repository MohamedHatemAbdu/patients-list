package com.me.presentation.patient.di;

import com.me.data.base.AppDatabase;
import com.me.data.patient.datasource.IPatientCacheDataSource;
import com.me.data.patient.datasource.local.PatientCacheDataSourceImpl;
import com.me.data.patient.model.mapper.PatientDataEntityMapper;
import com.me.data.patient.model.mapper.PatientEntityDataMapper;
import com.me.data.patient.repository.PatientRespositoryImpl;
import com.me.domain.patient.repository.IPatientRepository;
import com.me.domain.patient.usecase.PatientUseCase;
import com.me.domain.settings.repository.ISettingsRepository;
import com.me.presentation.patient.view.adapter.PatientsListAdapter;
import com.me.presentation.patient.view.viewmodel.PatientsViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PatientsListModule {


    @Provides
    public IPatientCacheDataSource providePatientCacheDataSource(
            AppDatabase appDatabase
    ) {
        return new PatientCacheDataSourceImpl(appDatabase);
    }


    @Provides
    public PatientEntityDataMapper providePatientEntityDataMapper() {
        return new PatientEntityDataMapper();
    }

    @Provides
    public PatientDataEntityMapper providePatientDataEntityMapper() {
        return new PatientDataEntityMapper();
    }

    @Provides
    public IPatientRepository providePatientRepository
            (PatientEntityDataMapper patientEntityDataMapper,
             PatientDataEntityMapper patientDataEntityMapper,
             IPatientCacheDataSource patientCacheDataSource
            ) {
        return new PatientRespositoryImpl(
                patientEntityDataMapper,
                patientDataEntityMapper,
                patientCacheDataSource
        );
    }


    @Provides
    public PatientUseCase providePatientUseCase(IPatientRepository patientRepository, ISettingsRepository settingsRepository) {

        return new PatientUseCase(patientRepository, settingsRepository);
    }

    @Provides
    public PatientsViewModel providePatientsListViewModel(
            PatientUseCase patientUseCase
    ) {
        return new PatientsViewModel(patientUseCase);
    }

    @Provides
    public PatientsListAdapter providePatientsLogListAdapter() {
        return new PatientsListAdapter();
    }

}
