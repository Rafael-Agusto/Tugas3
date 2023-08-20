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

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDate() {
        return date;
    }
}
