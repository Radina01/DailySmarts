package com.example.dailysmarts.data.api;


import com.example.dailysmarts.data.database.Quote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApiService {
    @GET("/?method=getQuote&format=json&lang=en")
    Call<Quote> getRandomEngQuote();

    @GET("/?method=getQuote&format=json&lang=ru")
    Call<Quote> getRandomRusQuote();
}
