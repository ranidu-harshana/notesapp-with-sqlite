package com.ranidu.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    TextView tv_signin;
    Button register_btn;
    EditText et_name, et_password, et_cpassword, et_email, et_phone;

    private String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String PasswordPattern = "[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_btn = findViewById(R.id.register_btn);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        et_cpassword = findViewById(R.id.et_cpassword);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        tv_signin = findViewById(R.id.tv_signin);

        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String name, password, email, phone, cpassword;
        DBHelper db;
        name = et_name.getText().toString();
        password = et_password.getText().toString();
        cpassword = et_cpassword.getText().toString();
        email = et_email.getText().toString();
        phone = et_phone.getText().toString();
        db = new DBHelper(this);

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        } else {
            if(cpassword.equals(password)) {
                if (email.matches(EmailPattern)) {
                    if (password.matches(PasswordPattern)) {
                        if (db.checkEmail(email)) {
                            Toast.makeText(RegisterActivity.this, "This email is already taken", Toast.LENGTH_SHORT).show();
                        } else {
                            if (db.insertUser(name, password, email, phone)) {
                                Intent intent = new Intent(RegisterActivity.this, AllNotesActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password must be include simple, capital and special character and must be between 8-24 characters", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}