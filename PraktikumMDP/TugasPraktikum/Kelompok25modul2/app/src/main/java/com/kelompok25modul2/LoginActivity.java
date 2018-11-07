package com.kelompok25modul2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Printer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok25modul2.ui.home.HomeActivity;

import java.security.PrivateKey;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsernameLogin, etPasswdLogin;
    private Button btnSignUpLogin, btnSignInLogin;
    String username, password;
    private User user;
    private DatabaseHandler presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        login();
        AllBtn();
    }
    private void AllBtn() {
        btnSignUpLogin.setOnClickListener(V -> {
            Intent reg = new Intent(this,
                    RegisterActivity.class);
            startActivity(reg);
            finish();
        });
    }
    private void initData() {
        user = new User();
        presenter = new DatabaseHandler(this);
    }
    private void initView() {
        etUsernameLogin = findViewById(R.id.etUsernameLogin);
        etPasswdLogin = findViewById(R.id.etPasswdLogin);
        btnSignInLogin = findViewById(R.id.btnSignInLogin);
        btnSignUpLogin = findViewById(R.id.btnSignUpLogin);
    }
    private Boolean validation() {
        username = etUsernameLogin.getText().toString();
        password = etPasswdLogin.getText().toString();
        if (username.isEmpty()) {
            toastmessage("masukan username");
            Log.e("Validation", "false");
            return false;
        }
        if (password.isEmpty()) {
            toastmessage("isikan password");
            Log.e("Validation", "false");
            return false;
        }
        Log.e("Validation", "true");
        return true;
    }
    private void login() {
        btnSignInLogin.setOnClickListener(v -> actLogin());
    }
    private void actLogin() {
        if (validation()) {
            Log.e("actLogin", "true");
            if (loginData()) {
                initSharedPreference();
                Intent main = new Intent(this, HomeActivity.class);
                startActivity(main);
                finish();
            } else {
               toastmessage("login gagal");
            }
        }
    }
    private Boolean loginData() {
        user = presenter.getLogin(username);

        if (password.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
    private void initSharedPreference() {
        SharedPreferences preferences =
                getSharedPreferences("LoginPreference",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", password);
        editor.putString("username", username);
        editor.commit();
        editor.apply();
    }
    private void toastmessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
