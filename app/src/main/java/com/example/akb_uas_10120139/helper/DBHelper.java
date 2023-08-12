// Nim : 10120139
// Nama : Mochamad Ridho
// Kelas : IF4
package com.example.akb_uas_10120139.helper;

import com.example.akb_uas_10120139.model.Note;
import com.example.akb_uas_10120139.model.User;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper {
    public static void saveUser(DatabaseReference DB, String userId, String name, String email) {
        User user = new User(name, email);

        DB.child("users")
                .child(userId)
                .setValue(user);
    }

    public static void saveNotes(DatabaseReference DB, String userId, String title, String category, String note) {
        SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        String now = ISO_8601_FORMAT.format(new Date());

        Note notes = new Note(userId, title, note, category, now, now);

        DB.child("notes")
                .child(userId)
                .child(category)
                .child(title)
                .setValue(notes);
    }

    public static void deleteNoteCategory(DatabaseReference DB, String userId, String category) {
        DB.child("notes")
                .child(userId)
                .child(category)
                .removeValue();
    }

    public static void deleteNote(DatabaseReference DB, String userId, String category, String title) {
        DB.child("notes")
                .child(userId)
                .child(category)
                .child(title)
                .removeValue();
    }
}
