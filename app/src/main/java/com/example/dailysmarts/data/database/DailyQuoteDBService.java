package com.example.dailysmarts.data.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

public class DailyQuoteDBService {
    private final DailyQuoteDao dailyQuoteDao;

    @Inject
    public DailyQuoteDBService(Context context) {
        dailyQuoteDao = Database.getInstance(context).dailyQuoteDao();
    }


    public void getAllQuotes(QuoteDBService.DataListener<List<DailyQuote>> dataListener) {
        new AsyncTask<Void, Void, List<DailyQuote>>() {
            @Override
            protected List<DailyQuote> doInBackground(Void... voids) {
                return dailyQuoteDao.getAll();
            }

            @Override
            protected void onPostExecute(List<DailyQuote> quotes) {
                dataListener.onData(quotes);
            }
        }.execute();
    }

    public void addQuote(DailyQuote quote) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                dailyQuoteDao.insertAll((quote));
                return null;

            }
        }.execute();
    }

    public void deleteQuote(DailyQuote dailyQuote) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                dailyQuoteDao.deleteQuote(dailyQuote);
                return null;
            }
        }.execute();
    }

    public interface DataListener<T> {
        void onData(T data);
    }
}
