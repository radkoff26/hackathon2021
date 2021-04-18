package com.example.learnenglishlistening;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class Adapter extends ArrayAdapter<EnglishWord> {

    public Adapter(@NonNull Context context, int resource, @NonNull List<EnglishWord> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        EnglishWord englishWord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        ((SpeechImageView) convertView.findViewById(R.id.iv)).setWordToSpeak(englishWord.getText());
        ((TextView) convertView.findViewById(R.id.tv)).setText(englishWord.getText());

        return convertView;
    }
}
