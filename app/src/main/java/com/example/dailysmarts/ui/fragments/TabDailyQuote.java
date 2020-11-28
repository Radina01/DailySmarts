package com.example.dailysmarts.ui.fragments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.dailysmarts.R;
import com.example.dailysmarts.core.contracts.TabDailyQuoteContract;
import com.example.dailysmarts.data.api.Api;
import com.example.dailysmarts.databinding.FragmentDailyQuoteBinding;
import com.example.dailysmarts.ui.activities.MainActivity;

import javax.inject.Inject;

public class TabDailyQuote extends BaseFragment<FragmentDailyQuoteBinding> implements TabDailyQuoteContract.ViewListener {

    @Inject TabDailyQuoteContract.PresenterListener presenterListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_daily_quote;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        presenterListener.setViewListener(this);
    }

    @Inject
    public TabDailyQuote() {
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            presenterListener.onRefreshButtonClicked();
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

    @Override
    public void generateNewQuote() {
        Api.getInstance().getRandomEngQuote(new Api.ApiListener() {

            @Override
            public void onQuoteReceived(String quote, String author) {
                binding.txtQuote.setText(quote);
                binding.txtAuthor.setText(author);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "Something happened", Toast.LENGTH_LONG).show();
            }
        });
    }
}
