package com.kelompok25modul2;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewDataActivity extends AppCompatActivity {

    ListView listitem;
    DatabaseHandler databaseHandler;
    ArrayAdapter adapter, psadapter;
    List<String> listItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        initView();
        viewdata();
    }

    private void viewdata() {

        //membuat cursor untuk menandai data username pada yang ada pada table database
        Cursor cursor = databaseHandler.viewDataMahasiswa();

        //membuat cursor jika ada data atau tidak
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no Data", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()) { //akan melakukan perulangan sebanyak data yang ada trus melangkah ke code selanjutnya
                String username = cursor.getString(1); //mendapatkan nilai username pada colomn 1
                listItem.add(username); // menambahkan nilai username pada array list
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listItem);
            // menambahkan array list kedalam adaptre

            listitem.setAdapter(adapter); // menambahkan adapter ke dalam listview


            listitem.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name = adapterView.getItemAtPosition(i).toString(); // mendapatkan posisi username pada listview
                    String password = null;
                    Log.e("name", "you clicked on" + name);

                    Cursor data = databaseHandler.getItemID(name);  // menjalankan perintah getItemID untuk mendapatkan 2 data
                                                                    // sehingga memiliki index 2 yaitu 0 dan 1
                                                                    // getItemID mendapatkan nilai id dan password
                                                                    // menggunakan penanda username

                    int itemID = -1;
                    while(data.moveToNext()){
                        itemID = data.getInt(0);  // mengambil nilai data pada index 0 pada getItemID
                        password = data.getString(1); //mengambil nilai data pada index 1 pada getItemID
                    }
                    if (itemID > -1){
                        Log.d("new", "onItemClickID: The ID is" + itemID);
                        Intent edit = new Intent(ViewDataActivity.this, UpdateActivity.class);
                        edit.putExtra("id", itemID); //memasukan data kedalam intent putExtra agar bisa dibawa ke activity selanjutnya
                        edit.putExtra("name", name );
                        edit.putExtra("password", password);
                        startActivity(edit);
                        finish();
                    }else {
                        toastmessge("no ID Associate with this");
                    }

                }
            });
        }
    }

    private void initView() {
        listitem = findViewById(R.id.aclistView);
        databaseHandler = new DatabaseHandler(this);
        listItem = new ArrayList<>();

    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
        finish();
    }

    public void toastmessge(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
