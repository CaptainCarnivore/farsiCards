package com.example.farsicards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager db_Manager = new DatabaseManager(this);
        /*
        db_Manager.BuildDB();
        db_Manager.AddWord("dev_english", "dev_farsi_updated");
        Log.d("Debug", db_Manager.GetWord("dev_english").getFarsi());*/
        Thread db_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                db_Manager.BuildDB();
                db_Manager.AddWord("test","test_f");
            }
        }, "DB_Thread");
        db_thread.start();

        // Audio Test
        MediaPlayer mediaPlayer = MediaPlayer.create(this, this.getResources().getIdentifier("raw/test_audio",null,this.getPackageName()));
        mediaPlayer.start();
    }

    public void OpenWordBank(View view){
        Intent wordBankIntent = new Intent(this, WordBank_Main_Activity.class);
        startActivity(wordBankIntent);
    }

    public void OpenReview(View view){
        Intent reviewIntent = new Intent(this, BasicReviewActivity.class);
        startActivity(reviewIntent);
    }

    // Austin's comment
    // Did you know? Bubble wrap was originally invented as wallpaper.

    // Stephen's comment
    // I've now made our comments inconsistent because I didn't put a space at the start of mine.

    // Austin's Second Comment
    // hhahahahah

    // Dallin's Comment
    // Fixed.



}