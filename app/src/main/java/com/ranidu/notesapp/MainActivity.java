package com.ranidu.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_login_email, et_login_pass;
    Button login_btn;
    TextView tv_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_email = findViewById(R.id.et_update_title);
        et_login_pass = findViewById(R.id.et_update_description);
        login_btn = findViewById(R.id.update_btn);
        tv_signup = findViewById(R.id.tv_signup);

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login () {
        String email, password;
        email = et_login_email.getText().toString();
        password = et_login_pass.getText().toString();

        DBHelper dbHelper;
        dbHelper = new DBHelper(this);

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        } else {
            if(dbHelper.checkEmail(email)) {
                if (dbHelper.checkEmailPassword(email, password)) {
                    Intent intent = new Intent(MainActivity.this, AllNotesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }
}