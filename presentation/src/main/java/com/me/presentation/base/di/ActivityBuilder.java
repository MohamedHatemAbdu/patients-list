package com.me.presentation.base.di;

import com.me.presentation.base.view.activity.MainActivity;
import com.me.presentation.patient.di.PatientsListModule;
import com.me.presentation.setting.di.SettingsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {PatientsListModule.class, SettingsModule.class})
    abstract public MainActivity bindMainActivity();
}
