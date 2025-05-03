DROP DATABASE IF EXISTS BilabonnementDatabase;

CREATE DATABASE BilabonnementDatabase;

USE BilabonnementDatabase;

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(50) NOT NULL,
                      role VARCHAR(50) NOT NULL
);


-- Dummy data, Det er de samme objekter som bruges i InitData.
INSERT INTO user (name, password, role) VALUES
                                      ('Mads', '1234', 'data_registration'),
                                      ('Admin', '1234', 'admin'),
                                      ('Joakim', '1234','admin'),
                                      ('Guney', '1234','mechanic'),
                                      ('Thamied', '1234', 'business_developer'),
                                      ('demo','demo','admin')
;