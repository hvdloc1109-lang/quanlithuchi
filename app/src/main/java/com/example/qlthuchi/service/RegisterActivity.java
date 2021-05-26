package com.example.qlthuchi.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuchi.R;
import com.example.qlthuchi.data.MyDatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password;
    Button btnSave;
    MyDatabaseHelper db;

    private void init() {
        username = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        btnSave = findViewById(R.id.btnRegister);
        db = new MyDatabaseHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = username.getText().toString();
                String pass = password.getText().toString();
                if(username.equals("") || password.equals("") || us.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Hãy điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkus = db.checkUsername(us);
                    if(checkus == true){
                        Boolean insert = db.insert(us, pass);
                        if(insert == true) {
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(com.example.qlthuchi.service.RegisterActivity.this, com.example.qlthuchi.service.LoginActivity.class));
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Tài khoản này đã được sữ dụng , hãy chọn tài khoản khác.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }




}
