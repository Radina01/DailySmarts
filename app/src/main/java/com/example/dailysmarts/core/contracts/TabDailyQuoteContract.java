package com.example.dailysmarts.core.contracts;

public interface TabDailyQuoteContract {

    interface ViewListener {
        void generateNewQuote();
        void saveQuoteInDatabase();
        void shareQuote();
    }

    interface PresenterListener {
        void setViewListener(ViewListener viewListener);
        void onRefreshButtonClicked();
        void onSaveButtonClicked();
        void onShareButtonClicked();
    }

}
