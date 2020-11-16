package com.example.dailysmarts.ui.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.dailysmarts.R;
import com.example.dailysmarts.databinding.FragmentDailyQuoteBinding;

public class TabDailyQuote extends BaseFragment<FragmentDailyQuoteBinding>{
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_daily_quote;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {

    }
}
