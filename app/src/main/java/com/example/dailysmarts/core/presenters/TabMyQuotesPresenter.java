package com.example.dailysmarts.core.presenters;

import com.example.dailysmarts.core.contracts.TabMyQuotesContract;

public class TabMyQuotesPresenter implements TabMyQuotesContract.PresenterListener {

    private TabMyQuotesContract.ViewListener viewListener;

    @Override
    public void setViewListener(TabMyQuotesContract.ViewListener viewListener) {
        this.viewListener = viewListener;
    }
}
