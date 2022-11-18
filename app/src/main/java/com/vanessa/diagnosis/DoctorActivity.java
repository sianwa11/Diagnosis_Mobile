package com.vanessa.diagnosis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class DoctorActivity extends AppCompatActivity {

    public static final String DOCTOR_ID = "doctorId";

    private TextView txtDoctorName, txtDoctorContact, txtSpecialty, txtDescription;
    private Button btnMakeAppointment, btnAddToFavourites;
    private ImageView doctorLicenseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        initViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        if (null != intent){
            int doctorId = intent.getIntExtra(DOCTOR_ID, -1);

            if (doctorId != -1) {
                Doctor incomingDoctor = DoctorUtils.getInstance().getDoctorById(doctorId);
                if (null != incomingDoctor) {
                    setData(incomingDoctor);
                }
            }
        }

        btnMakeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert intent != null;
                int doctorId = intent.getIntExtra(DOCTOR_ID, -1);
                Intent intent = new Intent(DoctorActivity.this, MakeAppointment.class);
                Doctor incomingDoctor = DoctorUtils.getInstance().getDoctorById(doctorId);
                intent.putExtra(DOCTOR_ID, incomingDoctor.getId());
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setData(Doctor incomingDoctor) {
        txtDoctorName.setText("Dr. " + incomingDoctor.getFirstName() + " " + incomingDoctor.getLastName());
        txtDoctorContact.setText(incomingDoctor.getMobile() + ", " + incomingDoctor.getEmail());
        txtSpecialty.setText(incomingDoctor.getSpecialty());
        txtDescription.setText(incomingDoctor.getDescription());
        Glide.with(this)
                .asBitmap()
                .load(incomingDoctor.getLicense())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(doctorLicenseImage);
    }

    private void initViews() {
        txtDoctorName = findViewById(R.id.txtDoctorName);
        txtDoctorContact = findViewById(R.id.txtDoctorContact);
        txtSpecialty = findViewById(R.id.txtSpecialty);
        txtDescription = findViewById(R.id.txtDescription);

        btnMakeAppointment = findViewById(R.id.btnMakeAppointment);
        btnAddToFavourites = findViewById(R.id.btnAddToFavourites);

        doctorLicenseImage = findViewById(R.id.doctorLicenseImage);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}