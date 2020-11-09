package com.example.dailysmarts.data.database;

import android.content.Context;

import androidx.room.Room;

public class Database {
    private static AppDatabase instance;
    public static final String DATABASE_NAME = "com.example.dailysmarts.data.database";

    private Database() {
    }

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
