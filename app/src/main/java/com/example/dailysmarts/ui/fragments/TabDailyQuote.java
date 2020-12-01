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
import com.example.dailysmarts.data.database.Quote;
import com.example.dailysmarts.data.database.QuoteDBService;
import com.example.dailysmarts.databinding.FragmentDailyQuoteBinding;
import com.example.dailysmarts.ui.activities.MainActivity;

import javax.inject.Inject;

public class TabDailyQuote extends BaseFragment<FragmentDailyQuoteBinding> implements TabDailyQuoteContract.ViewListener {

    @Inject
    TabDailyQuoteContract.PresenterListener presenterListener;
    @Inject
    QuoteDBService dbService;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_daily_quote;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        presenterListener.setViewListener(this);
        setOnClickListeners();
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

    private void setOnClickListeners() {
        binding.btnSave.setOnClickListener(v -> saveQuoteInDatabase());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.drawer_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void reload() {
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
        binding.btnSave.setBackgroundResource(R.drawable.full_heart);
    }

    @Override
    public void saveQuoteInDatabase() {
        //String author is used for checking if author is unknown(if the Api responses with empty string for author)
        String author = binding.txtQuote.getText().toString().equals("") ? "unknown" : binding.txtQuote.getText().toString();

        Quote quote = new Quote(author, binding.txtAuthor.getText().toString());
        dbService.addQuote(quote);


        binding.btnSave.setBackgroundResource(R.drawable.full_heart);
    }
}
