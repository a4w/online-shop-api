-- Create database
CREATE DATABASE IF NOT EXISTS `online-shop-api`;

USE `online-shop-api`;

CREATE TABLE `Customer` (
    `id` INT AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` CHAR(60) NOT NULL,
    `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);
