package com.example.farsicards;

import androidx.appcompat.app.AppCompatActivity;

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
        setContentView(R.layout.activity_word_bank__main);

    }

    /*
    Toggle Book Display
    toggles hiding the children chapter buttons
     */
    public void ToggleBookDisplayVol1(View view){
        is_vol_1_expanded = !is_vol_1_expanded;
        LinearLayout layout = findViewById(R.id.vol1_chapter_list);
        if (is_vol_1_expanded){
            layout.setVisibility(View.VISIBLE);
        }else{

            layout.setVisibility(View.GONE);
        }
    }
}