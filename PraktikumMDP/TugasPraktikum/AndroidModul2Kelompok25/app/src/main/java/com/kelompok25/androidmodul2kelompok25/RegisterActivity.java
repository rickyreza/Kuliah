package com.kelompok03.androidmodul2kelompok25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private User usermodel;
    private EditText etUsernameRegister;
    private EditText etPasswordRegister;
    private Button btRegister;
    private String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        register();
    }

    private void register() {
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDataHandler();
            }
        });
    }

    private void initView() {
        etUsernameRegister = findViewById(R.id.etUsernameRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btRegister = findViewById(R.id.btRegister);
    }

    private void initUser() {
        username = etUsernameRegister.getText().toString();
        password = etPasswordRegister.getText().toString();

        usermodel = new User();
        usermodel.setUsername(username);
        usermodel.setPassword(password);
    }

    private void initDataHandler() {
        initUser();
        databaseHandler = new DatabaseHandler(this);
        databaseHandler.addUser(usermodel);
        User model = databaseHandler.getMahasiswa(1);
        Log.e("record", model.getUsername().toString());
        Intent admin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(admin);
        finish();
    }
}
