package com.ranidu.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesListViewHolder>{
    Context context;
    List<NotesModel> notes;
    private NotesListViewHolder.RecycleViewClickListener clickListener;
    public NotesAdapter(Context context, List<NotesModel> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new NotesListViewHolder(layout, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListViewHolder holder, int position) {
        String id = String.valueOf(notes.get(position).getId());
        String title = notes.get(position).getTitle().toString();
        String description = notes.get(position).getDescription().toString();

        holder.tv_title.setText(title);
        holder.tv_description.setText(description);

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateNoteActivity.class);

                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("description", description);

                // starting our activity.
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NotesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_title, tv_description;
        public NotesListViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
        }

        @Override
        public void onClick(View v) {

        }

        public interface RecycleViewClickListener {
        }
    }

}
