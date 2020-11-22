package com.example.dailysmarts.di;

import android.app.Application;

import com.example.dailysmarts.DailySmartsApplication;
import com.example.dailysmarts.di.module.ActivityModule;
import com.example.dailysmarts.di.module.AppModule;
import com.example.dailysmarts.di.module.ContractsModule;
import com.example.dailysmarts.di.module.DataModule;
import com.example.dailysmarts.di.module.FragmentsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        DataModule.class,
        ActivityModule.class,
        FragmentsModule.class,
        ContractsModule.class,
        AppModule.class
})
public interface ApplicationComponent {

    void inject(DailySmartsApplication dailySmartsApplication);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder applicationBind(DailySmartsApplication dailySmartsApplication);


        ApplicationComponent build();
    }
}
