package fr.sheepy.aboutlotr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private final OnItemClickListener listener;

    private List<Character> values;
    // Provide a suitable constructor (depends on the kind of dataset)
    CharactersAdapter(List<Character> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(values.get(position), listener);
    }

    public void add(int position, Character item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
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

    @Override
    public int getItemCount() {
        return values.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Character item);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
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
}
