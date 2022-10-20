package com.example.demo.model;

public class Order {
    private long id;
    private String dish;
    private int qunt;
    private int price;

    public Order(){
    }

    public Order(String dish, int qunt)
    {
        this.dish=dish;
        this.qunt=qunt;
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

    public int getQunt() { return qunt; }
    public void setQunt(int qunt) { this.qunt = qunt; }

    public int getPrice() {return price;}
    public void setPrice(int price) {
        this.price = price;
    }
}
