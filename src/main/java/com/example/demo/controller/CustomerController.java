package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/cuapi")
public class CustomerController {
    @Autowired
    CustomerRepo customerRepo;

    // For customer
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAlCustomers(@RequestParam(required = false) String name) {
        try {
            List<Customer> customers = new ArrayList<Customer>();

            if (name == null)
                customerRepo.findAll().forEach(customers::add);
            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        Customer customer = customerRepo.findById(id);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("customers/getCustOrder/{id}")
    public ResponseEntity<Customer> getCustOrder(@PathVariable("id") long id) {
        Customer customer=customerRepo.custOrder(id);
        if(customer!=null){
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("customers/getCustBill/{id}")
    public ResponseEntity<Customer> getCustBill(@PathVariable("id") long id) {
        Customer customer=customerRepo.custBill(id);
        if(customer!=null){
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/customers")
    public ResponseEntity<String> createTutorial(@RequestBody Customer customer) {
        try {
            customerRepo.save(new Customer(customer.getName(), customer.getEmail(), customer.getNumber()));
            return new ResponseEntity<>("Customer was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") long id, @RequestBody Customer customer) {
        Customer _customer = customerRepo.findById(id);

        if (_customer != null) {
            _customer.setId(id);
            _customer.setName(customer.getName());
            _customer.setEmail(customer.getEmail());
            _customer.setNumber(customer.getNumber());
            _customer.setDish(customer.getDish());
            _customer.setQunt(customer.getQunt());
            _customer.setPrice(customer.getQunt());
            _customer.setBill(customer.getBill());

            customerRepo.update(_customer);
            return new ResponseEntity<>("Customer was updated successfully.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable("id") long id) {
        try {
            int result = customerRepo.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Customer was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers")
    public ResponseEntity<String> deleteAllCustomers() {
        try {
            int numRows = customerRepo.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Customer(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete customers.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

