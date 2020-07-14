package com.me.presentation.setting.di;

import android.content.SharedPreferences;

import com.me.data.base.AppDatabase;
import com.me.data.patient.datasource.IPatientCacheDataSource;
import com.me.data.patient.datasource.local.PatientCacheDataSourceImpl;
import com.me.data.settings.datasource.ISettingsCacheDataSource;
import com.me.data.settings.datasource.local.SettingsCacheDataSourceImpl;
import com.me.data.settings.repository.SettingsRepositoryImpl;
import com.me.domain.patient.repository.IPatientRepository;
import com.me.domain.patient.usecase.PatientUseCase;
import com.me.domain.settings.repository.ISettingsRepository;
import com.me.domain.settings.usecase.SettingsUseCase;
import com.me.presentation.patient.view.viewmodel.PatientsViewModel;
import com.me.presentation.setting.view.viewmodel.SettingsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {

    @Provides
    public ISettingsCacheDataSource provideISettingCacheDataSource(
            SharedPreferences sharedPreferences
    ) {
        return new SettingsCacheDataSourceImpl(sharedPreferences);
    }




    @Provides
    public ISettingsRepository provideSettingsRepository
            (ISettingsCacheDataSource settingsCacheDataSource) {
        return new SettingsRepositoryImpl(
                settingsCacheDataSource
        );
    }

    @Provides
    public SettingsUseCase provideSettingsUseCase(ISettingsRepository settingsRepository) {

        return new SettingsUseCase(settingsRepository);
    }

    @Provides
    public SettingsViewModel provideSettingsViewModel(
            SettingsUseCase settingsUseCase
    ) {
        return new SettingsViewModel(settingsUseCase);
    }


}
