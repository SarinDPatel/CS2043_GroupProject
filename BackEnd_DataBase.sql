CREATE DATABASE game_ziad_store;
USE game_ziad_store;


CREATE TABLE games (
    game_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    console VARCHAR(50),
    price DECIMAL(10,2),
    quantity_in_stock INT DEFAULT 0,
    release_date DATE,
    publisher VARCHAR(100),
    esrb_rating VARCHAR(10),
    description TEXT,
    image_path VARCHAR(255)
);

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
--name is their actual name and not user name
CREATE TABLE managers (
    manager_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    salary DECIMAL(10,2),
    hire_date DATE,
    department VARCHAR(100),
    access_level INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
--wage is per hour
--left the schedule blank as i dont really know how it should be altered
CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    wage DECIMAL(10,2),
    schedule TEXT,
    hire_date DATE,
    department VARCHAR(100),
	access_level INT,
    manager_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (manager_id) REFERENCES managers(manager_id) ON DELETE SET NULL
);

CREATE TABLE stock_updates (
    update_id INT PRIMARY KEY AUTO_INCREMENT,
    game_id INT,
    updated_by_id INT,
    updated_by_type ENUM('employee', 'manager'),
    old_quantity INT,
    new_quantity INT,
    update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (game_id) REFERENCES games(game_id)
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
	
	
	INSERT INTO employees(user_id, name, wage, hire_date, department, access_level, manager_id)
	values
	('1', 'Clark Kent', '17.80', NOW(), 'Cashier', '1', '1'), 
	('2', 'Diana Prince', '17.85', NOW(), 'Stocker', '1',  '1'),
	('4', 'Hal Jordan', '19.90', NOW(), 'Supervisor', '2',  '1');
	
	select * from employees;
	
	INSERT INTO managers (user_id, name, salary, hire_date, department, access_level)
	values
	('3','Bruce Wayne','31.12',NOW(),'Front End','3');
	
	select * from managers;
	