package com.vanessa.diagnosis;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginDataService {

    public static final String LOGIN_URI = MainActivity.API_URI + "/api/login";
    private final Context context;
    private int mStatusCode = 0;

    public LoginDataService(Context context) {
        this.context = context;
    }

    public interface logInResponse {
        void onError(String message);

        void onResponse(ArrayList<User> user, String statusCode);
    }

    public void loginUser(String mobile, String password, logInResponse logInResponse) {
        ArrayList<User> user = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.POST, LOGIN_URI, response -> {
            try {

                JSONObject jsonObject = new JSONObject(response);
                User loggedInUser = new User();
                Toast.makeText(context, "Success: " + jsonObject, Toast.LENGTH_LONG).show();
                Log.d("Response", String.valueOf(jsonObject.getString("token")));

                loggedInUser.setToken(jsonObject.getString("token"));
                loggedInUser.setId(jsonObject.getJSONObject("user").getInt("id"));
                loggedInUser.setEmail(jsonObject.getJSONObject("user").getString("email"));
                loggedInUser.setFirstname(jsonObject.getJSONObject("user").getString("firstname"));
                loggedInUser.setLastname(jsonObject.getJSONObject("user").getString("lastname"));
                loggedInUser.setMobile(jsonObject.getJSONObject("user").getString("mobile"));
                loggedInUser.setRole(jsonObject.getJSONObject("user").getString("role"));

                user.add(loggedInUser);
                logInResponse.onResponse(user, String.valueOf(mStatusCode));

            } catch (Exception error) {
                error.printStackTrace();
            }
        }, error -> {
            Toast.makeText(context, "Error: " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobile);
                params.put("password", password);
                return params;
            }
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                return headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                mStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getInstance(context).addToRequestQueue(request);
    }

}
