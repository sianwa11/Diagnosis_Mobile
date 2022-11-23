package com.vanessa.diagnosis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationBarView;
import com.vanessa.diagnosis.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private Context context;
    public static final String API_URI = "https://177f-105-163-1-89.in.ngrok.io";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        replaceFragment(new ExploreFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.explore:
                    replaceFragment(new ExploreFragment());
                    break;

                case R.id.book:
                    showBottomSheetDialog();
                    break;

                case R.id.history:
                    replaceFragment(new HistoryFragment());
                    break;
            }
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.signout:
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = MainActivity.this.getSharedPreferences("shared preferences", MainActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("user");
                editor.apply();
                finish();
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);

//        LinearLayout bookAppoitment = bottomSheetDialog.findViewById(R.id.bookAppointment);
        LinearLayout symptomChecker = bottomSheetDialog.findViewById(R.id.symptomChecker);
        bottomSheetDialog.show();

        assert symptomChecker != null;
        symptomChecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SymptomCheckerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}