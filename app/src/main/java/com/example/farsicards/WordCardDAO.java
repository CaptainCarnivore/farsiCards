package com.example.farsicards;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Word Card DAO
 * Responsible for queries to the Wordbank Database.
 */
@Dao
public interface WordCardDAO {
    @Insert
    public void insert(WordCard... WordCards);
    @Update
    public void update(WordCard... WordCards);
    @Delete
    public void delete(WordCard wordCard);

    /**
     * Selects all words from database.
     * @return
     */
    @Query("SELECT * FROM Words")
    public List<WordCard> getWords();

    /**
     * Selects a specific word from it's english key.
     * @param english
     * @return
     */
    @Query("SELECT * FROM Words WHERE english = :english")
    public WordCard getWordCardById(String english);

    /**
     * Gets the books in database
     * @return
     */
    @Query("SELECT book FROM Words")
    public List<String> getBooks();


    @Query("SELECT DISTINCT chapter FROM Words WHERE book= :book")
    public  List<Integer> GetChaptersFromBook(String book);


    @Query("SELECT * FROM Words WHERE book= :book AND chapter = :chapter")
    public List<WordCard> GetWordsInChapter(String book, int chapter);

    @Query("SELECT * FROM Words WHERE is_tagged = :tagged")
    public List<WordCard> GetTaggedWords(boolean tagged);

    @Query("UPDATE Words SET is_tagged = :is_tagged WHERE english= :english")
    public void UpdateTagForWord(String english, boolean is_tagged);
}
