package com.example.dailysmarts.di.module;

import com.example.dailysmarts.DailySmartsApplication;
import com.example.dailysmarts.data.database.DailyQuoteDBService;
import com.example.dailysmarts.data.database.QuoteDBService;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    QuoteDBService provideQuoteService(DailySmartsApplication application){
        return new QuoteDBService(application);
    }
    @Provides
    DailyQuoteDBService provideDailyQuoteService(DailySmartsApplication application){
        return new DailyQuoteDBService(application);
    }
}
