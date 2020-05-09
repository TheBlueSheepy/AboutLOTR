package fr.sheepy.aboutlotr.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import fr.sheepy.aboutlotr.Constants;
import fr.sheepy.aboutlotr.Singletons;
import fr.sheepy.aboutlotr.presentation.model.Character;
import fr.sheepy.aboutlotr.presentation.model.LOTRAPIResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LOTRRepository {

    private static LOTRRepository lotrRepositoryInstance;
    private Gson gson;
    private LOTRAPI lotrapi;
    private SharedPreferences sharedPreferences;

    private LOTRRepository(LOTRAPI lotrapi, SharedPreferences sharedPreferences, Gson gson) {
        this.lotrapi = lotrapi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    synchronized public static LOTRRepository getInstance(Context context) {
        if (lotrRepositoryInstance == null) {
            lotrRepositoryInstance = new LOTRRepository(
                    Singletons.getLotrapiInstance(),
                    Singletons.getSharedPreferencesInstance(context),
                    Singletons.getGsonInstance());
        }
        return lotrRepositoryInstance;
    }

    public void getAllCharacters(final LOTRCallback callback, String token) {
        List<Character> characters = getCharactersFromCache();
        if (characters != null) {
            callback.onSuccess(characters);
        } else {
            lotrapi.getAllCharacters(token).enqueue(new Callback<LOTRAPIResponse>() {
                @Override
                public void onResponse(Call<LOTRAPIResponse> call, Response<LOTRAPIResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Character> tmp = response.body().getDocs();
                        Collections.sort(tmp); // sort alphabetical order
                        saveCharactersList(tmp);
                        callback.onSuccess(tmp);
                    } else {
                        callback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<LOTRAPIResponse> call, Throwable t) {
                    callback.onFailed();
                }
            });
        }
    }

    private List<Character> getCharactersFromCache() {
        String jsonCharacters = sharedPreferences.getString(Constants.CHARACTERS_SHAREDPREF_LIST, null);
        if (jsonCharacters == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Character>>() {
            }.getType();
            return gson.fromJson(jsonCharacters, listType);
        }
    }

    private void saveCharactersList(List<Character> characters) {
        String jsonCharacters = gson.toJson(characters);
        sharedPreferences
                .edit()
                .putString(Constants.CHARACTERS_SHAREDPREF_LIST, jsonCharacters)
                .apply();
    }
}
