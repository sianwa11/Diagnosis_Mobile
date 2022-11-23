package com.vanessa.diagnosis;

import java.util.ArrayList;

public class SymptomsUtils {

    private static SymptomsUtils instance;

    private static ArrayList<Symptom> allSymptoms;


    private SymptomsUtils() {
        if (null == allSymptoms) {
            allSymptoms = new ArrayList<>();
            initData();
        }

    }

    public void addToAllSymptoms(Symptom symptom) {
        allSymptoms.add(symptom);
    }

    private void initData() {
    }

    public static SymptomsUtils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new SymptomsUtils();
            return instance;
        }
    }

    public static ArrayList<Symptom> getAllSymptoms() {
        return allSymptoms;
    }

}
