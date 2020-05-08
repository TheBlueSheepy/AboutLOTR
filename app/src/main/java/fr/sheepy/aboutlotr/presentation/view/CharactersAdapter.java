package fr.sheepy.aboutlotr.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import fr.sheepy.aboutlotr.R;
import fr.sheepy.aboutlotr.presentation.model.Character;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> implements Filterable {

    private final OnItemClickListener listener;

    private List<Character> characterList;
    private List<Character> characterListFull;
    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Character> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(characterListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Character item : characterListFull) {
                    if (item.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            characterList.clear();
            characterList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    CharactersAdapter(List<Character> characterList, OnItemClickListener listener) {
        this.characterList = characterList;
        characterListFull = new ArrayList<>(characterList);
        this.listener = listener;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(characterList.get(position), listener);
    }

    public void add(int position, Character item) {
        characterList.add(position, item);
        notifyItemInserted(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharactersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.character_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    private void remove(int position) {
        characterList.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickListener {
        void onItemClick(Character item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView character_name;
        TextView character_race;
        ImageView character_icon;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            character_icon = v.findViewById(R.id.character_icon);
            character_name = v.findViewById(R.id.character_name);
            character_race = v.findViewById(R.id.character_info);
        }

        public void bind(final Character item, final OnItemClickListener listener) {
            character_name.setText(item.getName());

            String race = item.getRace();
            if (race != null) {
                character_race.setText("Race: " + race);
                character_icon.setImageResource(item.getRaceResource());
            } else {
                character_race.setText("Race: Unknown");
                character_icon.setImageResource(R.drawable.unknown);
            }

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }
}
