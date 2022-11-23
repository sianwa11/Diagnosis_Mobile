package com.vanessa.diagnosis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class SymptomCheckerActivity extends AppCompatActivity {


    private EditText symptomsTxt;
    private Button symptomsBtn;
    private RecyclerView symptomsRecView;
    private ProgressBar symptomsProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_checker);

        initViews();

        symptomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String symptoms = symptomsTxt.getText().toString();

                if (symptoms.isEmpty()) {
                    Toast.makeText(SymptomCheckerActivity.this, "Please fill in the empty values", Toast.LENGTH_SHORT).show();
                    return;
                }

                searchSymptoms(symptoms);

            }
        });

    }

    private void searchSymptoms(String symptoms) {
        symptomsBtn.setEnabled(false);
        symptomsProgress.setVisibility(View.VISIBLE);
        Toast.makeText(SymptomCheckerActivity.this, symptoms, Toast.LENGTH_SHORT).show();
        SymptomDataService symptomDataService = new SymptomDataService(SymptomCheckerActivity.this);

        symptomDataService.searchSymptom(symptoms, new SymptomDataService.getAllSymptomsResponse() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ArrayList<Symptom> symptoms, String statusCode) {
                Log.d("Status Code", statusCode);

                switch (statusCode) {
                    case "200":
                        symptomsProgress.setVisibility(View.GONE);
                        SymptomsRecViewAdapter adapter = new SymptomsRecViewAdapter(SymptomCheckerActivity.this);
                        adapter.setSymptoms(symptoms);
                        symptomsRecView.setAdapter(adapter);
                        symptomsRecView.setLayoutManager(new LinearLayoutManager(SymptomCheckerActivity.this));
                        break;

                    default:
                        break;
                }
            }
        });

    }

    private void initViews() {
        symptomsTxt = findViewById(R.id.symptomsTxt);
        symptomsBtn = findViewById(R.id.symptomsBtn);
        symptomsRecView = findViewById(R.id.symptomsRecView);
        symptomsProgress = findViewById(R.id.symptomsProgress);
    }

}