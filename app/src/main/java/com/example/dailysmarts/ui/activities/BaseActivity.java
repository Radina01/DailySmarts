package com.example.dailysmarts.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.dailysmarts.R;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(getLayoutRes());
        onViewCreated();
    }

    protected abstract int getLayoutRes();

    protected abstract void onViewCreated();

}
