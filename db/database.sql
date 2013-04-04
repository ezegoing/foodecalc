DROP DATABASE foodecalc;

CREATE DATABASE foodecalc;

USE foodecalc;

CREATE TABLE food_unit (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
user_id INT,
protein INT,
carbs INT,
fat INT
);

CREATE TABLE food_portion (
id INT AUTO_INCREMENT PRIMARY KEY,
food_unit_id INT,
user_id INT,
name VARCHAR(255),
weight INT
);

CREATE TABLE meal (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
user_id INT
);

CREATE TABLE meal_part (
id INT AUTO_INCREMENT PRIMARY KEY,
meal_id INT,
food_portion_id INT
);

create table users(
username varchar(50) not null primary key,
password varchar(50) not null,
enabled boolean not null
);

create table user(
id INT AUTO_INCREMENT PRIMARY KEY,
username varchar(50),
email varchar(255)
);

create table authorities(
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

CREATE TABLE meal_sitting (
id INT AUTO_INCREMENT PRIMARY KEY,
user_id INT,
meal_id INT,
date_and_time TIMESTAMP
);