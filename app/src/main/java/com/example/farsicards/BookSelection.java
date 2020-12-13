package com.example.farsicards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Allows Selection of a specific book for review
 */
public class BookSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_selection);

    }

    /**
     * Return to Main Activity
     * @param view
     */
    public void ReturnToMainMenu(View view){
        Intent mainIntent = new Intent(this, MainActivity.class);

        startActivity(mainIntent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    // Vol 1 selection
    public void SelectBook1(View view){
        Intent chapterIntent = new Intent(this, ChapterSelection.class);
        chapterIntent.putExtra("book","vol1");
        startActivity(chapterIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
    // Vol 2 selection
    public void SelectBook2(View view){
        Intent chapterIntent = new Intent(this, ChapterSelection.class);
        chapterIntent.putExtra("book","vol2");
        startActivity(chapterIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    //Tagged Selection
    public void SelectTagged(View view){
        Intent reviewIntent = new Intent(getApplicationContext(), BasicReviewActivity.class);
        reviewIntent.putExtra("book","");
        reviewIntent.putExtra("chapter", 0);
        reviewIntent.putExtra("tagged",true);

        startActivity(reviewIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}