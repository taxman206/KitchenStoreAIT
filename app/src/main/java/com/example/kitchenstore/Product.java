package com.example.kitchenstore;

public class Product {
    private String name;
    private int amount;
    private double price;
    private int expiry;

    public Product(String name, int amount, double price, int expiry) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.expiry = expiry;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }
}
