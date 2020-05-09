package fr.sheepy.aboutlotr.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.sheepy.aboutlotr.R;
import fr.sheepy.aboutlotr.data.LOTRRepository;
import fr.sheepy.aboutlotr.presentation.model.Character;
import fr.sheepy.aboutlotr.presentation.presenter.CharactersPresenter;

public class CharactersFragment extends Fragment implements CharactersPresenter.View {
    private CharactersPresenter presenter;
    private RecyclerView recyclerView;
    private CharactersAdapter charactersAdapter;

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

        presenter = new CharactersPresenter(this, LOTRRepository.getInstance(getContext()));
        presenter.onStart();
    }

    @Override
    public void showList(List<Character> characters) {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        charactersAdapter = new CharactersAdapter(characters, new CharactersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Character item) {
                presenter.onCharacterClick(item);
            }
        });
        recyclerView.setAdapter(charactersAdapter);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // nothing
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
