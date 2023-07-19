package com.example.lesleyedinamabotsi.data;

public class Good implements Comparable<Good> , Entity{
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int category;
    private Vendor vendor;

    public Good(int id, String name, double price, int quantity,int category, Vendor vendor) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.vendor = vendor;
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public int getCategory(){
        return category;
    }

    @Override
    public int compareTo(Good other) {
        return Integer.compare(this.id, other.id);
    }
}
