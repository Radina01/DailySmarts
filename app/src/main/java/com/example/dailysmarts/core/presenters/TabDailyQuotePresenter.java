package com.example.dailysmarts.core.presenters;

import com.example.dailysmarts.core.contracts.TabDailyQuoteContract;

public class TabDailyQuotePresenter implements TabDailyQuoteContract.PresenterListener{

    private TabDailyQuoteContract.ViewListener viewListener;

    @Override
    public void setViewListener(TabDailyQuoteContract.ViewListener viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void onRefreshButtonClicked() {
        viewListener.generateNewQuote();
    }

    @Override
    public void onSaveButtonClicked() {
        viewListener.saveQuoteInDatabase();
    }

}
