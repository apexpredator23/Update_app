package com.example.updateapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class MyAdapter extends FirestoreRecyclerAdapter<Passage, MyAdapter.PassageHolder> {
    private OnItemClickListener listener;


    public MyAdapter(@NonNull FirestoreRecyclerOptions<Passage> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyAdapter.PassageHolder holder, int position, @NonNull Passage model) {
        holder.textViewSubject.setText(model.getSubject());
        holder.textViewDescription.setText(model.getPassage());
    }

    @NonNull
    @Override
    public MyAdapter.PassageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.passage_item,
                parent, false);
        return new PassageHolder(v);
    }
    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class PassageHolder extends RecyclerView.ViewHolder {
        TextView textViewSubject;
        TextView textViewDescription;
//

        public PassageHolder(View itemView) {
            super(itemView);
            textViewSubject = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
