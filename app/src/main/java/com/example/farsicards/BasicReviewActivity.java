package com.example.farsicards;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class BasicReviewActivity extends AppCompatActivity {

    boolean is_word_tagged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_review);
    }

    /*
        Submit Known
        used for when the user taps on the check button to indicate they know the word
     */
    public void SubmitKnown(View view){

    }

    /*
        Submit Unknown
        used for when the user taps on the check button to indicate they DONT know the word
     */
    public void SubmitUnknown(View view){

    }


    /*
        Toggle Tagged Word
        Used to toggle if a word is tagged or not
     */
    public void ToggleTaggedWord(View view){
        // for testing purposes
        Log.d("Toggle Checks", "Toggle");
        is_word_tagged = !is_word_tagged;
        ImageButton toggleButton = (ImageButton)findViewById(R.id.TagButton);
        if (is_word_tagged){
            Log.d("Toggle Checks", "ToggleTaggedWord: " + is_word_tagged);
            toggleButton.setImageResource(R.drawable.star_filled);
        }else{
            Log.d("Toggle Checks", "ToggleTaggedWord: " + is_word_tagged);
            toggleButton.setImageResource(R.drawable.star);

        }
    }

}