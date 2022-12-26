package com.ranidu.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    EditText et_add_title, et_add_description;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        et_add_title = findViewById(R.id.et_update_title);
        et_add_description = findViewById(R.id.et_update_description);
        add_btn = findViewById(R.id.update_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }

    private void addNote() {
        String title, description;
        title = et_add_title.getText().toString();
        description = et_add_description.getText().toString();
        NotesModel notesModel = new NotesModel();
        notesModel.setTitle(title);
        notesModel.setDescription(description);

        DBHelper db;
        db = new DBHelper(this);

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(AddNoteActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        } else {
            if(db.insertNote(notesModel)) {
                Intent intent = new Intent(AddNoteActivity.this, AllNotesActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(AddNoteActivity.this, "Failed to save note", Toast.LENGTH_SHORT).show();
            }
        }
    }
}