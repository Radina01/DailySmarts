package com.example.dailysmarts.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.example.dailysmarts.R;
import com.example.dailysmarts.databinding.FragmentFavouriteQuotesBinding;

public class TabMyQuotes extends BaseFragment<FragmentFavouriteQuotesBinding>{
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_quotes;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {

    }
}
