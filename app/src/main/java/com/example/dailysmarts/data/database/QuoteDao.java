package com.example.dailysmarts.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuoteDao {
    @Query("SELECT * FROM quotes")
    List<Quote> getAll();

    @Insert
    void insertAll(Quote... quotes);

    @Delete
    void deleteQuote(Quote quote);


}
