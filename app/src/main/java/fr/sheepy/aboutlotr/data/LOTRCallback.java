package fr.sheepy.aboutlotr.data;

import java.util.List;

import fr.sheepy.aboutlotr.presentation.model.Character;

public interface LOTRCallback {
    void onSuccess(List<Character> response);

    void onFailed();
}
