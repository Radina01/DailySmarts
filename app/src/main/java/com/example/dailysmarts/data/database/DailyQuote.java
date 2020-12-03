package com.example.dailysmarts.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DailyQuote")
public class DailyQuote {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "quote")
    public String quoteText;

    @ColumnInfo(name = "author")
    public String quoteAuthor;

    @ColumnInfo(name = "date")
    public String quoteDate;

    public DailyQuote() {
    }

    public DailyQuote(String quoteText, String quoteAuthor, String quoteDate) {
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
        this.quoteDate = quoteDate;
    }

    public int getId() {
        return id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public String getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(String quoteDate) {
        this.quoteDate = quoteDate;
    }
}
