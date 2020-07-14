package com.me.presentation.base.di;

import android.app.Application;

import com.me.presentation.base.App;
import com.me.presentation.patient.di.PatientsFragmentBuilder;
import com.me.presentation.setting.di.SettingsFragmentBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class, PatientsFragmentBuilder.class,
        SettingsFragmentBuilder.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }
}
