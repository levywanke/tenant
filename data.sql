-- Drop the databases if they exist
DROP DATABASE IF EXISTS tenant_a;
DROP DATABASE IF EXISTS tenant_b;
DROP DATABASE IF EXISTS tenant_c;
DROP DATABASE IF EXISTS tenant_1;
DROP DATABASE IF EXISTS tenant_2;

-- Create the databases
CREATE DATABASE tenant_a;
CREATE DATABASE tenant_b;
CREATE DATABASE tenant_c;
CREATE DATABASE tenant_1;
CREATE DATABASE tenant_2;


-- Tenant A
USE tenant_a;
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    tenantId VARCHAR(255)  
);

INSERT INTO product (name, price, tenantId) VALUES 
('Aspirin 100mg', 12.00, 'tenant_a'),
('Ibuprofen 200mg', 8.50, 'tenant_a'),
('Paracetamol 500mg', 5.00, 'tenant_a'),
('Amoxicillin 250mg', 15.00, 'tenant_a'),
('Cetirizine 10mg', 6.50, 'tenant_a');

-- Tenant B
USE tenant_b;
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    tenantId VARCHAR(255)  
);

INSERT INTO product (name, price, tenantId) VALUES 
('Ciprofloxacin 500mg', 14.00, 'tenant_b'),
('Metformin 850mg', 10.00, 'tenant_b'),
('Omeprazole 20mg', 9.00, 'tenant_b'),
('Azithromycin 250mg', 13.50, 'tenant_b'),
('Lisinopril 10mg', 7.50, 'tenant_b');

-- Tenant C
USE tenant_c;
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    tenantId VARCHAR(255)  
);

INSERT INTO product (name, price, tenantId) VALUES 
('Simvastatin 20mg', 11.00, 'tenant_c'),
('Atorvastatin 10mg', 15.00, 'tenant_c'),
('Metoprolol 50mg', 8.00, 'tenant_c'),
('Losartan 25mg', 12.50, 'tenant_c'),
('Clopidogrel 75mg', 16.00, 'tenant_c');

-- Tenant 1
USE tenant_1;
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    tenantId VARCHAR(255)  
);

INSERT INTO product (name, price, tenantId) VALUES 
('Hydrochlorothiazide 25mg', 10.50, 'tenant_1'),
('Gabapentin 300mg', 14.00, 'tenant_1'),
('Prednisone 5mg', 6.50, 'tenant_1'),
('Pantoprazole 40mg', 11.00, 'tenant_1'),
('Fluoxetine 20mg', 9.00, 'tenant_1');

-- Tenant 2
USE tenant_2;
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    tenantId VARCHAR(255)  
);

INSERT INTO product (name, price, tenantId) VALUES 
('Warfarin 5mg', 13.00, 'tenant_2'),
('Montelukast 10mg', 8.50, 'tenant_2'),
('Clarithromycin 500mg', 15.50, 'tenant_2'),
('Levothyroxine 50mcg', 7.00, 'tenant_2'),
('Alprazolam 1mg', 10.00, 'tenant_2');
