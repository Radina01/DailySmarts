package com.example.dailysmarts.ui.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
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

        onClickShare();

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
        binding.btnSave.setOnClickListener(v -> presenterListener.onSaveButtonClicked());
        binding.btnShare.setOnClickListener(v -> shareQuote());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.drawer_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void reload() {
        getLayoutRes();
    }


    public void onClickShare(){
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }


    @Override
    public void generateNewQuote() {
        Api.getInstance().getRandomEngQuote(new Api.ApiListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onQuoteReceived(String quote, String author) {
                binding.txtQuote.setText("\"" + quote + "\"");
                binding.txtAuthor.setText(author);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "Something happened", Toast.LENGTH_LONG).show();
            }
        });
        binding.btnSave.setBackgroundResource(R.drawable.empty_heart);
    }

    @Override
    public void saveQuoteInDatabase() {
        //String author is used for checking if author is unknown(if the Api responses with empty string for author)
        String author = binding.txtQuote.getText().toString().equals("") ? "unknown" : binding.txtQuote.getText().toString();

        Quote quote = new Quote(author, binding.txtAuthor.getText().toString());
        dbService.addQuote(quote);


        binding.btnSave.setBackgroundResource(R.drawable.full_heart);
    }

    @Override
    public void shareQuote() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = binding.txtQuote.getText().toString() + "\n             -" + binding.txtAuthor.getText().toString();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Quote");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
