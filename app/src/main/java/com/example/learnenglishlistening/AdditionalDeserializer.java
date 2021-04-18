package com.example.learnenglishlistening;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

class AdditionalDeserializer implements JsonDeserializer<ArrayList<EnglishWord>> {

    @Override
    public ArrayList<EnglishWord> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray arrayList = json
                .getAsJsonObject()
                .get("def")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("tr")
                .getAsJsonArray();
        ArrayList<EnglishWord> englishWords = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            englishWords.add(new Gson().fromJson(arrayList.get(i).getAsJsonObject(), EnglishWord.class));
        }

        return englishWords;
    }
}
