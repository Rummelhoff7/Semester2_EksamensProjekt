DROP DATABASE IF EXISTS BilabonnementDatabase;

CREATE DATABASE BilabonnementDatabase;

USE BilabonnementDatabase;

CREATE TABLE leasing (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_id INT NOT NULL,
    start_date DATE,
    end_date DATE,
    price DOUBLE,
    status BOOLEAN,
    customer_info TEXT
    /*  FOREIGN KEY (car_id)
    REFERENCES car(id) */
);

INSERT INTO leasing (car_id, start_date, end_date, price, status, customer_info) VALUES
    (1, '2025-05-02', '2025-09-02',40000.00, false, 'Markus Johansen'),
    (2, '2025-05-02', '2025-10-02',75000.00, false, 'Jakob Hansen'),
    (3, '2025-05-02', '2025-09-01',62000.00, false, 'Martin Gunnersen'),
    (4, '2025-05-02', '2025-10-02',56000.00, true, 'Alexander Frandsen'),
    (5, '2025-05-02', '2025-10-02',60000.00, true, 'Lukas Lucasson'),
    (6, '2025-05-02', '2025-11-01',80000.00, false, 'Sofie Jakobsen'),
    (7, '2025-05-02', '2025-12-24',88000.00, false, 'Amir Zakariasen')
;

CREATE TABLE damagereport (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_id INT NOT NULL,
    date DATE
    /*  FOREIGN KEY (car_id)
     REFERENCES car(id) */
);

INSERT INTO damagereport (car_id, date) VALUES
    (8,'2025-05-02')
/*  Damagereport skal kun være på biler der er færdige med leasing */
;

CREATE TABLE damageitem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dmg_id INT NOT NULL,
    description TEXT,
    cost DOUBLE,
    FOREIGN KEY (dmg_id)
    REFERENCES damagereport(id)
);

INSERT INTO damageitem (dmg_id, description, cost) VALUES
    (1, 'Manglende sidespejl', 5000.00)
;