package fr.sheepy.aboutlotr;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharactersFragment extends Fragment {
    private RecyclerView recyclerView;
    private CharactersAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    static final String BASE_URL = "https://the-one-api.herokuapp.com/v1/";
    static final String TOKEN = "56A91xRjOJ5hU3Zxtxry";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View charactersFragment = inflater.inflate(R.layout.characters_fragment, container, false);
        recyclerView = charactersFragment.findViewById(R.id.character_recycler_view);
        return charactersFragment;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MakeApiCall();
    }

    private void showList(List<Character> characters) {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CharactersAdapter(characters);
        recyclerView.setAdapter(mAdapter);
    }

    private void MakeApiCall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        LOTRAPI lotrAPI = retrofit.create(LOTRAPI.class);

        Call<LOTRAPIResponse> call = lotrAPI.getAllCharacter("Bearer " + TOKEN);
        call.enqueue(new Callback<LOTRAPIResponse>() {
            @Override
            public void onResponse(Call<LOTRAPIResponse> call, Response<LOTRAPIResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<Character> characters = response.body().getDocs();
                    Toast.makeText(getContext(),"API Success",Toast.LENGTH_SHORT).show();
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

    private void showError() {
        Toast.makeText(getContext(),"API Error",Toast.LENGTH_SHORT).show();
    }
}
