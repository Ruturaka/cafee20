package com.example.demo.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("menu")
public class Menu {
    private long id;
    private String dish;
    private int price;

    public Menu(){
    }

    public Menu(String dish, int price)
    {
        this.dish=dish;
        this.price=price;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }
    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
