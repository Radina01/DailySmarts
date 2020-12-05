package com.example.dailysmarts.core.contracts;

public interface TabDailyQuoteContract {

    interface ViewListener {
        void generateNewQuote();
        void saveOrDeleteQuoteInDatabase();
        void shareQuote();
        void changeLanguage();
    }

    interface PresenterListener {
        void setViewListener(ViewListener viewListener);
        void onRefreshButtonClicked();
        void onLanguageButtonClicked();
        void onSaveButtonClicked();
        void onShareButtonClicked();
    }

}
