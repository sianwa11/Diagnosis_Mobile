package com.vanessa.diagnosis;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DoctorDataService {

    public static final String QUERY_FOR_DOCTORS = "https://7a1e-196-202-173-158.eu.ngrok.io/api/doctor";

    public Context context;

    public DoctorDataService(Context context) {
        this.context = context;
    }

    public interface getAllDoctorsResponse {
        void onError(String message);

        void onResponse(ArrayList<Doctor> doctors);
    }

    public void getAllDoctors(getAllDoctorsResponse getAllDoctorsResponse) {
        ArrayList<Doctor> doctors = new ArrayList<>();

        // Get array of doctors

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, QUERY_FOR_DOCTORS, null,
                response -> {
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            Doctor doctor = new Doctor();
                            JSONObject doctorObject = response.getJSONObject(i);
                            doctor.setId(doctorObject.getInt("id"));
                            doctor.setFirstName(doctorObject.getString("firstname"));
                            doctor.setLastName(doctorObject.getString("lastname"));
                            doctor.setEmail(doctorObject.getString("email"));

                            if (doctorObject.has("doctor_data") && !doctorObject.isNull("doctor_data")) {
                                doctor.setSpecialty(doctorObject.getJSONObject("doctor_data").getString("specialty"));
                                doctor.setLicense(doctorObject.getJSONObject("doctor_data").getString("license"));
                                doctor.setLocation(doctorObject.getJSONObject("doctor_data").getString("location"));
                                doctor.setDescription(doctorObject.getJSONObject("doctor_data").getString("description"));
                                doctor.setJoined(doctorObject.getJSONObject("doctor_data").getString("created_at"));


                                Log.d("TEST", doctorObject.getJSONObject("doctor_data").getString("license"));
                            }

                            doctors.add(doctor);
                        }

                        getAllDoctorsResponse.onResponse(doctors);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                });

        MySingleton.getInstance(context).addToRequestQueue(request);

        // Get each item in array and populate Doctor Model
    }
}
