package fr.sheepy.aboutlotr.presentation.presenter;

import fr.sheepy.aboutlotr.presentation.model.Character;
import fr.sheepy.aboutlotr.presentation.view.CharacterFragment;

public class CharacterPresenter {
    private Character character;
    private CharacterFragment view;

    public CharacterPresenter(CharacterFragment view, Character character) {
        this.character = character;
        this.view = view;
    }

    public void onStart() {
        view.showCharacterInfos(character);
    }

    public interface View {
        void showCharacterInfos(Character character);
    }

}
