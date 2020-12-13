package com.example.farsicards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.farsicards.R;
import com.example.farsicards.WordCard;

import java.util.List;

public class ChapterArrayAdapter<W> extends ArrayAdapter<Integer> {
    public ChapterArrayAdapter(@NonNull Context context, @NonNull List<Integer> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Integer item = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.chapter_item,parent,false);
        TextView chapter = (TextView) convertView.findViewById(R.id.chapter);
        TextView chapter_num = (TextView) convertView.findViewById(R.id.chapter_text_num);
        chapter.setText(item.toString());
        chapter_num.setText(item.toString());

        return convertView;
    }
}
