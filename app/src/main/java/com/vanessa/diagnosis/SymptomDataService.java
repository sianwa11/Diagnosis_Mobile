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

public class SymptomDataService {
    public static final String MAKE_SEARCH_URI = MainActivity.API_URI + "/api/symptoms/search/";
    private final Context context;
    private int mStatusCode = 0;

    public SymptomDataService(Context context) {
        this.context = context;
    }

    public interface appointmentResponse {
        void onError(String message);

        void onResponse(String statusCode);
    }

    public interface getAllSymptomsResponse {
        void onError(String message);

        void onResponse(ArrayList<Symptom> symptoms, String statusCode);
    }

    public void searchSymptom(String symptomTxt, getAllSymptomsResponse getAllSymptomsResponse) {
        ArrayList<Symptom> symptomsArrList = new ArrayList<>();
        // Get array of doctors
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, MAKE_SEARCH_URI + symptomTxt, null,
                response -> {
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            Symptom symptom = new Symptom();
                            JSONObject symptomObject = response.getJSONObject(i);

                            symptom.setId(symptomObject.getInt("id"));
                            symptom.setSymptom(symptomObject.getString("symptom"));
                            symptom.setCategory(symptomObject.getString("category"));
                            symptom.setSimilarSymptomsList(symptomObject.getString("similarSymptomsList"));



                                Log.d("TEST", symptomObject.toString());


                            symptomsArrList.add(symptom);
                            SymptomsUtils.getInstance().addToAllSymptoms(symptom);
                        }

                        getAllSymptomsResponse.onResponse(symptomsArrList, String.valueOf(mStatusCode));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
        }){
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                mStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(request);
    }

}
