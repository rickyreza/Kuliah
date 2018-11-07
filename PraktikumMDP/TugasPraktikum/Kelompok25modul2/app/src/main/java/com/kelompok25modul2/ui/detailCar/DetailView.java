package com.kelompok25modul2.ui.detailCar;

import com.kelompok25modul2.data.model.DataCar;

import java.util.List;

public interface DetailView {

    String getName();

    String getMerk();

    String getModel();

    String getYear();

    void showError(String message);
    void showSuccess(List<DataCar> car);

    void successDelete();

    void failedDelete();
}