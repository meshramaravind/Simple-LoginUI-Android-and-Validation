package com.arvind.loginui.model;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    String email;
    String password;
    String ErrorEmail;
    String ErrorPassword;

    public String getErrorEmail() {
        return ErrorEmail;
    }

    public void setErrorEmail(String errorEmail) {
        ErrorEmail = errorEmail;
    }

    public String getErrorPassword() {
        return ErrorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        ErrorPassword = errorPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidEmail() {
        if (this.email != null && !TextUtils.isEmpty(email) && !isValidedEmail(email)) {
            return true;
        }

        return false;
    }

    private boolean isValidedEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean isValidPassword() {
        if (this.password != null && !TextUtils.isEmpty(password) && !isValidetPassword(password)) {
            return true;
        }
        return false;
    }

    private static boolean isValidetPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
