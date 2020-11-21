package com.example.farsicards;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordCardDAO {
    @Insert
    public void insert(WordCard... WordCards);
    @Update
    public void update(WordCard... WordCards);
    @Delete
    public void delete(WordCard wordCard);

    @Query("SELECT * FROM Words")
    public List<WordCard> getWords();

    @Query("SELECT * FROM Words WHERE english = :english")
    public WordCard getWordCardById(String english);

}
