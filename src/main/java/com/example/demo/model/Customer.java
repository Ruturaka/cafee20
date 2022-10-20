package com.example.demo.model;


public class Customer {
    private long id;
    private String name;
    private String email;
    private String number;
    private String dish;
    private int qunt;
    private int price;
    private int bill;



    public Customer(){
    }
    public Customer(String name, String email, String number)
    {
        this.name=name;
        this.email=email;
        this.number=number;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getDish() {
        return dish;
    }
    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getQunt() {
        return qunt;
    }
    public void setQunt(int qunt) {
        this.qunt = qunt;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
