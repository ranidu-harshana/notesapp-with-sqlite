package com.ranidu.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "notesdb";
    public DBHelper(Context context) {
        super(context, DBNAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT, email TEXT, phone TEXT)");
        db.execSQL("CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS notes");
    }

    public boolean insertUser(String name, String password, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("phone", phone);

        long result = db.insert("users", null, contentValues);
        return result != -1;
    }

    public boolean insertNote(NotesModel note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("description", note.getDescription());
        long result = db.insert("notes", null, contentValues);
        return result != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? and password=?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public ArrayList getAllNotes() {
        ArrayList<NotesModel> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes", null);
        if(cursor.moveToFirst()) {
            do {
                NotesModel note = new NotesModel();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setDescription(cursor.getString(2));

                notes.add(note);
            }while(cursor.moveToNext());
        }
        return notes;
    }

    public boolean updateNote(String id, String title, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("description", description);

        int i = db.update("notes", values, "id=?", new String[]{id});
        db.close();

        return i == 1;
    }

    public void deleteNote(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("notes", "id=?", new String[]{id});
        db.close();
    }

}
