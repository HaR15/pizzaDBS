CREATE DATABASE customers;

CREATE TABLE Customers(Active int(11) NOT NULL,name varchar(20) NOT NULL,addr varchar(20) NOT NULL,sex varchar(20) NOT NULL,age varchar(20) NOT NULL,balance int(11) NOT NULL,Username varchar(20) NOT NULL, Password varchar(20) NOT NULL, creditCardNum varchar(20) NOT NULL,PRIMARY KEY (Username));

CREATE TABLE product(pid int(11) NOT NULL AUTO_INCREMENT,name varchar(20) NOT NULL,category varchar(20) NOT NULL,price int(11) NOT NULL,supplier varchar(20) NOT NULL,manufacturer varchar(20) NOT NULL,description varchar(128) NOT NULL,special int(11) NOT NULL,PRIMARY KEY (pid));

CREATE TABLE deliverypeople(did int(11) NOT NULL AUTO_INCREMENT, currentLocation varchar(20) NOT NULL, costPerMiles varchar(20) NOT NULL, vehicle varchar(20) NOT NULL, name varchar(20) NOT NULL, address varchar(20) NOT NULL, workHours varchar(20) NOT NULL, salary int(11) NOT NULL, transInfo varchar(20) NOT NULL, PRIMARY KEY (did));

CREATE TABLE transaction(date timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP, totalPrice int(11) NOT NULL,standingOrders int(11) NOT NULL, tid int(11) NOT NULL AUTO_INCREMENT, username varchar(20) NOT NULL, methodOfPayment varchar(20) NOT NULL, did int(11), PRIMARY KEY (tid), FOREIGN KEY (username) REFERENCES Customers(Username));

CREATE TABLE `order`(oid int(11) NOT NULL AUTO_INCREMENT, tid int(11) NOT NULL, pid int(11) NOT NULL, quantity int(11) NOT NULL, price int(11) NOT NULL, PRIMARY KEY (oid),FOREIGN KEY (tid) REFERENCES transaction(tid), FOREIGN KEY (pid) REFERENCES product(pid));

CREATE TABLE pizzaOrder(pizzaoid int(11) NOT NULL AUTO_INCREMENT,tid int(11) NOT NULL,pid int(11) NOT NULL, oid int(11) NOT NULL, PRIMARY KEY (pizzaoid),FOREIGN KEY (oid) REFERENCES `order`(oid),FOREIGN KEY (tid) REFERENCES transaction(tid));

CREATE TABLE toppingOrder(toppingoid int(11) NOT NULL AUTO_INCREMENT, tid int(11) NOT NULL, pizzaOID int(11) NOT NULL, pid int(11) NOT NULL, oid int(11) NOT NULL,PRIMARY KEY (toppingoid), FOREIGN KEY (oid) REFERENCES `order`(oid),FOREIGN KEY (pizzaOID) REFERENCES pizzaOrder(pizzaOID),FOREIGN KEY (tid) REFERENCES transaction(tid));

INSERT INTO `customers`.`deliverypeople` (`did`, `currentLocation`, `costPerMiles`, `vehicle`, `name`, `address`, `workHours`, `salary`, `transInfo`) VALUES (NULL, 'markham', '10', 'truck', 'andrew', '10 ben alder', '5-9', '10', 'bad truck');

INSERT INTO `customers`.`product` (`pid`, `name`, `category`, `price`, `supplier`, `manufacturer`, `description`, `special`) VALUES (NULL, 'cheese pizza', 'pizza', '10', 'A company', 'B company', 'cheese on a pizza', '0');

INSERT INTO `customers`.`product` (`pid`, `name`, `category`, `price`, `supplier`, `manufacturer`, `description`, `special`) VALUES (NULL, 'pineapple pizza', 'pizza', '10', 'A company', 'B company', 'pineapple on a pizza', '0');

INSERT INTO `customers`.`product` (`pid`, `name`, `category`, `price`, `supplier`, `manufacturer`, `description`, `special`) VALUES (NULL, 'tomato', 'topping', '1, 'A company', 'B company', 'vegetable', '0');

INSERT INTO `customers`.`product` (`pid`, `name`, `category`, `price`, `supplier`, `manufacturer`, `description`, `special`) VALUES (NULL, 'bacon', 'topping', '1', 'A company', 'B company', 'meat', '0');

INSERT INTO `customers`.`product` (`pid`, `name`, `category`, `price`, `supplier`, `manufacturer`, `description`, `special`) VALUES (NULL, 'soda', 'drink', '3', 'A company', 'B company', 'drink', '0');

INSERT INTO `customers`.`customers` (`Active`, `name`, `addr`, `sex`, `age`, `balance`, `Username`, `Password`, `creditCardNum`) VALUES ('1', 'Richard Luo', '80 shale', 'm', '21', '20', 'Richard', '123', '123123232');