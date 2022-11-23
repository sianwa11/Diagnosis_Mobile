package com.vanessa.diagnosis;

import java.util.ArrayList;

public class UserUtils {

    private static UserUtils instance;

    private static ArrayList<User> loggedInUser;


    private UserUtils() {
        if (null == loggedInUser) {
            loggedInUser = new ArrayList<>();
            initData();
        }
    }

    public void addLoggedInUser(User user) {
        loggedInUser.add(user);
    }

    private void initData() {

    }

    public static UserUtils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new UserUtils();
            return instance;
        }
    }

    public static ArrayList<User> getLoggedInUser() {
        return loggedInUser;
    }

    public String getToken () {
        for (User user : loggedInUser) {
            return user.getToken();
        }
        return null;
    }

}
