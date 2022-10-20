package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepo;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/orapi")
public class OrderController {
    @Autowired
    //for order -
    OrderRepo orderRepo;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getWholeMenu(@RequestParam(required = false) String dish) {
        try {
            List<Order> order = new ArrayList<Order>();

            if (dish == null)
                orderRepo.findAll().forEach(order::add);
            else
                orderRepo.findByDish(dish).forEach(order::add);
            if (order.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getDishById(@PathVariable("id") long id) {
        Order menu = orderRepo.findById(id);

        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/orders/{dish}")
    public ResponseEntity<Order> GetPDish(@PathVariable("dish") String name){
        Order menu=orderRepo.findByDishh(name);

        if(menu!=null){
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("orders/getOrder/{id}")
    public ResponseEntity<Order> getPrice(@PathVariable("id") long id) {
        Order menu=orderRepo.orderPrice(id);

        if(menu!=null){
            return new ResponseEntity<Order>(menu, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<String> createDish(@RequestBody Order order) {
        try {
            orderRepo.save(new Order(order.getDish(), order.getQunt()));
            return new ResponseEntity<>("Dish was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/order/{id}")
    public ResponseEntity<String> updateDish(@PathVariable("id") long id, @RequestBody Order order) {
        Order _order = orderRepo.findById(id);

        if (_order != null) {
            _order.setId(id);
            _order.setDish(order.getDish());
            _order.setQunt(order.getQunt());
            _order.setPrice(order.getPrice());

            orderRepo.update(_order);
            return new ResponseEntity<>("Dish was updated successfully.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Cannot find Dish with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/order")
    public ResponseEntity<String> deleteDishes(){
        try{
            int numRows=orderRepo.deleteAll();
            return new ResponseEntity<>("Deleted "+ numRows+ " Dish(s) successfully.", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Cannot delete dishes.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable("id") long id) {
        try {
            int result = orderRepo.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Dish with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Dish was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete dish.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
