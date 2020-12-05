package com.example.dailysmarts.ui.fragments;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.dailysmarts.R;
import com.example.dailysmarts.core.contracts.TabDailyQuoteContract;
import com.example.dailysmarts.data.api.Api;
import com.example.dailysmarts.data.database.DailyQuote;
import com.example.dailysmarts.data.database.DailyQuoteDBService;
import com.example.dailysmarts.data.database.Quote;
import com.example.dailysmarts.data.database.QuoteDBService;
import com.example.dailysmarts.databinding.FragmentDailyQuoteBinding;
import com.example.dailysmarts.ui.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.inject.Inject;

public class TabDailyQuote extends BaseFragment<FragmentDailyQuoteBinding> implements TabDailyQuoteContract.ViewListener {

    @Inject
    TabDailyQuoteContract.PresenterListener presenterListener;

    @Inject
    DailyQuoteDBService dailyQuoteDBService;

    @Inject
    QuoteDBService dbService;

    private boolean isInEnglish = true;
    private boolean ifCurrentQuoteExistsInDb;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_daily_quote;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        setIfCurrentQuoteExistsInDb();
        presenterListener.setViewListener(this);
        showEnglishQuote();
        setOnClickListeners();
    }

    @Override
    public void onResume() {
        super.onResume();
        setIfCurrentQuoteExistsInDb();
    }

    private String getDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
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
        if (item.getItemId() == R.id.action_en_ru) {
            presenterListener.onLanguageButtonClicked();
            reload();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void setOnClickListeners() {
        binding.btnShare.setOnClickListener(v -> presenterListener.onShareButtonClicked());
        binding.btnSave.setOnClickListener(v -> presenterListener.onSaveButtonClicked());
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

            @SuppressLint("SetTextI18n")
            @Override
            public void onQuoteReceived(String quote, String author) {

                dailyQuoteDBService.getAllQuotes(new QuoteDBService.DataListener<List<DailyQuote>>() {
                    @Override
                    public void onData(List<DailyQuote> data) {
                        if (!data.isEmpty()) {
                            for (int i = 0; i < data.size(); i++) {
                                dailyQuoteDBService.deleteQuote(data.get(i));
                            }
                            DailyQuote dailyQuote = new DailyQuote(quote, author, getDate());
                            dailyQuoteDBService.addQuote(dailyQuote);
                            binding.txtQuote.setText(dailyQuote.getQuoteText());
                            binding.txtAuthor.setText(dailyQuote.getQuoteAuthor());
                        } else {
                            binding.txtQuote.setText(quote);
                            binding.txtAuthor.setText(author);
                            DailyQuote dailyQuote = new DailyQuote(String.valueOf(binding.txtQuote.getText()), String.valueOf(binding.txtAuthor.getText()), getDate());
                            dailyQuoteDBService.addQuote(dailyQuote);
                        }
                        setIfCurrentQuoteExistsInDb();
                    }
                });
                Toast.makeText(getContext(), "Generated new quotes.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "Something happened", Toast.LENGTH_LONG).show();
            }
        });
        binding.btnSave.setBackgroundResource(R.drawable.empty_heart);
    }

    public void generateNewRussianQuote() {
        Api.getInstance().getRandomRusQuote(new Api.ApiListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onQuoteReceived(String quote, String author) {

                dailyQuoteDBService.getAllQuotes(new QuoteDBService.DataListener<List<DailyQuote>>() {
                    @Override
                    public void onData(List<DailyQuote> data) {
                        if (data.size() > 1) {
                            DailyQuote dailyQuote = new DailyQuote(quote, author, getDate());
                            dailyQuoteDBService.addQuote(dailyQuote);
                            binding.txtQuote.setText(dailyQuote.getQuoteText());
                            binding.txtAuthor.setText(dailyQuote.getQuoteAuthor());
                        } else {
                            binding.txtQuote.setText(quote);
                            binding.txtAuthor.setText(author);
                            DailyQuote dailyQuote = new DailyQuote(String.valueOf(binding.txtQuote.getText()), String.valueOf(binding.txtAuthor.getText()), getDate());
                            dailyQuoteDBService.addQuote(dailyQuote);
                        }
                        setIfCurrentQuoteExistsInDb();
                    }
                });
            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "Something happened", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void changeLanguage() {
        Toast.makeText(getContext(), "Language changed!", Toast.LENGTH_SHORT).show();
        if (isInEnglish) {
            showRussianQuote();
        } else {
            showEnglishQuote();
        }
        isInEnglish = !isInEnglish;
    }

    public void deleteQuote() {
        dbService.deleteQuoteByQuoteText(binding.txtQuote.getText().toString());
        binding.btnSave.setBackgroundResource(R.drawable.empty_heart);
    }

    private void showRussianQuote() {
        dailyQuoteDBService = new DailyQuoteDBService(getContext());
        dailyQuoteDBService.getAllQuotes(new QuoteDBService.DataListener<List<DailyQuote>>() {
            @Override
            public void onData(List<DailyQuote> data) {
                if (data.size() > 1) {
                    if (data.get(1).getQuoteDate().equalsIgnoreCase(getDate())) {
                        binding.txtQuote.setText(data.get(1).getQuoteText());
                        binding.txtAuthor.setText(data.get(1).getQuoteAuthor());
                    } else {
                        generateNewRussianQuote();
                    }
                } else {
                    generateNewRussianQuote();
                }
                setIfCurrentQuoteExistsInDb();
            }
        });
    }

    private void showEnglishQuote() {
        dailyQuoteDBService = new DailyQuoteDBService(getContext());
        dailyQuoteDBService.getAllQuotes(new QuoteDBService.DataListener<List<DailyQuote>>() {
            @Override
            public void onData(List<DailyQuote> data) {
                if (!data.isEmpty()) {
                    if (data.get(0).getQuoteDate().equalsIgnoreCase(getDate())) {
                        binding.txtQuote.setText(data.get(0).getQuoteText());
                        binding.txtAuthor.setText(data.get(0).getQuoteAuthor());
                    } else {
                        generateNewQuote();
                    }
                } else {
                    generateNewQuote();
                }
                setIfCurrentQuoteExistsInDb();
            }
        });
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void saveOrDeleteQuoteInDatabase() {

        if (ifCurrentQuoteExistsInDb) {
            deleteQuote();
            ifCurrentQuoteExistsInDb = false;
        } else {

            //String author is used for checking if author is unknown(if the Api responses with empty string for author)
            String author = binding.txtQuote.getText().toString().equals("") ? "unknown" : binding.txtQuote.getText().toString();

            Quote quote = new Quote(author, binding.txtAuthor.getText().toString());
            dbService.addQuote(quote);

            binding.btnSave.setBackgroundResource(R.drawable.full_heart);
            ifCurrentQuoteExistsInDb = true;
        }
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

    private void checkIfExists(Boolean ifExists) {
        this.ifCurrentQuoteExistsInDb = (boolean) ifExists;
        if (ifExists) {
            binding.btnSave.setBackgroundResource(R.drawable.full_heart);
        } else {
            binding.btnSave.setBackgroundResource(R.drawable.empty_heart);
        }
    }

    private void setIfCurrentQuoteExistsInDb() {
        dbService.ifExists(this::checkIfExists, binding.txtQuote.getText().toString());
    }

}
