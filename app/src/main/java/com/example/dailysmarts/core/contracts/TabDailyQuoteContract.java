package com.example.dailysmarts.core.contracts;

public interface TabDailyQuoteContract {

    interface ViewListener {
        void generateNewQuote();
    }

    interface PresenterListener {
        void setViewListener(ViewListener viewListener);
        void onRefreshButtonClicked();
    }

}
