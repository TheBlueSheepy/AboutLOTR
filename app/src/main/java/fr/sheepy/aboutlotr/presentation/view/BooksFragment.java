package fr.sheepy.aboutlotr.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import fr.sheepy.aboutlotr.R;
import fr.sheepy.aboutlotr.presentation.presenter.BooksPresenter;

public class BooksFragment extends Fragment implements BooksPresenter.View {

    private BooksPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BooksPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.books_fragment, container, false);
    }
}
