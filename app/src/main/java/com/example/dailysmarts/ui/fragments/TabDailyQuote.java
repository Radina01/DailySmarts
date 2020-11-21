package com.example.dailysmarts.ui.fragments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import com.example.dailysmarts.R;
import com.example.dailysmarts.databinding.FragmentDailyQuoteBinding;

import javax.inject.Inject;

public class TabDailyQuote extends BaseFragment<FragmentDailyQuoteBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_daily_quote;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            reload();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.drawer_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void reload() {
        getLayoutRes();
    }
}
