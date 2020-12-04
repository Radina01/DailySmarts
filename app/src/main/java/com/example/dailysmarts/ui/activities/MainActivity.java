package com.example.dailysmarts.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.example.dailysmarts.di.ApplicationComponent;
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
        if(!isNetworkAvailable()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please check your internet connection and try again")
                    .setTitle("Not connected").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    recreate();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            tabManager();
            SwipeRefreshLayout pullToRefresh = findViewById(R.id.swipeRefresh);
            pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshData(); // your code
                    pullToRefresh.setRefreshing(false);
                }
            });
        }
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


    void refreshData() {
        tabMyQuotes.reload();
        tabDailyQuote.reload();
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();

    }
}

