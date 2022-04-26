DROP DATABASE IF EXISTS alumnos;

CREATE DATABASE alumnos;

Use alumnos;

CREATE TABLE alumnos (
	DNI varchar(9) NOT NULL,
	Nombre varchar(50) NOT NULL,
	Apellidos varchar(70) NOT NULL,
	Direccion varchar(100) NOT NULL,
	FechaNac date NOT NULL,
	PRIMARY KEY (DNI)
);

INSERT INTO alumnos VALUES
('12345678A', 'José Alberto', 'González Pérez', 'C/Albahaca, nº14, 1ºD', '1986-07-15'),
('23456789B', 'Almudena', 'Cantero Verdemar', 'Avd/ Profesor Alvarado, n27, 8ºA', '1988-11-04'),
('14785236d', 'Martín', 'Díaz Jiménez', 'C/Luis de Gongora, nº2.', '1987-03-09'),
('96385274f', 'Lucas', 'Buendia Portes', 'C/Pintor Sorolla, nº 16, 4ºB', '1988-07-10');

CREATE TABLE matriculas (
	DNI varchar(9) NOT NULL,
	NombreModulo varchar(60) NOT NULL,
	Curso varchar(5) NOT NULL,
	Nota double NOT NULL,
	PRIMARY KEY (DNI, NombreModulo, Curso)
) ;

INSERT INTO matriculas (DNI, NombreModulo, Curso, Nota) VALUES
('12345678A', 'Acceso a datos', '11-12', 7.5),
('12345678A', 'Desarrollo de Interfaces', '11-12', 8.6),
('12345678A', 'Entornos de desarrollo', '11-12', 9),
('12345678A', 'Sistemas informaticos', '11-12', 7.3),
('14785236d', 'Acceso a datos', '11-12', 5),
('14785236d', 'Desarrollo de Interfaces', '11-12', 7.2),
('23456789B', 'Acceso a datos', '11-12', 10),
('23456789B', 'Desarrollo de Interfaces', '11-12', 3.5);

Commit;