package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderRepo implements OrderRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Order order) {
        return jdbcTemplate.update("INSERT INTO orders (dish,qunt) VALUES(?,?)",
                new Object[] {order.getDish(), order.getQunt()});
    }

    @Override
    public int update(Order order) {
        return jdbcTemplate.update("UPDATE orders SET dish=?, qunt=? WHERE id=?",
                new Object[] {order.getDish(), order.getQunt(), order.getId() });
    }

    @Override
    public Order findByDishh(String dish)
    {
        try{
            Order order=jdbcTemplate.queryForObject("SELECT * FROM orders WHERE dish=?",
                    BeanPropertyRowMapper.newInstance(Order.class), dish);
            return order;
        }catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    @Override
    public Order orderPrice(long id)
    {
        try{
            Order order=jdbcTemplate.queryForObject("SELECT orders.id, orders.dish, menu.price, orders.qunt FROM orders left join menu on orders.dish=menu.dish WHERE orders.id=? ",
                    BeanPropertyRowMapper.newInstance(Order.class), id);
            return order;
        }
        catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }


    @Override
    public Order findById(Long id) {
        try {
            Order order = jdbcTemplate.queryForObject("SELECT * FROM orders WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Order.class), id);

            return order;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query("SELECT * from orders", BeanPropertyRowMapper.newInstance(Order.class));
    }

    @Override
    public List<Order> findByDish(String dish) {
        String q = "SELECT * from orders WHERE dish ILIKE '%" + dish + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Order.class));
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM orders WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from orders");
    }

}
