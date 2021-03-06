package com.example.dailysmarts.ui.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dailysmarts.R;
import com.example.dailysmarts.core.contracts.TabDailyQuoteContract;
import com.example.dailysmarts.core.contracts.TabMyQuotesContract;
import com.example.dailysmarts.data.database.QuoteDBService;

import com.example.dailysmarts.databinding.FragmentMyQuotesBinding;
import com.example.dailysmarts.ui.adapters.MyQuotesAdapter;

import javax.inject.Inject;

public class TabMyQuotes extends BaseFragment<FragmentMyQuotesBinding> implements TabMyQuotesContract.ViewListener{

    @Inject
    QuoteDBService quoteDBService;
    private MyQuotesAdapter adapter;

    @Inject
    TabMyQuotesContract.PresenterListener presenterListener;

    @Inject
    public TabMyQuotes(QuoteDBService quoteDBService) {
        this.quoteDBService = quoteDBService;

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_quotes;
    }

    @Override
    protected void onFragmentCreated(View view, Bundle savedInstanceState) {
        quoteDBService = new QuoteDBService(getContext());
        presenterListener.setViewListener(this);

        recViewSetUp();
    }


    @Override
    public void onResume() {
        super.onResume();
        loadStudents();
    }


    private void loadStudents() {
        quoteDBService.getAllQuotes(data -> adapter.setQuotes(data));
    }


    private void recViewSetUp(){
        binding.rvQuotes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new MyQuotesAdapter(getContext());
        binding.rvQuotes.setAdapter(adapter);
    }
}
