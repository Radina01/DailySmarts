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

import javax.inject.Inject;

public class TabDailyQuote extends BaseFragment<FragmentDailyQuoteBinding> implements TabDailyQuoteContract.ViewListener {

    @Inject
    TabDailyQuoteContract.PresenterListener presenterListener;

    @Inject
    DailyQuoteDBService dailyQuoteDBService;

    @Inject
    QuoteDBService dbService;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_daily_quote;
    }

    private List<DailyQuote> dailyQuoteList = new ArrayList<>();
    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        presenterListener.setViewListener(this);
        dailyQuoteDBService = new DailyQuoteDBService(getContext());
        dailyQuoteDBService.getAllQuotes(new QuoteDBService.DataListener<List<DailyQuote>>() {
            @Override
            public void onData(List<DailyQuote> data) {
                if (data != null){
                    if (data.get(0).getQuoteDate().equalsIgnoreCase(getDate())) {
                        binding.txtQuote.setText(data.get(0).getQuoteText());
                        binding.txtAuthor.setText(data.get(0).getQuoteAuthor());
                    }
                    else {
                        generateNewQuote();
                    }
                }
                else {
                    generateNewQuote();
                }
            }
        });
//        dailyQuoteDBService.getAllQuotes(data -> getQuotesList(data));
//        if (dailyQuoteList.size() > 0) {
//            binding.txtQuote.setText(dailyQuoteList.get(0).getQuoteText());
//            binding.txtAuthor.setText(dailyQuoteList.get(0).getQuoteAuthor());
//        }
//        else{
//            generateNewQuote();
//
//        }
        setOnClickListeners();
    }
    private void getQuotesList(List<DailyQuote> quoteList){
        this.dailyQuoteList = quoteList;
    }
    private String getDate(){
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
        return super.onOptionsItemSelected(item);

    }

    private void setOnClickListeners() {
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

            @Override
            public void onQuoteReceived(String quote, String author) {
                dailyQuoteDBService.getAllQuotes(new QuoteDBService.DataListener<List<DailyQuote>>() {
                    @Override
                    public void onData(List<DailyQuote> data) {
                        if (data != null){
                            dailyQuoteDBService.deleteQuote(data.get(0));
                            DailyQuote dailyQuote = new DailyQuote(quote, author, getDate());
                            dailyQuoteDBService.addQuote(dailyQuote);
                            binding.txtQuote.setText(dailyQuote.getQuoteText());
                            binding.txtAuthor.setText(dailyQuote.getQuoteAuthor());
                        }
                        else {
                            binding.txtQuote.setText(quote);
                            binding.txtAuthor.setText(author);
                            DailyQuote dailyQuote = new DailyQuote(String.valueOf(binding.txtQuote.getText()), String.valueOf(binding.txtAuthor.getText()), getDate());
                            dailyQuoteDBService.addQuote(dailyQuote);
                        }
                    }
                });
                binding.txtQuote.setText(quote);
                binding.txtAuthor.setText(author);
                DailyQuote dailyQuote = new DailyQuote(String.valueOf(binding.txtQuote.getText()), String.valueOf(binding.txtAuthor.getText()), getDate());
                dailyQuoteDBService.addQuote(dailyQuote);
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
    public void getQuote(){
        Api.getInstance().getRandomEngQuote(new Api.ApiListener() {
            @Override
            public void onQuoteReceived(String quote, String author) {
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                DailyQuote dailyQuote = new DailyQuote(quote, author, formattedDate);
                dailyQuoteDBService.addQuote(dailyQuote);
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
