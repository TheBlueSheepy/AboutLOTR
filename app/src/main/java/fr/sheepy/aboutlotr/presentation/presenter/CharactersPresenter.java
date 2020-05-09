package fr.sheepy.aboutlotr.presentation.presenter;

import java.util.List;

import androidx.navigation.fragment.NavHostFragment;
import fr.sheepy.aboutlotr.Constants;
import fr.sheepy.aboutlotr.Singletons;
import fr.sheepy.aboutlotr.data.LOTRCallback;
import fr.sheepy.aboutlotr.data.LOTRRepository;
import fr.sheepy.aboutlotr.presentation.model.Character;
import fr.sheepy.aboutlotr.presentation.view.CharactersFragment;
import fr.sheepy.aboutlotr.presentation.view.CharactersFragmentDirections;

public class CharactersPresenter {
    private LOTRRepository lotrRepository;
    private CharactersFragment view;

    public CharactersPresenter(CharactersFragment view, LOTRRepository lotrRepository) {
        this.lotrRepository = lotrRepository;
        this.view = view;
    }

    public void onStart() {
        lotrRepository.getAllCharacters(new LOTRCallback() {
            @Override
            public void onSuccess(List<Character> response) {
                view.showList(response);
            }

            @Override
            public void onFailed() {
                view.showError();
            }
        }, Constants.TOKEN);
    }

    public void onCharacterClick(Character c) {
        CharactersFragmentDirections.SeeCharacterDetails action = CharactersFragmentDirections
                .seeCharacterDetails(Singletons.getGsonInstance().toJson(c, Character.class));
        NavHostFragment.findNavController(view.getParentFragment())
                .navigate(action);
    }

    public interface View {
        void showList(List<Character> characters);

        void showError();
    }
}
