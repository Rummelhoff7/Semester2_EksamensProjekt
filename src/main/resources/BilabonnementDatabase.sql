DROP DATABASE IF EXISTS BilabonnementDatabase;

CREATE DATABASE BilabonnementDatabase;

USE BilabonnementDatabase;

-- Güney
CREATE TABLE cars(
                     id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
                     framenumber VARCHAR(100) UNIQUE,
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




-- Güney
INSERT INTO cars(framenumber, color, brand, model, equipment_level, steel_price, registration_fee, CO2_emissions, limited, status, img) VALUES
    ('5YJSA1E26HF000001', 'Hvid', 'Tesla', 'Model Y', 1, 240000.00, 0.00, 0.00, TRUE, 'Udlejet', 'teslayhvid.jpeg'),
    ('WBA3A5C54DF000002', 'Blå', 'BMW', '3 Series', 3, 180000.00, 250000.00, 130.00, TRUE, 'Udlejet', 'bmw3blue.jpeg'),
    ('WDBUF56X78B000003', 'Sort', 'Mercedes-Benz', 'E-Class', 4, 170000.00, 300000.00, 145.00, FALSE, 'Ledig', 'mercedesblack.png'),
    ('WVWZZZ1JZXW000004', 'Sølv', 'Volkswagen', 'Golf 8', 2, 120000.00, 220000.00, 120.00, FALSE, 'Udlejet', 'golf8silver.jpeg'),
    ('W0L0ZCF254T000005', 'Rød', 'Opel', 'Astra', 3, 85000.00, 150000.00, 130.00, FALSE, 'Udlejet', 'opelastrared.jpeg'),
    ('1FAFP404X1F000006', 'Blå', 'Ford', 'Focus 4', 3, 95000.00, 170000.00, 150.00, FALSE, 'Garage', 'fordfocusblue.webp'),
    ('JTDKN3DU7A0000007', 'Grøn', 'Toyota', 'Prius', 2, 105000.00, 160000.00, 115.00, TRUE, 'Ledig', 'toyotapriusgreen.jpeg'),
    ('JHMCM56557C000008', 'Blå', 'Honda', 'Civic', 1, 110000.00, 180000.00, 130.00, TRUE, 'Udlejet', 'hondacivicblue.png'),
    ('WAUZZZ8V1FA000009', 'Sølv', 'Audi', 'A4', 4, 175000.00, 210000.00, 135.00, FALSE, 'Ledig', 'audia4silver.png'),
    ('YV1RS61R542000010', 'Sort', 'Volvo', 'S60', 4, 160000.00, 230000.00, 140.00, TRUE, 'Garage', 'volvos60black.webp'),
    ('VF1BB1C0F564000011', 'Sølv', 'Renault', 'Clio V', 1, 90000.00, 140000.00, 115.00, TRUE, 'Udlejet', 'renaultcliosilver.jpeg'),
    ('ZFF65LJA7E0200012', 'Rød', 'Ferrari', '488 GTB', 3, 1200000.00, 1500000.00, 250.00, FALSE, 'Udlejet', 'ferrari488red.jpeg'),
    ('ZHWGU11S44LA00013', 'Gul', 'Lamborghini', 'Huracan EVO', 4, 1500000.00, 1800000.00, 280.00, FALSE, 'Ledig', 'lamborghiniyellow.jpeg'),
    ('5YJXCDE22HF000014', 'Sort', 'Tesla', 'Model X', 3, 290000.00, 0.00, 0.00, TRUE, 'Ledig', 'teslaxblack.webp'),
    ('WBAVC93527K000015', 'Sort', 'BMW', '5 Series', 1, 160000.00, 290000.00, 120.00, FALSE, 'Garage', 'bmw5black.jpg'),
    ('WDBNG70J53A000016', 'Sølv', 'Mercedes-Benz', 'S-Class', 2, 190000.00, 350000.00, 160.00, TRUE, 'Udlejet', 'mercedessilver.png'),
    ('WVWZZZ1KZAW000017', 'Blå', 'Volkswagen', 'Passat B8', 3, 130000.00, 240000.00, 125.00, FALSE, 'Udlejet', 'vwpassatblue.jpg'),
    ('W0L0XCE759T000018', 'Hvid', 'Opel', 'Mokka X', 2, 80000.00, 190000.00, 140.00, TRUE, 'Ledig', 'opelmokkaxwhite.webp'),
    ('1FAHP2E85DG000019', 'Sølv', 'Ford', 'Fiesta', 1, 100000.00, 130000.00, 145.00, FALSE, 'Udlejet', 'fordfiestasilver.jpg'),
    ('JTDKB20U093000020', 'Sølv', 'Toyota', 'Corolla', 3, 110000.00, 180000.00, 110.00, TRUE, 'Ledig', 'toyotacorollasilver.webp'),
    ('JHMEJ667X5S000021', 'Rød', 'Honda', 'HR-V', 2, 130000.00, 160000.00, 120.00, FALSE, 'Garage', 'hondahrvred.jpg'),
    ('WAULC68E65A000022', 'Sort', 'Audi', 'Q5', 3, 190000.00, 280000.00, 140.00, FALSE, 'Ledig', 'audiq5black.webp'),
    ('YV1TS92D521000023', 'Blå', 'Volvo', 'XC60', 4, 210000.00, 300000.00, 135.00, TRUE, 'Udlejet', 'volvoxc60blue.jpg'),
    ('VF1JZ1J0C567000024', 'Hvid', 'Renault', 'Captur', 1, 90000.00, 170000.00, 120.00, FALSE, 'Ledig', 'renaultcapturwhite.webp'),
    ('ZFF67NFA4D0200025', 'Sort', 'Ferrari', 'Portofino M', 4, 1000000.00, 1700000.00, 270.00, FALSE, 'Udlejet', 'ferrariblack.webp');
;

-- Mads
CREATE TABLE electric_car (
    id INT PRIMARY KEY,
    battery_capacity DECIMAL(5,2),
    charging_time DECIMAL(4,2),
    range_per_charge DECIMAL(6,2),
        FOREIGN KEY (id)
        REFERENCES cars(id)
);

-- Mads
INSERT INTO electric_car (id, battery_capacity, charging_time, range_per_charge) VALUES
    (1, 78.1, 8.15,514),
    (14,100, 7, 529);

-- Mads
CREATE TABLE leasing (
                         id INT AUTO_INCREMENT PRIMARY KEY UNIQUE ,
                         car_id INT NOT NULL,
                         start_date DATE,
                         end_date DATE,
                         price DOUBLE,
                         status BOOLEAN,
                         customer_info TEXT,
                             FOREIGN KEY (car_id)
                             REFERENCES cars(id)
);

-- Mads
INSERT INTO leasing (car_id, start_date, end_date, price, status, customer_info) VALUES
                        (1, '2025-05-05', '2025-08-02',40000.00, false, 'Markus Johansen'),
                        (2, '2025-05-02', '2025-10-02',75000.00, false, 'Jakob Hansen'),
                        (3, '2025-06-22', '2025-11-01',62000.00, false, 'Martin Gunnersen'),
                        (4, '2025-08-12', '2026-01-12',56000.00, true, 'Alexander Frandsen'),
                        (5, '2025-10-11', '2026-03-02',60000.00, true, 'Lukas Lucasson'),
                        (6, '2025-09-29', '2026-11-01',80000.00, false, 'Sofie Jakobsen'),
                        (7, '2025-01-02', '2026-01-24',88000.00, false, 'Amir Zakariasen')
;

-- Güney
CREATE TABLE damagereport (
                              id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
                              car_id INT NOT NULL,
                              date DATE,
                                  FOREIGN KEY (car_id)
                                  REFERENCES cars(id)
);




-- Güney
INSERT INTO damagereport (car_id, date) VALUES
    (8,'2025-05-02'),
    (2,'2025-05-06'),
    (4,'2025-05-06'),
    (5,'2025-06-02'),
    (6,'2025-05-06'),
    (7,'2025-06-02'),
    (3,'2025-06-02'),
    (15,'2025-05-06'),
    (12,'2025-06-02'),
    (4,'2025-06-02'),
    (20,'2025-08-10')
;

-- Güney
CREATE TABLE damageitem (
                            id INT AUTO_INCREMENT PRIMARY KEY UNIQUE ,
                            dmg_id INT,
                            description TEXT,
                            cost DOUBLE,
                            FOREIGN KEY (dmg_id)
                                REFERENCES damagereport(id)
);

-- Güney
INSERT INTO damageitem (dmg_id, description, cost) VALUES
    (1, 'Manglende sidespejl', 500.00),
    (1, 'Underlig lugt fra AC', 750.00),
    (2, 'Forsvundet måtte', 500.00),
    (2, '14 Ridser i lakken', 20000.00),
    (3, 'Smadret sidespejl', 5000.00),
    (3, 'Manglende gearstang', 8000.00),
    (4, 'Babymos på bagsædet', 950.00),

    (5, 'Forsvundet måtte', 500.00),
    (6, '14 Ridser i lakken', 20000.00),
    (5, 'Smadret sidespejl', 5000.00),
    (6, 'Manglende gearstang', 8000.00),
    (5, 'Babymos på bagsædet', 950.00),

    (6, 'Forsvundet måtte', 500.00),
    (7, '14 Ridser i lakken', 20000.00),
    (8, 'Smadret sidespejl', 5000.00),
    (7, 'Manglende gearstang', 8000.00),
    (8, 'Babymos på bagsædet', 950.00),

    (7, 'Forsvundet måtte', 500.00),
    (8, '14 Ridser i lakken', 20000.00),
    (9, 'Smadret sidespejl', 5000.00),
    (9, 'Manglende gearstang', 8000.00),
    (9, 'Babymos på bagsædet', 950.00),

    (4, 'Radioaktiv udstødningsrøg', 9000.00),
    (4, 'Flækket vindueshvisker', 8000.00)
;

-- Joakim
CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
                      name VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(50) NOT NULL,
                      role VARCHAR(50) NOT NULL
);
-- Joakim
INSERT INTO user(name, password, role) VALUES
                    ('demo', 'demo', 'admin'),
                    ('Mads','1234', 'data_registration'),
                    ('guney','1234','mechanic'),
                    ('thamied','1234','business_developer')
;
-- Joakim
CREATE TABLE advance_car_sale(
    id int AUTO_INCREMENT PRIMARY KEY UNIQUE ,
    car_id INT NOT NULL UNIQUE ,
    terms TEXT,
    exceeded_kilometers int,
    buying_price double,
    collection_point VARCHAR(100),
    FOREIGN KEY (car_id)
        REFERENCES cars(id)
                         );


-- Joakim
INSERT INTO advance_car_sale(car_id, terms, exceeded_kilometers, buying_price, collection_point) VALUES
    (1,'Garanti op til 6 måned', 200, 20560.20, 'Garage A'),
    (3,'ingen', 1000, 30560.20, 'Garage A'),
    (8,'Gratis vinterdæk', 2000, 510560.20, 'Garage C'),
    (10,'Gratis vinterdæk', 2050, 90560.20, 'Garage B')
;