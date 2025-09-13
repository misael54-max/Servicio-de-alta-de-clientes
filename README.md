#  Backend 

Proyecto en **Spring Boot** que implementa microservicios de autenticación y gestión de clientes.  
Incluye seguridad basada en **JWT** y buenas prácticas de arquitectura en capas.

---

## Estructura del Proyecto

### 1. `auth-services`
Microservicio de autenticación y autorización.

- **`auth/AuthController.java`** → expone endpoints de login/registro.
- **`auth/security/JwtAuthenticationFilter.java`** → filtra y valida tokens JWT en cada request.
- **`auth/security/JwtService.java`** → generación y validación de JWT.
- **`auth/security/SecurityConfig.java`** → configuración de Spring Security.
- **`auth/user/UserEntity.java`** → entidad de usuario.
- **`auth/user/UserRepository.java`** → repositorio JPA.
- **`auth/user/UserDetailsServiceImpl.java`** → integra usuarios con Spring Security.
- **`auth/user/UserTypeEntity.java`** → entidad para roles/tipos de usuario.

---

### 2. `services-coppel`
Microservicio de gestión de clientes.

- **`customer/controller/CustomerController.java`** → expone endpoints REST (`/api/customers`).
- **`customer/model/CustomerEntity.java`** → entidad de cliente.
- **`customer/ICustomerRepository.java`** → repositorio JPA.
- **`customer/ICustomerService.java`** y **`CustomerServiceImpl.java`** → lógica de negocio.
- **`country/model/CountryEntity.java`** → entidad para países.
- **`security/`** → configuración de seguridad (si aplica).

---

##  Requisitos Previos

- Java 17+
- Maven 3.9+
- Spring Boot 3.x
- PostgreSQL/MySQL (según configuración de `application.yml`)
- Docker (opcional para despliegue)

---

##  Cómo Ejecutar

### Opción 1: Local
mvn clean install

2. Levantar Auth Service
   cd auth-services
   mvn spring-boot:run

3. Host Disponible 
   http://localhost:8081

4. Levantar Customer Service
   http://localhost:8082

##  Script de Base de Datos

El proyecto utiliza un **schema `coppel`** en PostgreSQL con tablas para países, clientes y usuarios.  
Ejecuta el siguiente script SQL antes de correr los microservicios.

###  Script SQL
```sql
-- 1. Crear el schema
CREATE SCHEMA IF NOT EXISTS coppel;

-- 2. Crear la tabla Countries
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

-- 3. Crear la tabla Customers
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

-- Insertar clientes demo
INSERT INTO coppel.customers 
    (name, first_name, last_name, birth_date, email, phone_number, fki_country) 
VALUES
    ('Juan Pérez', 'Juan', 'Pérez', '1990-05-12', 'juan.perez@example.com', '+5215512345678', 1),
    ('Emily Smith', 'Emily', 'Smith', '1985-11-23', 'emily.smith@example.com', '+12025550123', 2),
    ('Robert Johnson', 'Robert', 'Johnson', '1992-07-15', 'robert.johnson@example.com', '+14165550123', 3),
    ('Ana Souza', 'Ana', 'Souza', '1995-02-28', 'ana.souza@example.com', '+5511998765432', 4);

-- 4. Crear tabla User Types
CREATE TABLE coppel.user_types (
    pki_user_type SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Insertar roles de usuario
INSERT INTO coppel.user_types (name) VALUES ('ADMIN'), ('DATA_ENTRY');

-- 5. Crear tabla Users
CREATE TABLE coppel.users (
    pki_user SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    fki_user_type INT NOT NULL,
    CONSTRAINT fk_user_type FOREIGN KEY (fki_user_type) REFERENCES coppel.user_types(pki_user_type)
);

- Los usuarios deben ser agregados mediante la API (endpoint /auth/register).