package com.example.demo.repository;

import com.example.demo.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMenuRepo implements MenuRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int save(Menu menu) {
        return jdbcTemplate.update("INSERT INTO menu (dish, price) VALUES(?,?)",
                new Object[] {menu.getDish(), menu.getPrice()});
    }

    @Override
    public int update(Menu menu) {
        return jdbcTemplate.update("UPDATE menu SET dish=?, price=? WHERE id=?",
                new Object[] {menu.getDish(), menu.getPrice(), menu.getId() });
    }

    @Override
    public Menu findById(Long id) {
        try {
            Menu menu = jdbcTemplate.queryForObject("SELECT * FROM menu WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Menu.class), id);

            return menu;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Menu> findAll() {
        return jdbcTemplate.query("SELECT * from menu", BeanPropertyRowMapper.newInstance(Menu.class));
    }

    @Override
    public List<Menu> findByDish(String dish) {
        String q = "SELECT * from menu WHERE dish ILIKE '%" + dish + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Menu.class));
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM menu WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from menu");
    }
}
