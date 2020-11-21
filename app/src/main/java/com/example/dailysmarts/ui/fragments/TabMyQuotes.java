package com.example.dailysmarts.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dailysmarts.R;
import com.example.dailysmarts.data.database.QuoteService;
import com.example.dailysmarts.databinding.FragmentMyQuotesBinding;
import com.example.dailysmarts.ui.adapters.MyQuotesAdapter;

import javax.inject.Inject;

public class TabMyQuotes extends BaseFragment<FragmentMyQuotesBinding> {

    QuoteService quoteService;
    MyQuotesAdapter adapter;

    @Inject
    public TabMyQuotes(QuoteService quoteService, MyQuotesAdapter adapter) {
        this.quoteService = quoteService;
        this.adapter = adapter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_quotes;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {
        quoteService = new QuoteService(getContext());

        binding.rvQuotes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new MyQuotesAdapter();
        binding.rvQuotes.setAdapter(adapter);

        loadStudents();

    }

    private void loadStudents() {
        quoteService.getAllQuotes(data -> adapter.setQuotes(data));
    }
}
