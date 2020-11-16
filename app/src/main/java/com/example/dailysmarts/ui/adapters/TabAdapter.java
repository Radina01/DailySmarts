package com.example.dailysmarts.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dailysmarts.ui.fragments.TabDailyQuote;
import com.example.dailysmarts.ui.fragments.TabMyQuotes;

public class TabAdapter extends FragmentStateAdapter {


    public TabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new TabDailyQuote();
            case 1: return new TabMyQuotes();
            default: throw new IllegalArgumentException("There are not that much fragments");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
