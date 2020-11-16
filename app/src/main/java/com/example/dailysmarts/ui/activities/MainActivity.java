package com.example.dailysmarts.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.dailysmarts.R;
import com.example.dailysmarts.ui.adapters.TabAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends BaseActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated() {

        TabLayout tabLayout = findViewById(R.id.tabs);
        TabAdapter fragmentAdapter = new TabAdapter(this.getSupportFragmentManager(), getLifecycle());

        ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setAdapter(fragmentAdapter);
        viewPager2.setUserInputEnabled(false);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            Log.e("ASDASD", "" + position);
            if (position == 0){
                tab.setText("Daily Quotes");
            }
            else {
                tab.setText("Favourite Quotes");
            }
        });
        mediator.attach();
    }
}
