// Nim : 10120139
// Nama : Mochamad Ridho
// Kelas : IF4
package com.example.akb_uas_10120139.helper;

public class StringHelper {
    // Generate Username From Email
    public static String usernameFromEmail(String email) {
        if (!email.contains("@")) return email;

        return email.split("@")[0];
    }
}
