package fr.sheepy.aboutlotr.presentation.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.sheepy.aboutlotr.Constants;
import fr.sheepy.aboutlotr.R;
import fr.sheepy.aboutlotr.data.LOTRAPI;
import fr.sheepy.aboutlotr.presentation.model.Character;
import fr.sheepy.aboutlotr.presentation.model.LOTRAPIResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharactersFragment extends Fragment {
    private RecyclerView recyclerView;
    private CharactersAdapter charactersAdapter;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View charactersFragment = inflater.inflate(R.layout.characters_fragment, container, false);
        recyclerView = charactersFragment.findViewById(R.id.character_recycler_view);
        setHasOptionsMenu(true);
        return charactersFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.charaters_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                charactersAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences(Constants.CHARACTERS_SHAREDPREF_NAME, Context.MODE_PRIVATE);

        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Character> characters = getCharactersFromCache();
        if (characters != null) {
            showList(characters);
        } else {
            MakeApiCall();
        }
    }

    private List<Character> getCharactersFromCache() {
        String jsonCharacters = sharedPreferences.getString(Constants.CHARACTERS_SHAREDPREF_LIST, null);
        if (jsonCharacters == null) {
            return null;
        } else {
            Log.i("API DATA RESTORED", "The API data has been successfully restored from cache.");
            Type listType = new TypeToken<List<Character>>() {
            }.getType();
            return gson.fromJson(jsonCharacters, listType);
        }
    }

    private void showList(List<Character> characters) {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        charactersAdapter = new CharactersAdapter(characters, new CharactersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Character item) {
                Log.i("Test item", "onItemClick: " + item.toString());
                CharactersFragmentDirections.SeeCharacterDetails action = CharactersFragmentDirections
                        .seeCharacterDetails(gson.toJson(item, Character.class));
                NavHostFragment.findNavController(getParentFragment())
                        .navigate(action);
            }
        });
        recyclerView.setAdapter(charactersAdapter);
    }

    private void MakeApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        LOTRAPI lotrAPI = retrofit.create(LOTRAPI.class);

        Call<LOTRAPIResponse> call = lotrAPI.getAllCharacter("Bearer " + Constants.TOKEN);
        call.enqueue(new Callback<LOTRAPIResponse>() {
            @Override
            public void onResponse(Call<LOTRAPIResponse> call, Response<LOTRAPIResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Character> characters = response.body().getDocs();
                    Log.i("API SUCCESS", response.message());

                    // sort alphabetical order
                    Collections.sort(characters);

                    saveList(characters);
                    showList(characters);
                } else {
                    Log.i("API ERROR", response.message());
                    showError();
                }
            }

            @Override
            public void onFailure(Call<LOTRAPIResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Character> characters) {
        String jsonCharacters = gson.toJson(characters);
        sharedPreferences
                .edit()
                .putString("jsonCharacters", jsonCharacters)
                .apply();
        Log.i("API DATA SAVED", "The API data has been successfully saved.");
    }

    private void showError() {
        Toast.makeText(getContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getActivity(), "sort !", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
