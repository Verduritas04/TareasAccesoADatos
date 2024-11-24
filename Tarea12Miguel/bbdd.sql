DROP DATABASE IF EXISTS alumno1585520;

CREATE DATABASE alumno1585520;
USE alumno1585520;

CREATE TABLE GRUPO (
cod_grupo int PRIMARY KEY,
nombre    VARCHAR(50)
);

CREATE TABLE ALUMNO (
nia              int PRIMARY KEY,
nombre           VARCHAR(50),
apellidos        VARCHAR(50),
genero           VARCHAR(50),
fecha_nacimiento DATE,
ciclo            VARCHAR(50),
curso            VARCHAR(50),
fk_grupo         int,
FOREIGN KEY (fk_grupo) REFERENCES GRUPO(cod_grupo) ON UPDATE CASCADE ON DELETE CASCADE
);
