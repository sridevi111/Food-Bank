package com.foodbank.app.utils;


public class FirebaseUtils {

    private static FirebaseUtils INSTANCE;

    public static FirebaseUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseUtils();
        }
        return INSTANCE;
    }


}
