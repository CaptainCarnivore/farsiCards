package com.example.farsicards;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Basic Review Activity
 * Selects a random word and allows the user to select if they know it or not.
 * User can also flip over the word to see the translation and upon sumbitting known/ unkown a new word is chosen.
 */
public class BasicReviewActivity extends AppCompatActivity {

    // Used for tagging system
    boolean is_word_tagged = false;
    boolean use_tagged_words = false;
    // DB Variables
    DatabaseManager db;
    List<WordCard> words;
    int current_word_index = 0;
    int last_index = 0;
    Random random;



    // Show translation
    boolean show_english = false;
    // Textview for the word
    TextView word_text;
    // Fonts so English and Farsi can look cleaner
    Typeface english_typeface;
    Typeface farsi_typeface;

    // Book and Chapter
    String book = "vol1";
    int chapter = 0;

    /**
     * OnCreate
     * Set up variables and select the first word.
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        book = getIntent().getExtras().getString("book");
        chapter = getIntent().getExtras().getInt("chapter");
        use_tagged_words = getIntent().getExtras().getBoolean("tagged");

        english_typeface = getResources().getFont(R.font.oswald);
        farsi_typeface = getResources().getFont(R.font.mirza);
        setContentView(R.layout.activity_basic_review);
        word_text = (TextView) findViewById(R.id.wordTextView);
        random = new java.util.Random();


        if (use_tagged_words){
            words = db.wordCardDAO.GetTaggedWords(true);
            Log.d("Review", "Using Tagged Words.");
            Log.d("Review", String.valueOf(words.size()));
        }
        else {
            words = db.wordCardDAO.GetWordsInChapter(book, chapter);
        }
        last_index = words.size();
        NewWord();


    }

    /**
     * New Word
     * Reset font selection to Farsi and chose a new word to display.
     */
    public void NewWord(){
        if( last_index < 0 ){
            return;
        }
        // Default back to farsi
        show_english = false;
        word_text.setTypeface(farsi_typeface);
        word_text.setTextSize(64);

        // select new index
        current_word_index = random.nextInt(last_index);
        String text = words.get(current_word_index).getFarsi();
        Log.d("Review", String.valueOf(current_word_index));
        Log.d("Review", text);
        word_text.setText(text);
        is_word_tagged = words.get(current_word_index).isIs_tagged();

        // Update Tag star
        ImageView star_image = (ImageView)findViewById(R.id.tag_fill);
        if (is_word_tagged){
            star_image.setVisibility(0);
            //toggleButton.setImageResource(R.drawable.star_filled);
        }else{
            star_image.setVisibility(4);
            //toggleButton.setImageResource(R.drawable.star);

        }

    }

    /**
        Submit Known
        used for when the user taps on the check button to indicate they know the word
     */
    public void SubmitKnown(View view){
        // Assign new word
        NewWord();
    }

    /**
        Submit Unknown
        used for when the user taps on the check button to indicate they DONT know the word
     */
    public void SubmitUnknown(View view){
        // Assign new word
        NewWord();
    }


    /**
     * Flip the word between languages
     * @param view
     */
    public void FlipOver(View view){
        show_english = !show_english;
        if( show_english ){
            String text = words.get(current_word_index).getEnglish();
            Log.d("Review", text);
            word_text.setText(text);
            word_text.setTypeface(english_typeface);
            word_text.setTextSize(48);
        }else{
            String text = words.get(current_word_index).getFarsi();
            Log.d("Review", text);
            word_text.setText(text);
            word_text.setTypeface(farsi_typeface);
            word_text.setTextSize(64);
        }
    }

    /**
        Toggle Tagged Word
        Used to toggle if a word is tagged or not
     */
    public void ToggleTaggedWord(View view){
        // for testing purposes
        Log.d("Toggle Checks", "Toggle");

        // toggle
        is_word_tagged = !is_word_tagged;
        ImageButton toggleButton = (ImageButton)findViewById(R.id.TagButton);
        ImageView star_image = (ImageView)findViewById(R.id.tag_fill);
        // update image and database
        if (is_word_tagged){
            Log.d("Toggle Checks", "ToggleTaggedWord: " + is_word_tagged);
            db.wordCardDAO.UpdateTagForWord(words.get(current_word_index).getEnglish(), true);
            star_image.setVisibility(0);
            words.get(current_word_index).setIs_tagged(true);
        }else{
            Log.d("Toggle Checks", "ToggleTaggedWord: " + is_word_tagged);
            db.wordCardDAO.UpdateTagForWord(words.get(current_word_index).getEnglish(), false);
            star_image.setVisibility(4);
            words.get(current_word_index).setIs_tagged(false);

        }
    }


    /** Return to Chapter Selection
     *
     * @param view
     */
    public void ReturnToChapterSelect(View view){
        // go back to book selection if in tagged mode
        if ( use_tagged_words ){
            Intent intent = new Intent(this, BookSelection.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return;
        }

        Intent intent = new Intent(this, ChapterSelection.class);
        intent.putExtra("book", book);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}