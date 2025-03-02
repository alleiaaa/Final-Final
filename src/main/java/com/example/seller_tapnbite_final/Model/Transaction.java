package com.example.seller_tapnbite_final.Model;

public class Transaction {
    private String canteen;
    private String orderId;
    private int amount;

    public Transaction(String canteen, String orderId, int amount) {
        this.canteen = canteen;
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getCanteen() {
        return canteen;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getAmount() {
        return amount;
    }
}
