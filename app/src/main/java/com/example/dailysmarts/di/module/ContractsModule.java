package com.example.dailysmarts.di.module;

import com.example.dailysmarts.core.contracts.TabDailyQuoteContract;
import com.example.dailysmarts.core.contracts.TabMyQuotesContract;
import com.example.dailysmarts.core.presenters.TabDailyQuotePresenter;
import com.example.dailysmarts.core.presenters.TabMyQuotesPresenter;
import com.example.dailysmarts.ui.fragments.TabMyQuotes;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContractsModule {

    @Singleton
    @Provides
    public TabDailyQuoteContract.PresenterListener provideTabDailyQuotePresenter() {
        return new TabDailyQuotePresenter();
    }

    @Singleton
    @Provides
    public TabMyQuotesContract.PresenterListener provideTabMyQuotesPresenter() {
        return new TabMyQuotesPresenter();
    }
}
