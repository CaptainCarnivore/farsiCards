package com.example.farsicards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;


/**
 * Main Activity providing user selection options.
 */
public class MainActivity extends AppCompatActivity {
    // Database
    DatabaseManager db_Manager;

    boolean database_loaded = false;

    // Animation Variables for the background words
    ValueAnimator animation = ValueAnimator.ofFloat(.0f,1f);
    ValueAnimator animation_02 = ValueAnimator.ofFloat(1.0f,0.0f);
    ValueAnimator animation_03 = ValueAnimator.ofFloat(1.0f,0.0f);
    ObjectAnimator alpha_animation_in;
    ObjectAnimator alpha_animation_out;
    ObjectAnimator alpha_animation_in_02;
    ObjectAnimator alpha_animation_out_02;
    ObjectAnimator alpha_animation_in_03;
    ObjectAnimator alpha_animation_out_03;
    TextView background_word_01;
    TextView background_word_02;
    TextView background_word_03;

    // Variables for selecting new words in the background
    int last_index = 0;
    Random random;
    List<WordCard> words;

    /**
     * onCreate
     * Starts the main activity. Sets up the background animations and initializes the wordbank database.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create Database
        if (db_Manager.wordCardDAO == null){
            db_Manager = new DatabaseManager(this);
            Thread db_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    db_Manager.BuildDB();
                    InputStream inputStream = getResources().openRawResource(R.raw.wordbank);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    try {
                        String csvLine;
                        while ((csvLine = reader.readLine()) != null) {
                            String[] row = csvLine.split(",");

                            Log.d("DB", row[3]);
                            db_Manager.AddWord(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]), row[3], row[4], row[5], row[6], row[7], false);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    last_index = db_Manager.wordCardDAO.getWords().size() - 1;
                    words = db_Manager.wordCardDAO.getWords();
                }
            }, "DB_Thread");
            db_thread.start();
         }

        // Craete Random for selecting words for the background
        random = new java.util.Random();

        // Textviews used for the background
        background_word_01 = (TextView) findViewById(R.id.backgroundword_01);
        background_word_02 = (TextView) findViewById(R.id.backgroundword_02);
        background_word_03 = (TextView) findViewById(R.id.backgroundword_03);

        // Set the delayed start words alpha to 0
        background_word_02.setAlpha(0.0f);
        background_word_03.setAlpha(0.0f);

        // Duration of the sideways movement
        animation.setDuration(12000);
        // Repeat animation
        animation.setRepeatCount(Animation.INFINITE);

        // Fade in alpha
        alpha_animation_in = ObjectAnimator.ofFloat(background_word_01,"alpha",0.0f, 1f);
        // Duration is 1/2 of the sideways movement
        alpha_animation_in.setDuration(6000);

        //Fade out alpha animation
        alpha_animation_out = ObjectAnimator.ofFloat(background_word_01,"alpha",1.0f, 0.0f);
        alpha_animation_out.setDuration(6000);

        //  Sets up the sideways movement
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                // You can use the animated value in a property that uses the
                // same type as the animation. In this case, you can use the
                // float value in the translationX property.
                float animatedValue = (float)updatedAnimation.getAnimatedValue();
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) background_word_01.getLayoutParams();
                params.horizontalBias = animatedValue;
                params.verticalBias = params.verticalBias;
                background_word_01.setLayoutParams(params);
            }
        });

        // Animation set to play the fade in then the fade out
        AnimatorSet word_set = new AnimatorSet();
        word_set.play(alpha_animation_in).before(alpha_animation_out);
        word_set.play(alpha_animation_out);
        // Listener for the end of the animation so we can restart the animation set and select a new word
        word_set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                String text = words.get(random.nextInt(last_index)).getFarsi();
                background_word_01.setText(text);
                word_set.start();
            }
        });

        // Start animations
        word_set.start();
        animation.start();


        // Same as above but for word 2, different timing used and a delay is added to the start
        animation_02.setDuration(12000);
        animation_02.setRepeatCount(Animation.INFINITE);

        alpha_animation_in_02 = ObjectAnimator.ofFloat(background_word_02,"alpha",0.0f, 1f);
        alpha_animation_in_02.setDuration(6000);

        alpha_animation_out_02 = ObjectAnimator.ofFloat(background_word_02,"alpha",1.0f, 0.0f);
        alpha_animation_out_02.setDuration(6000);

        animation_02.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                // You can use the animated value in a property that uses the
                // same type as the animation. In this case, you can use the
                // float value in the translationX property.
                float animatedValue = (float)updatedAnimation.getAnimatedValue();
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) background_word_02.getLayoutParams();
                params.horizontalBias = animatedValue;
                params.verticalBias = params.verticalBias;
                background_word_02.setLayoutParams(params);
            }
        });


        AnimatorSet word_set_02 = new AnimatorSet();
        word_set_02.play(alpha_animation_in_02).before(alpha_animation_out_02);
        word_set_02.play(alpha_animation_out_02);
        word_set_02.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animation_02.setStartDelay(0);
                word_set_02.setStartDelay(0);
                String text = words.get(random.nextInt(last_index)).getFarsi();
                background_word_02.setText(text);
                word_set_02.start();
            }
        });

        word_set_02.setStartDelay(3000);
        word_set_02.start();
        animation_02.setStartDelay(3000);
        animation_02.start();



        // Same as above but for word 3, different timing used and a delay is added to the start
        animation_03.setDuration(14000);
        animation_03.setRepeatCount(Animation.INFINITE);

        alpha_animation_in_03 = ObjectAnimator.ofFloat(background_word_03,"alpha",0.0f, 1f);
        alpha_animation_in_03.setDuration(7000);

        alpha_animation_out_03 = ObjectAnimator.ofFloat(background_word_03,"alpha",1.0f, 0.0f);
        alpha_animation_out_03.setDuration(7000);

        animation_03.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                // You can use the animated value in a property that uses the
                // same type as the animation. In this case, you can use the
                // float value in the translationX property.
                float animatedValue = (float)updatedAnimation.getAnimatedValue();
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) background_word_03.getLayoutParams();
                params.horizontalBias = animatedValue;
                params.verticalBias = params.verticalBias;
                background_word_03.setLayoutParams(params);
            }
        });


        AnimatorSet word_set_03 = new AnimatorSet();
        word_set_03.play(alpha_animation_in_03).before(alpha_animation_out_03);
        word_set_03.play(alpha_animation_out_03);
        word_set_03.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animation_03.setStartDelay(0);
                word_set_03.setStartDelay(0);
                String text = words.get(random.nextInt(last_index)).getFarsi();
                background_word_03.setText(text);
                word_set_03.start();
            }
        });

        word_set_03.setStartDelay(8000);
        word_set_03.start();
        animation_03.setStartDelay(8000);
        animation_03.start();

        // Audio Test
        /*
        MediaPlayer mediaPlayer = MediaPlayer.create(this, this.getResources().getIdentifier("raw/test_audio",null,this.getPackageName()));
        mediaPlayer.start();

         */
    }

    public void OpenWordBank(View view){
        Intent wordBankIntent = new Intent(this, WordBank_Main_Activity.class);

        startActivity(wordBankIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    public void OpenBookSelect(View view){
        Intent reviewIntent = new Intent(this, BookSelection.class);
        startActivity(reviewIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}