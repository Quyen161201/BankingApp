package com.jmc.bankapp.Controllers.Client;

public class LoginSession {
    private static String phoneNumber;
    private static String password;

    public static void setCredentials(String phone, String pass) {
        phoneNumber = phone;
        password = pass;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static String getPassword() {
        return password;
    }
}
