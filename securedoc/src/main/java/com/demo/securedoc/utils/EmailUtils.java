package com.demo.securedoc.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        return "Hello " + name + ",\n\n" + "We received a request to reset your password. Please click the link below to reset it:\n\n" + getVerificationUrl(host, token) + "\n\n" + "If you did not request a password reset, you can safely ignore this email.\n\n" + "Best regards,\n" + "Your Support Team";
    }

    public static String getResetPasswordEmail(String name, String host, String token) {
        return "Hello " + name + ",\n\n" + "We received a request to reset your password. Please click the link below to reset it:\n\n" + getResetPasswordUrl(host, token) + "\n\n" + "If you did not request a password reset, you can safely ignore this email.\n\n" + "Best regards,\n" + "Your Support Team";
    }

    private static String getVerificationUrl(String host, String token) {
        //return "http://" + host + ":8080/securedoc/email/verify?token=" + token;
        return host + "/verify/account?token=" + token;
    }

    private static String getResetPasswordUrl(String host, String token) {
        //return "http://" + host + ":8080/securedoc/email/verify?token=" + token;
        return host + "/verify/password?token=" + token;
    }

}
