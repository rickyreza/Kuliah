package com.kelompok25modul2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {


    private EditText eUsernameUpdate, ePasswordUpdate;
    private Button bList, bUpdate, bDelete;

    private DatabaseHandler databaseHandler;
    private int selectedID;
    private String selectedName;
    private String selectedPassword;

    Intent receivedIntent;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initView();
        initData();
        buttonClick();


    }

    private void buttonClick() {
        bUpdate.setOnClickListener(view -> {
            String iUser = eUsernameUpdate.getText().toString();
            String iPassword = ePasswordUpdate.getText().toString();

            if(!iUser.equals("") && !iPassword.equals("")){
                boolean newupdate = databaseHandler.updateName(selectedID, iUser, iPassword);
                if(newupdate){
                    toastMessage("Berhasil Di Update");
                }else{
                    toastMessage("gagal diupdate");
                }
                changeActivity(ViewDataActivity.class);
            }else {
                toastMessage("You must enter a name");
            }
        });

        bDelete.setOnClickListener(view -> {
            showAlertDialog();
        });

        bList.setOnClickListener(view -> {
            changeActivity(ViewDataActivity.class);
        });
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Apa ingin menghapus Akun ini?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       databaseHandler.deleteName(selectedID, selectedName);
                       ePasswordUpdate.setText("");
                       eUsernameUpdate.setText("");
                       toastMessage("succesfully deleted");
                       changeActivity(LoginActivity.class);
                       finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void initData() {
        receivedIntent = getIntent();
        databaseHandler = new DatabaseHandler(this);
        user = new User();

        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("name");
        selectedPassword = receivedIntent.getStringExtra("password");

        eUsernameUpdate.setText(selectedName);
        ePasswordUpdate.setText(selectedPassword);
    }

    private void initView() {
        eUsernameUpdate = findViewById(R.id.etLoginUpdate);
        ePasswordUpdate = findViewById(R.id.etPasswordUpdate);
        bUpdate = findViewById(R.id.btUpdate);
        bList = findViewById(R.id.btList);
        bDelete = findViewById(R.id.btDelete);
    }


    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        Intent reg = new Intent(this, MainActivity.class);
        startActivity(reg);
        finish();
    }

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void changeActivity(Class classes){
        Intent change = new Intent(this, classes);
        startActivity(change);
        finish();
    }
}
