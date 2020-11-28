package com.example.dailysmarts.ui.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dailysmarts.R;
import com.example.dailysmarts.data.database.QuoteDBService;
import com.example.dailysmarts.databinding.FragmentMyQuotesBinding;
import com.example.dailysmarts.ui.adapters.MyQuotesAdapter;

import javax.inject.Inject;

public class TabMyQuotes extends BaseFragment<FragmentMyQuotesBinding> {

    @Inject
    QuoteDBService quoteDBService;
    @Inject MyQuotesAdapter adapter;

    @Inject
    public TabMyQuotes(QuoteDBService quoteDBService, MyQuotesAdapter adapter) {
        this.quoteDBService = quoteDBService;
        this.adapter = adapter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_quotes;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {
        quoteDBService = new QuoteDBService(getContext());

        binding.rvQuotes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new MyQuotesAdapter();
        binding.rvQuotes.setAdapter(adapter);

        loadStudents();

    }

    private void loadStudents() {
        quoteDBService.getAllQuotes(data -> adapter.setQuotes(data));
    }

    public interface OnFragmentInteractionListener {

    }
}
