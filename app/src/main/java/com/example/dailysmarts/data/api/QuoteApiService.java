package com.example.dailysmarts.data.api;


import com.example.dailysmarts.data.database.Quote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApiService {
    @GET("/quotes/random")
    Call<Quote> getRandomQuote();
}
