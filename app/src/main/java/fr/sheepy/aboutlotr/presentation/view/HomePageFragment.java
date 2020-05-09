package fr.sheepy.aboutlotr.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import fr.sheepy.aboutlotr.R;
import fr.sheepy.aboutlotr.presentation.presenter.HomePagePresenter;

public class HomePageFragment extends Fragment implements HomePagePresenter.View {
    private HomePagePresenter presenter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePagePresenter(this);
        /*view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomePageFragment.this)
                        .navigate(R.id.HomeToCharacterList);
            }
        });*/
    }
}
