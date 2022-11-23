package com.vanessa.diagnosis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SymptomsRecViewAdapter extends RecyclerView.Adapter<SymptomsRecViewAdapter.ViewHolder>{

    private ArrayList<Symptom> symptoms = new ArrayList<>();
    private Context context;

    public SymptomsRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptoms_list, parent, false);
        SymptomsRecViewAdapter.ViewHolder holder = new SymptomsRecViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtCategory.setText("Category of illness: " + symptoms.get(position).getCategory());
        holder.txtSymptom.setText("Symptom: " + symptoms.get(position).getSymptom());
        holder.txtSimilarSymptoms.setText("Similar symptoms: " + symptoms.get(position).getSimilarSymptomsList());


    }

    public void setSymptoms(ArrayList<Symptom> symptoms) {
        this.symptoms = symptoms;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return symptoms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSymptom, txtCategory, txtSimilarSymptoms;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSymptom = itemView.findViewById(R.id.txtSymptom);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtSimilarSymptoms = itemView.findViewById(R.id.txtSimilarSymptoms);
        }
    }
}
