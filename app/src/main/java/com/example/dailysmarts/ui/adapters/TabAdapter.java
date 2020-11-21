package com.example.dailysmarts.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dailysmarts.ui.fragments.TabDailyQuote;
import com.example.dailysmarts.ui.fragments.TabMyQuotes;

import javax.inject.Inject;

public class TabAdapter extends FragmentStateAdapter {

    private TabDailyQuote provideTabDailyQuote;
    private TabMyQuotes provideTabMyQuotes;

    @Inject
    public TabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, TabDailyQuote tabDailyQuote, TabMyQuotes tabMyQuotes) {
        super(fragmentManager, lifecycle);
        this.provideTabDailyQuote = tabDailyQuote;
        this.provideTabMyQuotes = tabMyQuotes;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return provideTabMyQuotes;
            case 1:
                return provideTabDailyQuote;
            default:
                throw new IllegalArgumentException("There are not that much fragments");
        }
    }



    @Override
    public int getItemCount() {
        return 2;
    }
}
