package com.example.dailysmarts.data.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import dagger.Provides;

public class QuoteDBService {

    private final QuoteDao quoteDao;

    @Inject
    public QuoteDBService(Context context) {
        quoteDao = Database.getInstance(context).quoteDao();
    }


    public void getAllQuotes(DataListener<List<Quote>> dataListener) {
        new AsyncTask<Void, Void, List<Quote>>() {
            @Override
            protected List<Quote> doInBackground(Void... voids) {
                return quoteDao.getAll();
            }

            @Override
            protected void onPostExecute(List<Quote> students) {
                dataListener.onData(students);
            }
        }.execute();
    }

    public void addQuote(Quote quote) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                quoteDao.insertAll((quote));
                return null;

            }
        }.execute();
    }

    public void deleteQuote(Quote quote) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                quoteDao.deleteQuote(quote);
                return null;
            }
        }.execute();
    }

    public interface DataListener<T> {
        void onData(T data);
    }
}
