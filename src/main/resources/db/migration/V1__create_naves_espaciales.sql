-- Crear la base de datos llamada 'mysql_db'
CREATE DATABASE IF NOT EXISTS mysql_db;

-- Usar la base de datos recién creada
USE mysql_db;

-- Crear la tabla para almacenar las Naves Espaciales
CREATE TABLE nave_espacial (
    nave_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL
);

-- Crear la tabla para almacenar Series o Películas
CREATE TABLE serie_pelicula (
    serie_pelicula_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo_produccion ENUM('SERIE', 'PELICULA') NOT NULL,
    genero VARCHAR(255) NULL
);

-- Crear la tabla intermedia para la relación ManyToMany entre Naves Espaciales y Series/Películas
CREATE TABLE nave_serie_pelicula (
    nave_id BIGINT NOT NULL,
    serie_pelicula_id BIGINT NOT NULL,
    PRIMARY KEY (nave_id, serie_pelicula_id),
    CONSTRAINT fk_nave FOREIGN KEY (nave_id) REFERENCES nave_espacial(nave_id) ON DELETE CASCADE,
    CONSTRAINT fk_serie_pelicula FOREIGN KEY (serie_pelicula_id) REFERENCES serie_pelicula(serie_pelicula_id) ON DELETE CASCADE
);
