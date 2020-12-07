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
    public void AddWord(String book, int chapter,int chapter_index ,String english, String farsi,String spoken_farsi , String farsi_sentence, String spoken_sentence){
        if(db == null || wordCardDAO == null){
            Log.d("DB","DB NULL");
            return;
        }

        if (GetWord(english) != null){
            Log.d("DB","Word Exists");
            UpdateWord( book,  chapter, chapter_index , english,  farsi, spoken_farsi ,  farsi_sentence,  spoken_sentence);
            return;
        }

        WordCard word = new WordCard();
        word.setEnglish(english);
        word.setFarsi(farsi);
        word.setBook(book);
        word.setChapter(chapter);
        word.setChapter_index(chapter_index);
        word.setFarsi_spoken(spoken_farsi);
        word.setFarsi_sentence_written(farsi_sentence);
        word.setFarsi_sentence_spoken(spoken_sentence);
        word.setHas_spoken_variation(spoken_farsi != "-");

        Log.d("DB","Adding Word");
        this.wordCardDAO.insert(word);

    }

    public void UpdateWord(String book, int chapter,int chapter_index ,String english, String farsi,String spoken_farsi , String farsi_sentence, String spoken_sentence){
        if(db == null || wordCardDAO == null){
            return;
        }
        if (GetWord(english) == null){
            AddWord( book,  chapter, chapter_index , english,  farsi, spoken_farsi ,  farsi_sentence,  spoken_sentence);
        }
        WordCard word = new WordCard();
        word.setEnglish(english);
        word.setFarsi(farsi);
        word.setBook(book);
        word.setChapter(chapter);
        word.setChapter_index(chapter_index);
        word.setFarsi_spoken(spoken_farsi);
        word.setFarsi_sentence_written(farsi_sentence);
        word.setFarsi_sentence_spoken(spoken_sentence);
        word.setHas_spoken_variation(spoken_farsi != "-");

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
