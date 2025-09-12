-- 1. Crear el schema
CREATE SCHEMA IF NOT EXISTS coppel;

-- 1. Crear la tabla Countries dentro del schema coppel
CREATE TABLE coppel.countries (
    pki_country SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);
-- Insertar países
INSERT INTO coppel.countries (name) VALUES
('México'),
('Estados Unidos'),
('Canadá'),
('Brasil'),
('Argentina'),
('Chile'),
('Colombia'),
('Perú'),
('Venezuela'),
('Ecuador'),
('Bolivia'),
('Paraguay'),
('Uruguay'),
('Costa Rica'),
('Panamá'),
('Guatemala'),
('Honduras'),
('El Salvador'),
('Nicaragua'),
('Cuba'),
('República Dominicana'),
('España'),
('Francia'),
('Alemania'),
('Italia');

-- 2. Crear la tabla Customers dentro del schema coppel
CREATE TABLE coppel.customers (
    pki_customer SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    birth_date DATE,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    fki_country INT,
    CONSTRAINT fk_country FOREIGN KEY (fki_country) REFERENCES coppel.countries(pki_country)
);

INSERT INTO coppel.customers 
    (name, first_name, last_name, birth_date, email, phone_number, fki_country) 
VALUES
    ('Juan Pérez', 'Juan', 'Pérez', '1990-05-12', 'juan.perez@example.com', '+5215512345678', 1),
    ('Emily Smith', 'Emily', 'Smith', '1985-11-23', 'emily.smith@example.com', '+12025550123', 2),
    ('Robert Johnson', 'Robert', 'Johnson', '1992-07-15', 'robert.johnson@example.com', '+14165550123', 3),
    ('Ana Souza', 'Ana', 'Souza', '1995-02-28', 'ana.souza@example.com', '+5511998765432', 4);


-- Tabla de tipos de usuarios
CREATE TABLE coppel.user_types (
    pki_user_type SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Insertar tipos de usuario
INSERT INTO coppel.user_types (name) VALUES ('ADMIN'), ('DATA_ENTRY');

-- Modificación de la tabla users
CREATE TABLE coppel.users (
    pki_user SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(100) not null,
    fki_user_type INT NOT NULL,
    CONSTRAINT fk_user_type FOREIGN KEY (fki_user_type) REFERENCES coppel.user_types(pki_user_type)
);
-- Usuarios deben ser agregados por api

