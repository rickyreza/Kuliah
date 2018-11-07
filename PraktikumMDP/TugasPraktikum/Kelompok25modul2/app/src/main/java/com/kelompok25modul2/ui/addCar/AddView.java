package com.kelompok25modul2.ui.addCar;

public interface AddView {
    String getName();

    String getMerk();

    String getModel();

    String getYear();

    void successAddCar();

    void failedAddCar();
}
