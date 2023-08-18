package com.example.tugas3;

import android.widget.TextView;

import androidx.annotation.NonNull;

public class Menu {
    public String namaMenu;
    public int harga;
    public String deskripsi;
    public int gambar;
    public int counter;
    public int totalPrice;

    public Menu(String namaMenu, int harga, String deskripsi, int gambar, int counter) {
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.counter = counter;
        this.totalPrice = harga * counter;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return namaMenu;
    }

    // Getter for price
    public int getPrice() {
        return harga;
    }

    // Getter for description
    public String getDescription() {
        return deskripsi;
    }

    // Getter for imageResource
    public int getImageResource() {
        return gambar;
    }

    // Getter for quantity
    public int getQuantity() {
        return counter;
    }

    @NonNull
    @Override
    public String toString() {
        return this.namaMenu + this.harga + this.deskripsi;
    }
}
