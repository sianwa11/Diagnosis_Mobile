package com.vanessa.diagnosis;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppointmentDataService {

    public static final String MAKE_APPOINTMENT_URI = "https://f746-197-231-178-123.in.ngrok.io/api/appointment";
    private final Context context;
    private int mStatusCode = 0;

    public AppointmentDataService(Context context) {
        this.context = context;
    }

    public interface appointmentResponse {
        void onError(String message);

        void onResponse(String statusCode);
    }

    public void makeAppointment(String title, String description, String date, Integer doctor_id, appointmentResponse appointmentResponse) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://f746-197-231-178-123.in.ngrok.io/api/appointment", response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Toast.makeText(context, "Success: " + jsonObject, Toast.LENGTH_LONG).show();
                Log.d("Response", jsonObject.toString());
                appointmentResponse.onResponse(String.valueOf(mStatusCode));
            } catch (Exception error) {
                error.printStackTrace();
            }
        }, error -> {
            Toast.makeText(context, "Error: " + error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("to_userid", String.valueOf(doctor_id));
                params.put("name", title);
                params.put("description", description);
                params.put("appointment_date", date);
                return params;
            }
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer 8|eCIUe6FwYEZn42HcGzSsD7ZDRStpL4Zn0PiSAwCU");
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
