package com.ranidu.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AllNotesActivity extends AppCompatActivity {
    NotesAdapter notesAdapter;
    ArrayList<NotesModel> notesData;
    RecyclerView rv_notes_list;
    Button add_note_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);
        rv_notes_list = findViewById(R.id.rv_notes_list);
        add_note_btn = findViewById(R.id.add_note_btn);

        add_note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllNotesActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        getData();
        setNotesDataAdapter();
    }

    private void setNotesDataAdapter() {
        notesAdapter = new NotesAdapter(AllNotesActivity.this, notesData);
        rv_notes_list.setAdapter(notesAdapter);
        rv_notes_list.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getData() {
        DBHelper db = new DBHelper(this);

        notesData = db.getAllNotes();
        System.out.println("notesData");
    }
}