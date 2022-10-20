package com.example.demo.repository;

import com.example.demo.model.Menu;

import java.util.List;

public interface MenuRepo {
    int save(Menu record);

    int update(Menu record);

    Menu findById(Long id);

    List<Menu> findAll();

    List<Menu> findByDish(String dish);

    int deleteById(Long id);

    int deleteAll();
}


