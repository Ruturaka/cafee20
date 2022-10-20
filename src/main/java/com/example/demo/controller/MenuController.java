package com.example.demo.controller;

import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/meapi")
public class MenuController {
    @Autowired
    MenuRepo menuRepo;

    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getWholeMenu(@RequestParam(required = false) String dish) {
        try {
            List<Menu> menu = new ArrayList<Menu>();

            if (dish == null)
                menuRepo.findAll().forEach(menu::add);
            else
                menuRepo.findByDish(dish).forEach(menu::add);
            if (menu.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<Menu> getDishById(@PathVariable("id") long id) {
        Menu menu = menuRepo.findById(id);

        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/menu")
    public ResponseEntity<String> createDish(@RequestBody Menu menu) {
        try {
            menuRepo.save(new Menu(menu.getDish(), menu.getPrice()));
            return new ResponseEntity<>("Dish was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menu")
    public ResponseEntity<String> deleteDishes(){
        try{
            int numRows=menuRepo.deleteAll();
            return new ResponseEntity<>("Deleted "+ numRows+ " Dishe(s) successfully.", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Cannot delete dishes.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable("id") long id) {
        try {
            int result = menuRepo.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Dish with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Dish was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete dish.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

