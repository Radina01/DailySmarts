package com.example.dailysmarts.data.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

public class QuoteDBService {

    @Inject QuoteDao quoteDao;

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

    public void ifExists(DataListener<Boolean> dataListener, String quote) {

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return quoteDao.ifExists(quote);
            }

            @Override
            protected void onPostExecute(Boolean ifExists) {
                dataListener.onData(ifExists);
            }
        }.execute();
    }

    public interface DataListener<T> {
        void onData(T data);
    }
}
