package com.me.presentation.setting.di;

import com.me.presentation.patient.di.PatientsListModule;
import com.me.presentation.setting.view.fragment.UsersSettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SettingsFragmentBuilder {
    @ContributesAndroidInjector(modules = {SettingsModule.class,})
    abstract UsersSettingsFragment bindUsersSettingsFragment();


}
