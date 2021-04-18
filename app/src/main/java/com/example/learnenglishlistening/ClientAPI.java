package com.example.learnenglishlistening;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ClientAPI {
    @GET("/dicservice.json/lookup")
    Call<ArrayList<EnglishWord>> getTranslation(@Query("key") String key, @Query("lang") String lang, @Query("text") String text);
}
