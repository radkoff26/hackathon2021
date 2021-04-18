package com.example.learnenglishlistening;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

// CAUTION! It doesn't work in emulator. Try it only on physical device.
public class SpeechImageView extends androidx.appcompat.widget.AppCompatImageView {

    private String wordToSpeak;
    private TextToSpeech tts;

    public SpeechImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.tts = new TextToSpeech(context, status -> {
        });
        this.tts.setLanguage(Locale.ENGLISH);
        this.tts.setPitch(.8f);

        this.setOnClickListener(v -> {
            if (wordToSpeak != null) {
                tts.speak(wordToSpeak, TextToSpeech.QUEUE_ADD, null);
            }
        });
    }

    public void setWordToSpeak(String wordToSpeak) {
        this.wordToSpeak = wordToSpeak;
    }
}