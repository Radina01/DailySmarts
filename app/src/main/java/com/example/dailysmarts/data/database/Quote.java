package com.example.dailysmarts.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotes")
public class Quote {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "quote")
    public String quote;

    @ColumnInfo(name = "author")
    public String author;

    public Quote(){}

    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }
}
