package fr.sheepy.aboutlotr;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.sheepy.aboutlotr.data.LOTRAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static LOTRAPI lotrapiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGsonInstance() {
        if (gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static LOTRAPI getLotrapiInstance() {
        if (lotrapiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))
                    .build();

            lotrapiInstance = retrofit.create(LOTRAPI.class);
        }
        return lotrapiInstance;
    }

    public static SharedPreferences getSharedPreferencesInstance(Context context) {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences(Constants.CHARACTERS_SHAREDPREF_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
