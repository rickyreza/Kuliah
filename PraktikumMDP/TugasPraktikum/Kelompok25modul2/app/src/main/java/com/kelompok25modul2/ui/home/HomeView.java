package com.kelompok25modul2.ui.home;

import com.kelompok25modul2.data.model.DataCar;

import java.util.List;

interface HomeView {

    void successShowCar(List<DataCar> dataCars);

    void failedShowCar(String message);

}
