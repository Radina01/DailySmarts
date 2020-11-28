package com.example.dailysmarts.data.api;


import com.example.dailysmarts.data.database.Quote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteApiService {
    @GET("/api/1.0/?method=getQuote&format=json&lang=en")
    Call<Quote> getRandomEngQuote();

    @GET("/api/1.0/?method=getQuote&format=json&lang=ru")
    Call<Quote> getRandomRusQuote();
}
