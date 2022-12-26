package com.ranidu.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNoteActivity extends AppCompatActivity {
    EditText et_update_title, et_update_description;
    Button update_btn, delete_btn;
    String id, title, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        et_update_title = findViewById(R.id.et_update_title);
        et_update_description = findViewById(R.id.et_update_description);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");

        et_update_title.setText(title);
        et_update_description.setText(description);
        DBHelper dbHelper = new DBHelper(UpdateNoteActivity.this);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_update_title = findViewById(R.id.et_update_title);
                et_update_description = findViewById(R.id.et_update_description);

                String title, description;
                title = et_update_title.getText().toString();
                description = et_update_description.getText().toString();

                if(dbHelper.updateNote(id, title, description)) {
                    Intent intent = new Intent(UpdateNoteActivity.this, AllNotesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdateNoteActivity.this, "Edit Note Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteNote(id);
                Intent intent = new Intent(UpdateNoteActivity.this, AllNotesActivity.class);
                startActivity(intent);
            }
        });
    }
}