package com.example.farsicards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Screen for selecting chapter from passed in book
 */
public class ChapterSelection extends AppCompatActivity {
    DatabaseManager db;
    String book = "vol1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_selection);
        book = getIntent().getExtras().getString("book");

        String book_title = "Vol. 1";
        if (book.matches("vol2")){
            book_title = "Vol. 2";
        }

        TextView book_name = (TextView) findViewById(R.id.book_name);
        book_name.setText(book_title);

        List<Integer> chapter_list = db.wordCardDAO.GetChaptersFromBook(book);
        Log.d("Chapters", String.valueOf(chapter_list.size()));




        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ChapterArrayAdapter<Integer> adapter =
                        new ChapterArrayAdapter<Integer>(getApplicationContext(), chapter_list);
                ListView list = (ListView) findViewById(R.id.chapter_list);
                list.setAdapter(adapter);
            }
        });

        ListView list = (ListView) findViewById(R.id.chapter_list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Chapter", String.valueOf(position));

                Intent reviewIntent = new Intent(getApplicationContext(), BasicReviewActivity.class);
                reviewIntent.putExtra("book",book);
                reviewIntent.putExtra("chapter",position + 1);
                reviewIntent.putExtra("tagged",false);

                startActivity(reviewIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


            }
        });
    }


    public void ReturnToBookSelect(View view){
        Intent intent = new Intent(this, BookSelection.class);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void OpenReviewActivity(View view){
        Log.d("Chapters", view.toString());
        Intent reviewIntent = new Intent(this, BasicReviewActivity.class);
        startActivity(reviewIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }



}