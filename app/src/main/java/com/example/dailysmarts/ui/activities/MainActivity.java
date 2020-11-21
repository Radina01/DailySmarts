package com.example.dailysmarts.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dailysmarts.R;
import com.example.dailysmarts.ui.adapters.TabAdapter;
import com.example.dailysmarts.ui.fragments.TabDailyQuote;
import com.example.dailysmarts.ui.fragments.TabMyQuotes;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    TabDailyQuote tabDailyQuote;

    @Inject
    TabMyQuotes tabMyQuotes;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated() {
        tabManager();
    }

    private void tabManager() {
        TabLayout tabLayout = findViewById(R.id.tabs);
        TabAdapter fragmentAdapter = new TabAdapter(this.getSupportFragmentManager(), getLifecycle(), tabDailyQuote, tabMyQuotes);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setAdapter(fragmentAdapter);
        viewPager2.setUserInputEnabled(false);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            checkPositionAndSetText(position, tab);
        });
        mediator.attach();
    }

    private void checkPositionAndSetText(Integer position, TabLayout.Tab tab){
        if (position == 0) {
            tab.setText("Daily Quotes");
        } else {
            tab.setText("My quotes");
        }
    }
}

