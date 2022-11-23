package com.vanessa.diagnosis;

public class Symptom {
    private int id;
    private String symptom;
    private String category;
    private String similarSymptomsList;

    public Symptom(int id, String symptom, String category, String similarSymptomsList) {
        this.id = id;
        this.symptom = symptom;
        this.category = category;
        this.similarSymptomsList = similarSymptomsList;
    }

    public Symptom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSimilarSymptomsList() {
        return similarSymptomsList;
    }

    public void setSimilarSymptomsList(String similarSymptomsList) {
        this.similarSymptomsList = similarSymptomsList;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", symptom='" + symptom + '\'' +
                ", category='" + category + '\'' +
                ", similarSymptomsList='" + similarSymptomsList + '\'' +
                '}';
    }
}
