package com.kelompok25modul2.ui.detailCar;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kelompok25modul2.R;
import com.kelompok25modul2.data.model.DataCar;
import com.kelompok25modul2.ui.home.HomeActivity;
import com.kelompok25modul2.utility.Constant;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private DataCar car;
    private DetailPresenter detailPresenter;
    private DetailDeletePresenter deletePresenter;
    private EditText Nama, Merk, Model, Tahun;
    private Button  bDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initIntentData();
        initPresenter();
        initData();
        deleteCar();
    }

    private void deleteCar() {
        bDelete.setOnClickListener(view -> {
            deletePresenter.deleteCar(car);
        });
    }

    private void initView() {

        Nama = findViewById(R.id.tv_nama_isi);
        Merk = findViewById(R.id.tv_Merk_isi);
        Model = findViewById(R.id.tv_model_isi);
        Tahun = findViewById(R.id.tv_Tahun_isi);
        bDelete = findViewById(R.id.bDelete);
        deletePresenter = new DetailDeletePresenter(this);

    }

    private void initData() {
        detailPresenter.getCarById(car);
    }

    private void initPresenter() {
        detailPresenter = new DetailPresenter(this);
    }

    private void initIntentData() {
        car = getIntent().getParcelableExtra(Constant.Extra.DATA);
        if (car == null) finish();
    }

    @Override
    public String getName() {
        return Nama.getText().toString();
    }

    @Override
    public String getMerk() {
        return Merk.getText().toString();
    }

    @Override
    public String getModel() {
        return Model.getText().toString();
    }

    @Override
    public String getYear() {
        return Tahun.getText().toString();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showSuccess(List<DataCar> car) {
        Nama.setText(car.get(0).getName());
        Merk.setText(car.get(0).getMerk());
        Model.setText(car.get(0).getModel());
        Tahun.setText(car.get(0).getYear());
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil Mengubah Mobil", Toast.LENGTH_SHORT).show();
        Intent home = new Intent(DetailActivity.this, HomeActivity.class);
        startActivity(home);
        finish();
    }

    @Override
    public void failedDelete() {
        Toast.makeText(this, "Gagal Mengubah Mobil", Toast.LENGTH_SHORT).show();
    }


}
