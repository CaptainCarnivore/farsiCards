package com.example.farsicards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class WordBank_Main_Activity extends AppCompatActivity {

    boolean is_vol_1_expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager db = new DatabaseManager(this);
        setContentView(R.layout.activity_word_bank__main);

    }

    public void ReturnToMainMenu(View view){
        Intent mainIntent = new Intent(this, MainActivity.class);

        startActivity(mainIntent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}