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

    private TextView name, race, height, birth, death, spouse, realm, URL;
    private ImageView icon, gender;
    private Character character;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View characterView = inflater.inflate(R.layout.character_fragment, container, false);
        icon = characterView.findViewById(R.id.charaIcon);
        gender = characterView.findViewById(R.id.charaGender);
        name = characterView.findViewById(R.id.charaName);
        race = characterView.findViewById(R.id.charaRace);
        height = characterView.findViewById(R.id.charaHeight);
        birth = characterView.findViewById(R.id.charaBirth);
        death = characterView.findViewById(R.id.charaDeath);
        spouse = characterView.findViewById(R.id.charaSpouse);
        realm = characterView.findViewById(R.id.charaRealm);
        URL = characterView.findViewById(R.id.charaURL);
        return characterView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gson = new GsonBuilder()
                .setLenient()
                .create();

        String temp = CharacterFragmentArgs.fromBundle(getArguments()).getCharacterInfo();
        if (temp != null) {
            character = gson.fromJson(temp, Character.class);
            // icon du character
            icon.setImageResource(character.getRaceResource());

            // nom du character
            if (character.getName() != null && !character.getName().equals("")) {
                name.setText(character.getName());
            }

            // genre du character
            if (character.getGender().equals("Male")) {
                gender.setImageResource(R.drawable.boy);
            } else if (character.getGender().equals("Female")) {
                gender.setImageResource(R.drawable.boy);
            } else {
                gender.setVisibility(View.GONE);
            }

            // race du character
            if (character.getRace() != null) {
                race.setText(character.getRace());
            } else {
                race.setText("Unknown Race");
            }

            // height du character
            if (character.getHeight() != null && !character.getHeight().equals("")) {
                height.setText("Height: " + character.getHeight());
            } else {
                height.setVisibility(View.GONE);
            }

            // birth du character
            if (character.getBirth() != null && !character.getBirth().equals("")) {
                birth.setText("Birth: " + character.getBirth());
            } else {
                birth.setVisibility(View.GONE);
            }

            // death du character
            if (character.getDeath() != null && !character.getDeath().equals("")) {
                death.setText("Death: " + character.getDeath());
            } else {
                death.setVisibility(View.GONE);
            }

            // Spouse du character
            if (character.getSpouse() != null && !character.getSpouse().equals("")) {
                spouse.setText("Spouse: " + character.getSpouse());
            } else {
                spouse.setVisibility(View.GONE);
            }

            // Realm du character
            if (character.getRealm() != null && !character.getRealm().equals("")) {
                realm.setText("Realm: " + character.getRealm());
            } else {
                realm.setVisibility(View.GONE);
            }

            // URL du character
            if (character.getWikiUrl() != null && !character.getWikiUrl().equals("")) {
                URL.setText("Wiki link:\n" + character.getWikiUrl());
            } else {
                URL.setText("Unknown Wiki Url");
            }

        }
    }
}
