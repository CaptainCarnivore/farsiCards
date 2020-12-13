package com.example.farsicards;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Database Manager
 * Responsible for Creation and Modifying fo Wordbank Database
 * Using Android Room for storage.
 */
public class DatabaseManager{
    Context context;
    public static WordDatabase db;
    public static WordCardDAO wordCardDAO;
    Thread db_thread;

    public DatabaseManager(Context context){
        this.context = context;
    }
    public void BuildDB(){
        // Checks if we've built the DB
        if(this.db != null){
            return;
        }
        Log.d("DB","Building DB");
        this.db = Room.databaseBuilder(this.context, WordDatabase.class, "WordBank")
                .allowMainThreadQueries()
                .build();




        this.wordCardDAO = this.db.getWordCardDAO();

    }

    /**
     * Add a word to the Database
     * @param book
     * @param chapter
     * @param chapter_index
     * @param english
     * @param farsi
     * @param spoken_farsi
     * @param farsi_sentence
     * @param spoken_sentence
     */
    public void AddWord(String book, int chapter,int chapter_index ,String english, String farsi,String spoken_farsi , String farsi_sentence, String spoken_sentence, boolean is_tagged){
        if(db == null || wordCardDAO == null){
            Log.d("DB","DB NULL");
            return;
        }

        if (GetWord(english) != null){
            Log.d("DB","Word Exists");
            //UpdateWord( book,  chapter, chapter_index , english,  farsi, spoken_farsi ,  farsi_sentence,  spoken_sentence, is_tagged);
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

    /**
     * Update a word that exists in the database.
     * @param book
     * @param chapter
     * @param chapter_index
     * @param english
     * @param farsi
     * @param spoken_farsi
     * @param farsi_sentence
     * @param spoken_sentence
     */
    public void UpdateWord(String book, int chapter,int chapter_index ,String english, String farsi,String spoken_farsi , String farsi_sentence, String spoken_sentence, boolean is_tagged){
        if(db == null || wordCardDAO == null){
            return;
        }
        if (GetWord(english) == null){
            AddWord( book,  chapter, chapter_index , english,  farsi, spoken_farsi ,  farsi_sentence,  spoken_sentence, is_tagged);
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

    /**
     * Get a specific word from Database
     * @param english
     * @return
     */
    public WordCard GetWord(String english){
        if(db == null || wordCardDAO == null){
            return null;
        }
        WordCard card = wordCardDAO.getWordCardById(english);
        return card;
    }

}
