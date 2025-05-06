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

CREATE TABLE cars(
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     framenumber VARCHAR(100),
                     color VARCHAR(50) NOT NULL ,
                     brand VARCHAR(50) ,
                     model VARCHAR(50) ,
                     equipment_level INT NOT NULL ,
                     steel_price DOUBLE NOT NULL ,
                     registration_fee DOUBLE NOT NULL ,
                     CO2_emissions DOUBLE NOT NULL ,
                     limited BOOLEAN,
                     status VARCHAR(50),
                     img VARCHAR(50)

);

INSERT INTO cars(id, framenumber, color, brand, model, equipment_level, steel_price, registration_fee, CO2_emissions, limited, status, img) VALUES
                                                                                                                                                (1, '5YJSA1E26HF000001', 'White', 'Tesla', 'Model Y', 1, 240000.00, 0.00, 0.00, TRUE, 'Leased', 'teslayhvid.jpeg'),
                                                                                                                                                (2, 'WBA3A5C54DF000002', 'Blue', 'BMW', '3 Series', 3, 180000.00, 250000.00, 130.00, TRUE, 'Leased', 'bmw3blue.jpeg'),
                                                                                                                                                (3, 'WDBUF56X78B000003', 'Black', 'Mercedes-Benz', 'E-Class', 4, 170000.00, 300000.00, 145.00, FALSE, 'Available', 'mercedesblack.png'),
                                                                                                                                                (4, 'WVWZZZ1JZXW000004', 'Silver', 'Volkswagen', 'Golf 8', 2, 120000.00, 220000.00, 120.00, FALSE, 'Leased', 'golf8silver.jpeg'),
                                                                                                                                                (5, 'W0L0ZCF254T000005', 'Red', 'Opel', 'Astra', 3, 85000.00, 150000.00, 130.00, FALSE, 'Leased', 'opelastrared.jpeg'),
                                                                                                                                                (6, '1FAFP404X1F000006', 'Blue', 'Ford', 'Focus 4', 3, 95000.00, 170000.00, 150.00, FALSE, 'Back', 'fordfocusblue.webp'),
                                                                                                                                                (7, 'JTDKN3DU7A0000007', 'Green', 'Toyota', 'Prius', 2, 105000.00, 160000.00, 115.00, TRUE, 'Available', 'toyotapriusgreen.jpeg'),
                                                                                                                                                (8, 'JHMCM56557C000008', 'Blue', 'Honda', 'Civic', 1, 110000.00, 180000.00, 130.00, TRUE, 'Leased', 'hondacivicblue.png'),
                                                                                                                                                (9, 'WAUZZZ8V1FA000009', 'Silver', 'Audi', 'A4', 4, 175000.00, 210000.00, 135.00, FALSE, 'Available', 'audia4silver.png'),
                                                                                                                                                (10, 'YV1RS61R542000010', 'Black', 'Volvo', 'S60', 4, 160000.00, 230000.00, 140.00, TRUE, 'Back', 'volvos60black.webp'),
                                                                                                                                                (11, 'VF1BB1C0F564000011', 'Silver', 'Renault', 'Clio V', 1, 90000.00, 140000.00, 115.00, TRUE, 'Leased', 'renaultcliosilver.jpeg'),
                                                                                                                                                (12, 'ZFF65LJA7E0200012', 'Red', 'Ferrari', '488 GTB', 3, 1200000.00, 1500000.00, 250.00, FALSE, 'Leased', 'ferrari488red.jpeg'),
                                                                                                                                                (13, 'ZHWGU11S44LA00013', 'Yellow', 'Lamborghini', 'Huracan EVO', 4, 1500000.00, 1800000.00, 280.00, FALSE, 'Available', 'lamborghininyellow.jpeg'),
                                                                                                                                                (14, '5YJXCDE22HF000014', 'Black', 'Tesla', 'Model X', 3, 290000.00, 0.00, 0.00, TRUE, 'Available', 'teslaxblack.webp'),
                                                                                                                                                (15, 'WBAVC93527K000015', 'Black', 'BMW', '5 Series', 1, 160000.00, 290000.00, 120.00, FALSE, 'Back', 'bmw5black.jpg'),
                                                                                                                                                (16, 'WDBNG70J53A000016', 'Silver', 'Mercedes-Benz', 'S-Class', 2, 190000.00, 350000.00, 160.00, TRUE, 'Leased', 'mercedessilver.png'),
                                                                                                                                                (17, 'WVWZZZ1KZAW000017', 'Blue', 'Volkswagen', 'Passat B8', 3, 130000.00, 240000.00, 125.00, FALSE, 'Leased', 'vwpassatblue.jpg'),
                                                                                                                                                (18, 'W0L0XCE759T000018', 'White', 'Opel', 'Mokka X', 2, 80000.00, 190000.00, 140.00, TRUE, 'Available', 'opelmokkaxwhite.webp'),
                                                                                                                                                (19, '1FAHP2E85DG000019', 'Silver', 'Ford', 'Fiesta', 1, 100000.00, 130000.00, 145.00, FALSE, 'Leased', 'fordfiestasilver.jpg'),
                                                                                                                                                (20, 'JTDKB20U093000020', 'Silver', 'Toyota', 'Corolla', 3, 110000.00, 180000.00, 110.00, TRUE, 'Available', 'toyotacorollasilver.webp'),
                                                                                                                                                (21, 'JHMEJ667X5S000021', 'Red', 'Honda', 'HR-V', 2, 130000.00, 160000.00, 120.00, FALSE, 'Back', 'hondahrvred.jpg'),
                                                                                                                                                (22, 'WAULC68E65A000022', 'Black', 'Audi', 'Q5', 3, 190000.00, 280000.00, 140.00, FALSE, 'Available', 'audiq5black.webp'),
                                                                                                                                                (23, 'YV1TS92D521000023', 'Blue', 'Volvo', 'XC60', 4, 210000.00, 300000.00, 135.00, TRUE, 'Leased', 'volvoxc60blue.jpg'),
                                                                                                                                                (24, 'VF1JZ1J0C567000024', 'White', 'Renault', 'Captur', 1, 90000.00, 170000.00, 120.00, FALSE, 'Available', 'renaultcapturwhite.webp'),
                                                                                                                                                (25, 'ZFF67NFA4D0200025', 'Black', 'Ferrari', 'Portofino M', 4, 1000000.00, 1700000.00, 270.00, FALSE, 'Leased', 'ferrariblack.webp')
;