CREATE DATABASE game_store_db;
USE game_store_db;


CREATE TABLE inventory(
	inventory_id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	price DECIMAL(10,2),
<<<<<<< HEAD:BackEnd_DataBase.sql
	discount INT,
=======
	discount INT DEFAULT 0,--this would be the percent out of 100 that would be the discount
>>>>>>> f154661eb7ed28a5cf374d578798b255d64eea5b:GameStoreManagement/BackEnd_DataBase.sql
    quantity_in_stock INT DEFAULT 0,
    description TEXT DEFAULT "",
	warranty DATE,
	type enum ('Game','Merch')
);
CREATE TABLE games (
    game_id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(100),
    console VARCHAR(50),
	inventory_id int,
	FOREIGN KEY (inventory_id) REFERENCES inventory(inventory_id) ON DELETE SET NULL
);
CREATE TABLE merch (
	merch_id INT PRIMARY KEY AUTO_INCREMENT,
	type enum ('Hat','T-Shirt','Hoodie','Bottle','Socks'),
	size enum ('XS','S','M','L','XL'),
	inventory_id int,
	FOREIGN KEY (inventory_id) REFERENCES inventory(inventory_id) ON DELETE SET NULL
);






CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
);
--name is their actual name and not user name
CREATE TABLE managers (
    manager_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    manager_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (manager_id) REFERENCES managers(manager_id) ON DELETE SET NULL
);

--this info is just for show
INSERT INTO USER(username, password, email)
values 
	('PizzaBoy155','Password123','ClarkKent@gmail.com'),
	('KittyKat','Ilovekats!1!','Diana1Prince2@gmail.com'),
	('SuperEmo','IAMbATMAN00!','Batman324@gmail.com'),
	('GreenLanty','daB3stH3r0','Greenlantern@gmail.com'),
	('MartinM','howDoITyPE2222','MartinManHunt@gmail.com'),
	('FastestManAlive', '1CanTyp3This@Faster', 'BarryAllen@gmail.com';
	
	select * from user;
	
	
	INSERT INTO employees(user_id, name, access_level, manager_id)
	values
	('1', 'Clark Kent', '1', '1'), 
	('2', 'Diana Prince', '1',  '1'),
	('4', 'Hal Jordan', '2',  '1');
	
	select * from employees;
	
	INSERT INTO managers (user_id, name,  access_level)
	values
	('3','Bruce Wayne','3');
	
	select * from managers;
	
	--a Query for all users and their name, Username, and email
	
	--a query for all employee for their name, wage and department
	
	--a query for the manager so they could see employee name, hire date, wage, schedule,(schedule is optional)
	
	--
	
