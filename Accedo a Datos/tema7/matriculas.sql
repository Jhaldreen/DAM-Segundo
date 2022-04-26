CREATE TABLE IF NOT EXISTS matriculas
(
    DNI          varchar(9)  NOT NULL,
    NombreModulo varchar(60) NOT NULL,
    Curso        varchar(5)  NOT NULL,
    Nota         double      NOT NULL,
    PRIMARY KEY (DNI)

)
    DEFAULT CHARSET = latin1;


insert into matriculas(DNI, NombreModulo, Curso, Nota)
values ('12345678A', 'DAM', '21-22', 5),
       ('87456321B', 'DAM', '21-22', 8),
       ('97864563C', 'DAW', '21-22', 7),
       ('56489731D', 'DAW', '21-22', 10);