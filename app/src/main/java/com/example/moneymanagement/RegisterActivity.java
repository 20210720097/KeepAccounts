package com.example.moneymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {
    private EditText account, password;
    private Button register;
    protected SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将背景图和状态栏融合
        if (Build.VERSION.SDK_INT >= 21) {
            View decroView = getWindow().getDecorView();
            decroView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_register);
        account = findViewById(R.id.regiser_et_1);
        password = findViewById(R.id.register_et_2);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_data = account.getText().toString();
                String password_data = password.getText().toString();
                if (account_data.length()>2 && password_data.length() >= 8) {
                    editor = getSharedPreferences("accountData",MODE_PRIVATE).edit();
                    editor.putString("account_data",account_data);
                    editor.putString("password_data",password_data);
                    editor.apply();
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(RegisterActivity.this, StartActivity.class);
//                    intent.putExtra("account_data",account_data);
//                    intent.putExtra("password_data",password_data);
//                    startActivity(intent);
                }
                else if (account_data.length() <= 2) {
                    Toast.makeText(RegisterActivity.this,"请输入超过3位自负的用户名",Toast.LENGTH_SHORT).show();
                }
                else if (password_data.length() < 8) {
                    Toast.makeText(RegisterActivity.this,"请输入超过8位自负的密码",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}