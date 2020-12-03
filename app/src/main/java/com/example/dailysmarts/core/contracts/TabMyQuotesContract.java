package com.example.dailysmarts.core.contracts;

public interface TabMyQuotesContract {

    interface ViewListener {
        void reloadResources();
    }

    interface PresenterListener {
        void setViewListener(ViewListener viewListener);

        void reloadResources();
    }
}
