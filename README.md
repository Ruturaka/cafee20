# cafee20

Cafe doc 
 
Problem Statement - The aim of the project was to get each customer's bill according to what the customer has ordered along with all customer & order details.In my code the basic entities of the cafe are - customer,  orders & menu.


Requirements - 
1. Customer - This will have customers information (name , number and email address). It will take this information from the customer. Along with this it should have api’s that show customer order details , customer bill details, customer info details. For doing this we require these rest api’s. 
2. Order entity -Take customer order by its item name & quantity preferred.
3.Menu - Should be able to create a dish item along with its price.

Approach - We have these main packages which are discussed below - 

Model - Our entities here are customer, order & menu. 
Customer: name, email & number.
Order - dish, qunt.
Menu - dish, price.

Repository - To perform CRUD operation on our entities. We define 3 interfaces mainly CustomerRepo, OrderRepo & Menu Repo. In the interfaces we have declared the methods we want our respective models must override. 

CustomerRepo interface -  Along with normal methods of save, update, delete for performing crud operations.The other methods we use are -
Customer findById(long id);
Customer custOrder(long id);
Customer custBill(long id);
List<Customer> findAll();

OrderRepo interface -  Along with normal methods of save, update, delete for performing crud operations.The other methods we use are -
Order orderPrice(long id);
Order findById(long id);
List<Order> findByDish(String dish);

MenuRepo interface -  Along with normal methods of save, update, delete for performing crud operations. The other methods we use are - 
Menu findById(long id);
List<Menu> findAll();
List<Menu> findByDish(String dish);

These interface methods are overridden by JdbcCustomerReopo, JdbcOrderRepo & JdbcMenuRepo respectively. Inside those overridden methods the main query logic is written for each method. 
Eg. The JdbcCustomerRepo class implements the CustomerRepo interface. Inside overridden method Customer custOrder(long id) we write the sql query which gives us the customer order details - 




Controller - which is basically the business layer. Here we implement the actual business logic of processing information. 
 
Customer Api’s - 
/customers - Read all customers' data.
/customers/{id} - Read customer data of particular id
/customers/getCustOrder/{id} - Read particular customers orders data.  
/customers/getCustBill/{id} - Gives a particular customer bill along with other details.
 /customers - Create a new customer record. 
/customers/{id} - Update customer.
/customers/{id} - Delete a particular customer. 
/customers - Delete all customers.
 
Order Api’s -  
/order - Read all orders data. 
/order/{id}" - Read the order of a particular order id.
/orders/getOrder/{id} - Gives order details along with a bill for that particular order id.
/order" - Create a new order record. 
/order/{id} - Update order.
/order - Delete all orders.
/menu/{id} - Delete a particular order.
 
 
Menu Api’s - 
/menu - Read the whole menu. 
/menu/{id} - Read a particular menu dish.  
/menu - Create a new dish record in the menu.   
/menu - Delete  the whole menu.  
/menu/{id} - Delete a particular dish from the menu. 


3. Entity Diagram for reference - 
 

Customer
id
name
email-id
number
1
Rutu raka
rutu17@gmail.com
7219118711
2
Kshitij luthara
Kshitij175@gmail.com
9021910730

 

Order 
id
dish
qunt
1
Pasta
1
2
French Fries
3







Menu
id
dish
price
1
Pasta
500
2
French Fries
200



We have these 3 tables with us. And using these tables we have to calculate what will be the bill of each customer & also know what each customer has ordered. 

Order table will have a relation with the menu table. To get know the order price we need to take that from the menu table. We will check this by dish name. 

To know the customer order - We need to join the order & customer table at id. Which will be the same for both. 

Similarly to calculate customers' bills we need to join all 3 tables as - customer has a relationship with order & order table is related to menu. 

What I did is - We know that the menu is constant. We want the price of the item order which we will get from the menu entity table. So, to do that I have used left join on orders.dish=menu.dish   for that particular id of the order entity. 

To get customer order & customer bill we need to write a query which will join 2 inner joins together. First Inner join between customers and orders table on customers.id=orders.id and second inner join between orders and menu table on orders.dish=menu.dish  for given customer id. 

customers/getCustOrder/{id} gives us - Customers order details (id, name, email, number , dish & quantity).




customers/getCustBill/{id} gives us - Customers order details (id, name, email, number , dish, quantity, price, bill).


Real use case of the solution -  This system can be used by the cafe owner to have the record database of each customer that visited the cafe. Also, it can track each customer order details. I can further add revenue entity too. Which can be calculated using this database that we have now.

--
