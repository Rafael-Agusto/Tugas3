package com.example.tugas3;

public class PurchaseHistoryItem {
    private int totalCounter;
    private double totalPrice;
    private String paymentMethod;
    private String date;

    public PurchaseHistoryItem(int totalCounter, double totalPrice, String paymentMethod, String date) {
        this.totalCounter = totalCounter;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }

    public int getTotalCounter() {
        return totalCounter;
    }

    // Other getter methods for other fields
}

