package com.me.presentation.patient.di;

import com.me.presentation.patient.view.fragment.PatientsListFragment;
import com.me.presentation.setting.di.SettingsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PatientsFragmentBuilder {

    @ContributesAndroidInjector(modules = {PatientsListModule.class, SettingsModule.class})
    abstract PatientsListFragment bindPatientsListFragment();

}
