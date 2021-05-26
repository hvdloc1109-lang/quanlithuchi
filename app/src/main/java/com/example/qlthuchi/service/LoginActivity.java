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

public class LoginActivity extends AppCompatActivity {
    private EditText txtUserName, txtPassWord;
    private Button btnLogin, btnRegister;
    private MyDatabaseHelper db;

    public void init() {
        txtUserName = findViewById(R.id.edUsername);
        txtPassWord = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        db = new MyDatabaseHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUserName.getText().toString();
                String passWord = txtPassWord.getText().toString();
                if (userName.equals("")) {
                    txtUserName.setError("Bạn hãy nhập tài khoản");
                    txtUserName.requestFocus();
                } else if (passWord.equals("")) {
                    txtPassWord.setError("Hãy nhập mật khẩu");
                    txtPassWord.requestFocus();
                }
                if (db.checkLogin(userName, passWord) == true) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Tài khoản không chính xác , hãy nhập lại", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
//    boolean CheckEmail(String email) {
//        String EMAIL_PATTERN = "^[a-z]{1,}([0-9]{0,})+@nuce\\.edu\\.vn$";
//        return Pattern.matches(EMAIL_PATTERN, email);
//    }
}