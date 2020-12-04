package com.example.dailysmarts.core.contracts;

public interface TabMyQuotesContract {

    interface ViewListener {
    }

    interface PresenterListener {
        void setViewListener(ViewListener viewListener);
        void onNextClicked();
    }
}
