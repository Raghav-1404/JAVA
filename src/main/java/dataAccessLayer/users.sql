-- Create the database
CREATE DATABASE FoodWasteReduction;

-- Switch to the new database
USE FoodWasteReduction;

-- Create the Users table
CREATE TABLE Users (
    userid INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    usertype VARCHAR(50) NOT NULL
);

CREATE TABLE Inventory (
    itemid INT PRIMARY KEY AUTO_INCREMENT,
    itemname VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    isItemSurplus BOOLEAN NOT NULL
);


select * from users;
