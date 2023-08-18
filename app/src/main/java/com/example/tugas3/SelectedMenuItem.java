package com.example.tugas3;

public class SelectedMenuItem {
    private String name;
    private int price;
    private int counter;

    public SelectedMenuItem(String name, int price, int counter) {
        this.name = name;
        this.price = price;
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCounter() {
        return counter;
    }
}
