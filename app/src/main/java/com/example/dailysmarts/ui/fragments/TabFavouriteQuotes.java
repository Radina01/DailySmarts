package com.example.dailysmarts.ui.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.dailysmarts.R;
import com.example.dailysmarts.databinding.FragmentFavouriteQuotesBinding;

public class TabFavouriteQuotes extends BaseFragment<FragmentFavouriteQuotesBinding>{
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_favourite_quotes;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {

    }
}
