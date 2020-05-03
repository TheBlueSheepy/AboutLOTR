package fr.sheepy.aboutlotr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CharacterFragment extends Fragment {

    private TextView name, race, birth, death;
    private ImageView icon, gender;
    private Character character;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View characterView = inflater.inflate(R.layout.character_fragment, container, false);
        icon = characterView.findViewById(R.id.charaIcon);
        name = characterView.findViewById(R.id.charaName);
        race = characterView.findViewById(R.id.charaRace);
        birth = characterView.findViewById(R.id.charaBirth);
        death = characterView.findViewById(R.id.charaDeath);
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
            icon.setImageResource(character.getRaceResource());
            name.setText(character.getName());
            race.setText(character.getRace());
            birth.setText(character.getBirth());
            death.setText(character.getDeath());
        }
    }

}
