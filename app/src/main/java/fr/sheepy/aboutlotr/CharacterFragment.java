package fr.sheepy.aboutlotr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CharacterFragment extends Fragment {

    private TextView infos;
    private Character character;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View characterView = inflater.inflate(R.layout.character_fragment, container, false);
        infos = characterView.findViewById(R.id.characInfo);
        return characterView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gson = new GsonBuilder()
                .setLenient()
                .create();

        assert getArguments() != null;
        String temp = CharacterFragmentArgs.fromBundle(getArguments()).getCharacterInfo();
        if (temp != null) {
            character = gson.fromJson(temp, Character.class);
            infos.setText(character.toString());
        }
    }

}
