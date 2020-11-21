package com.example.farsicards;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

public class DatabaseManager{
    Context context;
    WordDatabase db;
    WordCardDAO wordCardDAO;
    Thread db_thread;

    public DatabaseManager(Context context){
        this.context = context;
    }
    public void BuildDB(){
        Log.d("DB","Building DB");
        this.db = Room.databaseBuilder(this.context, WordDatabase.class, "WordBank")
                .allowMainThreadQueries()
                .build();
        this.wordCardDAO = this.db.getWordCardDAO();
    }
    public void AddWord(String english, String farsi){
        if(db == null || wordCardDAO == null){
            Log.d("DB","DB NULL");
            return;
        }

        if (GetWord(english) != null){
            Log.d("DB","Word Exists");
            UpdateWord(english,farsi);
            return;
        }

        WordCard word = new WordCard();
        word.setEnglish(english);
        word.setFarsi(farsi);

        Log.d("DB","Adding Word");
        this.wordCardDAO.insert(word);

    }

    public void UpdateWord(String english, String farsi){
        if(db == null || wordCardDAO == null){
            return;
        }
        if (GetWord(english) == null){
            AddWord(english,farsi);
        }
        WordCard word = new WordCard();
        word.setEnglish(english);
        word.setFarsi(farsi);

        Log.d("DB","Updating Word");
        wordCardDAO.update(word);

    }

    public WordCard GetWord(String english){
        if(db == null || wordCardDAO == null){
            return null;
        }
        WordCard card = wordCardDAO.getWordCardById(english);
        return card;
    }


}
