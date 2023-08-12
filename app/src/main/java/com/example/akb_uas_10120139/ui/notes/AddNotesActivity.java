// Nim : 10120139
// Nama : Mochamad Ridho
// Kelas : IF4
package com.example.akb_uas_10120139.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.akb_uas_10120139.Config;
import com.example.akb_uas_10120139.MainActivity;
import com.example.akb_uas_10120139.R;
import com.example.akb_uas_10120139.helper.DBHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddNotesActivity extends AppCompatActivity {

    private DatabaseReference DB;
    private FirebaseAuth Auth;
    private EditText fTitle;
    private EditText fCategory;
    private EditText fNote;
    private Button bSave;
    private Button bBack;
    private String previousActivity;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        DB = FirebaseDatabase.getInstance(Config.getDB_URL()).getReference();
        Auth = FirebaseAuth.getInstance();

        Intent i = getIntent();
        this.previousActivity = i.getStringExtra("backTo");
        this.category = i.getStringExtra("category");

        // Set component
        fTitle = findViewById(R.id.add_note_title);
        fCategory = findViewById(R.id.add_note_category);
        fNote = findViewById(R.id.add_note_description);
        bSave = findViewById(R.id.add_note_save_btn);
        bBack = findViewById(R.id.add_note_back_btn);

        // Set category
        fCategory.setText(this.category);

        // Save button on click
        bSave.setOnClickListener(v -> {
            DBHelper.saveNotes(DB,
                        Auth.getCurrentUser().getUid(),
                        fTitle.getText().toString(),
                        fCategory.getText().toString(),
                        fNote.getText().toString()
                    );

            // Make alert
            Toast.makeText(AddNotesActivity.this, "Data created !",
                    Toast.LENGTH_SHORT).show();

            if(this.previousActivity.compareTo("note_category") == 0) {
                goToMainActivity();
            } else {
                goToNoteDetail(this.category);
            }
        });

        // Back button on click
        bBack.setOnClickListener(v -> {
            if(this.previousActivity.compareTo("note_category") == 0) {
                goToMainActivity();
            } else {
                goToNoteDetail(this.category);
            }
        });
    }

    public void goToMainActivity() {
        Intent intent = new Intent(AddNotesActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void goToNoteDetail(String category) {
        Intent intent = new Intent(AddNotesActivity.this, DetailNotesActivity.class);
        intent.putExtra("category", category);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}