package com.example.moneymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity {
    private EditText account, password;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        Log.d("StartActivity", "onCreate: 执行" );
        // 将背景图和状态栏融合
        if (Build.VERSION.SDK_INT >= 21) {
            View decroView = getWindow().getDecorView();
            decroView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        account = findViewById(R.id.start_et_1);
        password = findViewById(R.id.start_et_2);
        Button login = findViewById(R.id.start_login);
        Button register = findViewById(R.id.start_register);
        CheckBox rememberPassword = findViewById(R.id.rememner_password);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String acc_data = pref.getString("account_data","");
            String pw_data = pref.getString("password_data","");
            account.setText(acc_data);
            password.setText(pw_data);
            rememberPassword.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = account.getText().toString();
                String pw = password.getText().toString();
//                // 使用intent接收上一个活动的数据
//                Intent intent1 = getIntent();
//                String acc_data = intent1.getStringExtra("account_data");
//                String pw_data = intent1.getStringExtra("password_data");
                // 使用sharedPreference读取数据
                SharedPreferences preferences = getSharedPreferences("accountData",MODE_PRIVATE);
                String acc_data = preferences.getString("account_data",null);
                String pw_data = preferences.getString("password_data",null);

                if (acc.equals(acc_data) && pw.equals(pw_data)){
                    editor = pref.edit();
                    if (rememberPassword.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account_data", acc);
                        editor.putString("password_data", pw);
                    }
                    else {
                        editor.clear();
                    }
                    editor.apply();
                    // 登录后再按返回键不再回到登录页面
                    Intent intent=new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClass(StartActivity.this,MainActivity.class);
                    startActivity(intent);

                }
                else if (!acc.equals(acc_data)) {
                    Toast.makeText(StartActivity.this,"账户未注册", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(StartActivity.this,"密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}