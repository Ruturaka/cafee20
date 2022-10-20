package com.example.demo.repository;

import com.example.demo.model.Customer;

import java.util.List;

import javax.sql.DataSource;
import com.example.demo.model.Customer;

public interface CustomerRepo {
    int save(Customer record);

    int update(Customer record);

    Customer findById(long id);

    Customer custOrder(long id);

    Customer custBill(long id);

    Customer showByName(String name);


    List<Customer> findAll();

    List<Customer> findBYName(String name);

    int deleteById(Long id);

    int deleteAll();

}
