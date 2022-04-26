create database ies;

use ies;

/* Creo la tabla profesores y relleno sus datos */
create table profesores(
    nombre varchar(15) not null,
    apellidos varchar(50) not null,
    dni varchar(10) not null,
    telefono varchar(9) not null,
    numAsignaturas integer not null,
    primary key (dni)
);

insert into profesores values 
('Diego', 'Serrano Toca', '11111111-A', '987654321', 4),
('David', 'Salas Torre', '22222222-B', '887654321', 4),
('Marta', 'Ugarte Uriarte', '33333333-C0', '787654321', 1),
('Cristina', 'Arranz Salmon', '44444444-D', '687654321', 4),
('Fernando', 'Pereda Mingo', '55555555-E', '587654321', 3);

/* Creo la tabla asignaturas y relleno sus datos */
create table asignaturas(
    id integer not null,
    nombre varchar(100) not null,
    curso integer not null,
    horas integer not null,
    primary key (id)
);

insert into asignaturas values
(1, 'Sistemas informaticos', 1, 100),
(2, 'Bases de datos', 1, 105),
(3, 'Programacion', 1, 135),
(4, 'Lenguajes de marcas', 1, 70),
(5, 'Entornos', 1, 50),
(6, 'Acceso a datos', 2, 80),
(7, 'Programacion multimedia', 2, 55),
(8, 'Programacion de servicios', 2, 40),
(9, 'Empresa e iniciativa emprendedora', 2, 35);

/* Creo la tabla que relaciona profesores y asignaturas, y relleno sus datos */
create table matriculas(
    profesor varchar(10) not null,
    asignatura integer not null,
    primary key (profesor,asignatura)
);

insert into matriculas values
('11111111-A', 2),
('11111111-A', 3),
('11111111-A', 4),
('11111111-A', 6),
('22222222-B', 1),
('22222222-B', 2),
('22222222-B', 3),
('22222222-B', 5),
('33333333-C', 9),
('44444444-D', 5),
('44444444-D', 6),
('44444444-D', 7),
('44444444-D', 8),
('55555555-E', 3),
('55555555-E', 6),
('55555555-E', 7);