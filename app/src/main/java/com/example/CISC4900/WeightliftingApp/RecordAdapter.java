package com.example.CISC4900.WeightliftingApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

class RecordAdapter extends ListAdapter<Record, RecordAdapter.RecordHolder> {

    private OnItemClickListener listener;

    public RecordAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Record> DIFF_CALLBACK = new DiffUtil.ItemCallback<Record>() {
        @Override
        public boolean areItemsTheSame(@NonNull Record oldItem, @NonNull Record newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Record oldItem, @NonNull Record newItem) {
            return  oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getExercise().equals(newItem.getExercise()) &&
                    oldItem.getWeight() == (newItem.getWeight()) &&
                    oldItem.getSets() == (newItem.getSets()) &&
                    oldItem.getReps() == (newItem.getReps());
        }
    };

    public Record getRecordAt(int position) {
        return getItem(position);
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record, parent, false);
        return new RecordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordHolder holder, int position) {
        Record currentRecord = getItem(position);

        holder.date.setText(String.valueOf(currentRecord.getDate()));
        holder.exercise.setText(currentRecord.getExercise());
        holder.weight.setText(String.valueOf((int)currentRecord.getWeight()));
        holder.sets.setText(String.valueOf((int)currentRecord.getSets()));
        holder.reps.setText(String.valueOf((int)currentRecord.getReps()));
    }

    public class RecordHolder extends RecyclerView.ViewHolder {

        public TextView date;
        public TextView exercise;
        public TextView weight;
        public TextView sets;
        public TextView reps;


        public RecordHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            exercise = itemView.findViewById(R.id.exercise);
            weight = itemView.findViewById(R.id.weight);
            sets = itemView.findViewById(R.id.sets);
            reps = itemView.findViewById(R.id.reps);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Record record);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
