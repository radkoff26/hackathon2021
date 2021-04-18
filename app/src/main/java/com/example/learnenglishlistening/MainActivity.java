package com.example.learnenglishlistening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String KEY = "dict.1.1.20210418T102615Z.c63159147e4b7cf0.2264db2eb24a92d92e7e34b6dab39981a5de5540";
    private Retrofit retrofit;
    private Gson gson;
    private TextView tv;
    private SpeechImageView iv;
    private ListView lv;
    private TextInputEditText et;
    private AppCompatButton btn;
    private ClientAPI api;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        lv = findViewById(R.id.lv);
        et = findViewById(R.id.ru);
        btn = findViewById(R.id.search);

        gson = new GsonBuilder()
                .registerTypeAdapter(ArrayList.class, new AdditionalDeserializer())
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://dictionary.yandex.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(ClientAPI.class);

        btn.setOnClickListener(v -> {
            if (et.getText().toString().isEmpty()) {
                Snackbar.make(btn, "Вы не ввели слово!", Snackbar.LENGTH_SHORT).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show();
                return;
            }

            if (et.getText().toString().split(" ").length > 1) {
                Snackbar.make(btn, "Вы ввели больше двух слов!", Snackbar.LENGTH_SHORT).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show();
                return;
            }

            String word = et.getText().toString();

            search(word);
        });

    }

    public void search(String word) {
        api.getTranslation(KEY, "ru-en", word)
                .enqueue(new Callback<ArrayList<EnglishWord>>() {
                    @Override
                    public void onResponse(Call<ArrayList<EnglishWord>> call, Response<ArrayList<EnglishWord>> response) {
                        ArrayList<EnglishWord> words = response.body();
                        ArrayList<EnglishWord> res = new ArrayList<>();
                        StringBuilder s = new StringBuilder();
                        if (words.size() > 5) {
                            for (int i = 0; i < 5; i++) {
                                res.add(words.get(i));
                            }
                        } else {
                            for (int i = 0; i < words.size(); i++) {
                                res.add(words.get(i));
                            }
                        }
                        adapter = new Adapter(getApplicationContext(), R.layout.item, res);
                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<EnglishWord>> call, Throwable t) {
                        t.getMessage();
                    }
                });

    }
}