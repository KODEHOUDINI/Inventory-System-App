package com.example.lesleyedinamabotsi.data;

import java.util.Date;

public class IssuedGood {
    private int id;
    private Date date;
    private Good good;
    private int quantity;

    public IssuedGood(Date date, Good good, int quantity) {
        this.date = date;
        this.good = good;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

