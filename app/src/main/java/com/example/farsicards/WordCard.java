package com.example.farsicards;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Words")
public class WordCard {
    @PrimaryKey
    @NonNull private String english;
    @NonNull private String farsi;

    @NonNull private String book;
    @NonNull private int chapter;
    @NonNull private int chapter_index;
    @NonNull private String farsi_spoken;
    @NonNull private String farsi_sentence_written;
    @NonNull private String farsi_sentence_spoken;
    @NonNull private boolean has_spoken_variation;




    @NonNull
    public String getEnglish() {
        return english;
    }

    public void setEnglish(@NonNull String english) {
        this.english = english;
    }

    public String getFarsi() {
        return farsi;
    }

    public void setFarsi(String farsi) {
        this.farsi = farsi;
    }

    @NonNull
    public String getBook() {
        return book;
    }

    public void setBook(@NonNull String book) {
        this.book = book;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getChapter_index() {
        return chapter_index;
    }

    public void setChapter_index(int chapter_index) {
        this.chapter_index = chapter_index;
    }

    @NonNull
    public String getFarsi_spoken() {
        return farsi_spoken;
    }

    public void setFarsi_spoken(@NonNull String farsi_spoken) {
        this.farsi_spoken = farsi_spoken;
    }

    @NonNull
    public String getFarsi_sentence_written() {
        return farsi_sentence_written;
    }

    public void setFarsi_sentence_written(@NonNull String farsi_sentence_written) {
        this.farsi_sentence_written = farsi_sentence_written;
    }

    @NonNull
    public String getFarsi_sentence_spoken() {
        return farsi_sentence_spoken;
    }

    public void setFarsi_sentence_spoken(@NonNull String farsi_sentence_spoken) {
        this.farsi_sentence_spoken = farsi_sentence_spoken;
    }

    public boolean isHas_spoken_variation() {
        return has_spoken_variation;
    }

    public void setHas_spoken_variation(boolean has_spoken_variation) {
        this.has_spoken_variation = has_spoken_variation;
    }
}
