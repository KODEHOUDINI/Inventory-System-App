package com.example.lesleyedinamabotsi.data;

import java.util.*;

public class Bill {
    private int id;
    private Date date;
    private List<Good> items;

    public Bill(int id, Date date, List<Good> items) {
        this.id = id;
        this.date = date;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Good> getItems() {
        return items;
    }

    public void setItems(List<Good> items) {
        this.items = items;
    }
}