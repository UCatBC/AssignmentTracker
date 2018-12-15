package com.example.user1.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Notes extends AppCompatActivity {

    EditText notesText;
    SqliteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        db = new SqliteHelper(this);
        notesText = (EditText)findViewById(R.id.editTextNotes);

        Intent intent = getIntent();
        String titleNotes = intent.getStringExtra("NOTES");

        Assignment view = db.getOneAssignment(titleNotes);

        notesText.setText(view.getNotesText());
    }
}
