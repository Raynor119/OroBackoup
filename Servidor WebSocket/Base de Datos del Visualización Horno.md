la estructura de la Base de datos es la siguiente

~~~plaintext
┌────────────────────────────┐
│     measurement_sessions   │
├────────────────────────────┤
│ id (PK)                    │
│ user_id                    │
│ start_timestamp            │
│ end_timestamp              │
│ status                     │
└────────────┬───────────────┘
             │
             │ 1
             │
             ▼
┌────────────────────────────┐
│    temperature_readings    │
├────────────────────────────┤
│ id (PK)                    │
│ session_id (FK)            │
│ timestamp                  │
│ temperature                │
└────────────────────────────┘
~~~
y para crear dicha base de datos es los siguientes comandos SQL

~~~SQL
-- Creando la base de datos
CREATE DATABASE horno_monitoring;

-- Entramos a la base de datos
USE horno_monitoring;

-- Creando tabla de sesiones de medición
CREATE TABLE measurement_sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    start_timestamp DATETIME NOT NULL,
    end_timestamp DATETIME DEFAULT NULL,
    status ENUM('active', 'stopped') NOT NULL DEFAULT 'active'
);

-- Creando tabla de lecturas de temperatura
CREATE TABLE temperature_readings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    session_id INT NOT NULL,
    timestamp DATETIME NOT NULL,
    temperature FLOAT NOT NULL,
    FOREIGN KEY (session_id) REFERENCES measurement_sessions(id) ON DELETE CASCADE
);
~~~

al momento de crear la mariaDB esta de la siguiente forma
- Consultado: 20/04/2025 - 3:03:P.M.
~~~SQL
MariaDB [(none)]> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| Joyeria            |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0,001 sec)
~~~
la creación del usuario es con el siguiente comando, este usuario es el encargado de usarse para interactuar entre el script del servidor websocket con la base de datos 
~~~SQL
CREATE USER 'ServerWS'@'localhost' IDENTIFIED BY '67895421d';
~~~
le damos permisos de modificación de todas las tablas
~~~SQL
GRANT INSERT, SELECT, UPDATE ON horno_monitoring.* TO 'ServerWS'@'localhost';
~~~
Aplicamos los cambios y listo
~~~SQL
FLUSH PRIVILEGES;
~~~
