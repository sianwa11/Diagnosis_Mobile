package com.vanessa.diagnosis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText txtMobile, txtPassword;
    Button btnCreateAcc, btnLogin;
    ArrayList<User> me = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCreateAcc = findViewById(R.id.btnCreateAcc);
        btnLogin = findViewById(R.id.btnLogin);
        txtMobile = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        loadData();


        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobile = txtMobile.getText().toString();
                String password = txtPassword.getText().toString();

                if (mobile.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill in the empty fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginUser(mobile, password);
            }
        });
    }

    private void loginUser(String mobile, String password) {

        // login user
        LoginDataService loginDataService = new LoginDataService(LoginActivity.this);
        loginDataService.loginUser(mobile, password, new LoginDataService.logInResponse() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ArrayList<User> user, String statusCode) {
                Log.d("Status Code", statusCode);
                Log.d("User", user.toString());

                saveData(user);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        // store in sharedprefs
    }

    private void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("user", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        me = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (me != null) {
            // if the array list is empty
            // creating a new array list.
//            courseModalArrayList = new ArrayList<>();
            for (User user : me) {
                UserUtils.getInstance().addLoggedInUser(user);
            }
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    private void saveData(ArrayList<User> user) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(user);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("user", json);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

        Toast.makeText(this, "Login successful ", Toast.LENGTH_SHORT).show();
    }


}