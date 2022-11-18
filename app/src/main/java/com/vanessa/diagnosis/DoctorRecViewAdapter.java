package com.vanessa.diagnosis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DoctorRecViewAdapter extends RecyclerView.Adapter<DoctorRecViewAdapter.ViewHolder> {

    private ArrayList<Doctor> doctors = new ArrayList<>();
    private Context context;

    public DoctorRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtFirstName.setText(doctors.get(position).getFirstName());
        holder.txtLastName.setText(doctors.get(position).getLastName());
        holder.txtEmail.setText(doctors.get(position).getEmail());
        holder.txtSpecialty.setText(doctors.get(position).getSpecialty());

        Glide.with(context).asBitmap().load(doctors.get(position).getLicense())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.image);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DoctorActivity.class);
                intent.putExtra(DoctorActivity.DOCTOR_ID, doctors.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private TextView txtFirstName, txtLastName, txtEmail, txtSpecialty;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.doctorListParent);
            txtFirstName = itemView.findViewById(R.id.txtFirstName);
            txtLastName = itemView.findViewById(R.id.txtLastName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtSpecialty = itemView.findViewById(R.id.txtSpecialty);
            image = itemView.findViewById(R.id.drImage);

        }
    }
}
