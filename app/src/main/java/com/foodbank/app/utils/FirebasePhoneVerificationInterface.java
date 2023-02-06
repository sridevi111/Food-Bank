package com.foodbank.app.utils;

public interface FirebasePhoneVerificationInterface {
    void onCodeSent(String code, String exception);
}
