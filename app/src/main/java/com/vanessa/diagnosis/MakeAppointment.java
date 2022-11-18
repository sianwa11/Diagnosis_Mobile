package com.vanessa.diagnosis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeAppointment extends AppCompatActivity {

    private Button btnCancelAppointment, btnConfirmAppointment;
    private EditText appointmentTitle, appointmentDescription;
    private CalendarView appointmentDate;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        initViews();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 2);
        appointmentDate.setMinDate(cal.getTimeInMillis());

        btnCancelAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnConfirmAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String selectedDate = sdf.format(new Date(appointmentDate.getDate()));

                String title = appointmentTitle.getText().toString();
                String description = appointmentDescription.getText().toString();

                if (title.isEmpty()
                        || description.isEmpty()
                        || selectedDate.isEmpty()) {
                    Toast.makeText(MakeAppointment.this, "Please fill in the empty values", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = getIntent();
                if (null != intent) {
                    int doctorId = intent.getIntExtra(DoctorActivity.DOCTOR_ID, -1);

                    if (doctorId != -1) {
                        makeAppointment(title, description, selectedDate, doctorId);
                    }
                }
            }
        });
    }

    private void makeAppointment(String appointmentTitle, String appointmentDescription, String appointmentDate, int doctorId) {
        btnConfirmAppointment.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        AppointmentDataService appointmentDataService = new AppointmentDataService(MakeAppointment.this);

        appointmentDataService.makeAppointment(appointmentTitle, appointmentDescription, appointmentDate, doctorId, new AppointmentDataService.appointmentResponse() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(String statusCode) {
                Log.d("Status Code", statusCode);

                switch (statusCode) {
                    case "201":
                        // TODO: Redirect to appointment history page
                        // TODO: Add notification if possible
                        // TODO: Add ProgressBar to explore fragment recycler view
                        finish();
                }
            }
        });
    }

    private void initViews() {
        btnCancelAppointment = findViewById(R.id.btnCancelAppointment);
        btnConfirmAppointment = findViewById(R.id.btnConfirmAppointment);
        appointmentTitle = findViewById(R.id.appointmentTitle);
        appointmentDescription = findViewById(R.id.appointmentDescription);
        appointmentDate = findViewById(R.id.appointmentDate);
        progressBar = findViewById(R.id.progressBar);
    }
}