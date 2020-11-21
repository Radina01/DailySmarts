package com.example.dailysmarts.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.dailysmarts.DailySmartsApplication;
import com.example.dailysmarts.data.database.AppDatabase;
import com.example.dailysmarts.data.database.Database;
import com.example.dailysmarts.data.database.QuoteDao;
import com.example.dailysmarts.ui.adapters.MyQuotesAdapter;
import com.example.dailysmarts.ui.adapters.TabAdapter;
import com.example.dailysmarts.ui.fragments.TabDailyQuote;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    private AppDatabase appDatabase;

    
    public DataModule(Application mApplication) {
        appDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, "demo-db").build();
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    QuoteDao providesQuoteDao(AppDatabase appDatabase) {
        return appDatabase.quoteDao();
    }



}
