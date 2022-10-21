# cafee20

Problem Statement - The aim of the project was to get each customer's bill according to what the customer has ordered along with all customer & order details.In my code the basic entities of cafe are - customer,  orders, menu, 
Customer -  id, name, email, number, dish, qunt, price, bill.
Order -  id, dish, qunt, price.
Menu - id, dish, price. 
Customer Api’s - 
1. @GetMapping("/customers") - Read all customers data.
2. @GetMapping("/customers/{id}" - Read customer data of particular id
3. @GetMapping("customers/getCustOrder/{id}" - Read particular customers orders data.  
4. @GetMapping("customers/getCustBill/{id}") - Gives particular customer bill along with other details.
5. @PostMapping("/customers") - Create a new customer record. 
6. @PutMapping("/customers/{id}") - Update customer.
7. @DeleteMapping("/customers/{id}") - Delete a particular customer. 
8. @DeleteMapping("/customers")- Delete all customers.


Order Api’s - 
1. @GetMapping("/order") - Read all orders data. 
2. @GetMapping("/order/{id}") - Read order of a particular order id.
3. @GetMapping("orders/getOrder/{id}") - Gives order details along with bill for that particular order id. 
4. @PostMapping("/order") - Create a new order record. 
5. @PutMapping("/order/{id}") - Update order.
6. @DeleteMapping("/order") - Delete all orders.
7. @DeleteMapping("/menu/{id}") - Delete a particular order.

Menu Api’s - 
1. @GetMapping("/menu") - Read the whole menu. 
2. @GetMapping("/menu/{id}") - Read a particular menu dish.  
3. @PostMapping("/menu") - Create a new dish record in menu.   
4. @DeleteMapping("/menu") - Delete  the whole menu.  
5. @DeleteMapping("/menu/{id}") - Delete a particular dish from the menu. 

--
