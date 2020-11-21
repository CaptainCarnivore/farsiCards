package com.example.farsicards;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Words")
public class WordCard {
    @PrimaryKey
    @NonNull private String english;
    @NonNull private String farsi;
    /*
    @NonNull private String book;
    @NonNull private int chapter;
    @NonNull private int chapter_index;
    @NonNull private String farsi_spoken;
    @NonNull private String farsi_sentence_written;
    @NonNull private String farsi_sentence_spoken;
    @NonNull private boolean has_spoken_variation;
*/



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
}
