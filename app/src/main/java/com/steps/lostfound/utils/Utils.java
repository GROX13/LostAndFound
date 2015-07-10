package com.steps.lostfound.utils;

import android.text.TextUtils;

public class Utils {

    public static boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() > 4;
    }

    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && email.contains("@");
    }

}
