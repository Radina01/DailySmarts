package com.example.dailysmarts.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DailyQuoteDao {
    @Query("SELECT * FROM DailyQuote")
    List<DailyQuote> getAll();
    @Insert
    void insertAll(DailyQuote... dailyQuote);

    @Delete
    void deleteQuote(DailyQuote dailyQuote);
}
