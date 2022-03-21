USE alumnos;

DROP TABLE IF EXISTS matriculas;

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