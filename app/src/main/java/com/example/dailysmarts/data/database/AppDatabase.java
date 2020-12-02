package com.example.dailysmarts.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Quote.class, DailyQuote.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuoteDao quoteDao();
    public abstract DailyQuoteDao dailyQuoteDao();
}
