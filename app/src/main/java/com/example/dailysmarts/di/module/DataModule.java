package com.example.dailysmarts.di.module;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.dailysmarts.DailySmartsApplication;
import com.example.dailysmarts.data.database.AppDatabase;
import com.example.dailysmarts.data.database.Database;
import com.example.dailysmarts.data.database.QuoteDao;
import com.example.dailysmarts.data.database.QuoteService;
import com.example.dailysmarts.ui.adapters.MyQuotesAdapter;
import com.example.dailysmarts.ui.adapters.TabAdapter;
import com.example.dailysmarts.ui.fragments.TabDailyQuote;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    QuoteService provideQuoteService(DailySmartsApplication application){
        return new QuoteService(application);
    }
}
