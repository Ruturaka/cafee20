package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.Menu;

import java.util.List;

public interface OrderRepo {
    int save(Order record);

    int update(Order record);

    Order findByDishh(String dish);

    Order orderPrice(long id);


    Order findById(Long id);

    List<Order> findAll();

    List<Order> findByDish(String dish);

    int deleteById(Long id);

    int deleteAll();




}
