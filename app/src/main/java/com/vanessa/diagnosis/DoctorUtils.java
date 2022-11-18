package com.vanessa.diagnosis;

import android.content.Context;

import java.util.ArrayList;

public class DoctorUtils {


    private static DoctorUtils instance;

    private static ArrayList<Doctor> allDoctors;
    private static ArrayList<Doctor> favouriteDoctors;


    private DoctorUtils() {
        if (null == allDoctors) {
            allDoctors = new ArrayList<>();
            initData();
        }

        if (null == favouriteDoctors) {
            favouriteDoctors = new ArrayList<>();
        }

    }

    public void addToAllDoctors(Doctor doctors) {
        allDoctors.add(doctors);
    }

    private void initData() {
       // allDoctors.add(new Book(1, "1q84", "Haruku Maham",
               // 1300, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBQZSg-b2LFkLlt9fWndS1w8SONabDZBHf0dtdb3-bqcuKxduL", "A wrork of maddening brilliance", "Long Description"));
//        DoctorDataService doctorDataService = new DoctorDataService();

    }

    public static DoctorUtils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new DoctorUtils();
            return instance;
        }
    }

    public static ArrayList<Doctor> getAllBooks() {
        return allDoctors;
    }


    public static ArrayList<Doctor> getFavouriteBooks() {
        return favouriteDoctors;
    }

    public Doctor getDoctorById(int id) {
        for (Doctor d : allDoctors) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public boolean addToFavourites(Doctor doctor) {
        return favouriteDoctors.add(doctor);
    }

    public boolean removeFromFavourites(Doctor doctor) {
        return favouriteDoctors.remove(doctor);
    }


}
