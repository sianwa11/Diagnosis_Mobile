package com.vanessa.diagnosis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecViewAdapter extends RecyclerView.Adapter<HistoryRecViewAdapter.ViewHolder>  {

    private ArrayList<History> history = new ArrayList<>();
    private Context context;

    public HistoryRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCreated.setText(history.get(position).getCreated_at());
        holder.txtName.setText(history.get(position).getName());
        holder.txtType.setText(history.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtType, txtCreated;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtHistoryName);
            txtType = itemView.findViewById(R.id.txtHistoryType);
            txtCreated = itemView.findViewById(R.id.txtHistoryCreated);
        }
    }
}
