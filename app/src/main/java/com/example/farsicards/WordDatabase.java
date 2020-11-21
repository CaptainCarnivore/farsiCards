package com.example.farsicards;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {WordCard.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordCardDAO getWordCardDAO();
}
