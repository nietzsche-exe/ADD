drop database if exists ciudadescolar;

create database ciudadescolar CHARACTER SET UTF8mb4;

use ciudadescolar;

create table alumnos(
   expediente varchar(3) not null,
   nombre varchar(100) not null,
   constraint pk_alumnos primary key ( expediente )
);

insert into alumnos(expediente, nombre) values(1, 'juan');
insert into alumnos(expediente, nombre) values(2, 'luis');
insert into alumnos(expediente, nombre) values(3, 'ana');
insert into alumnos(expediente, nombre) values(4, 'marta');


create table titulaciones
(
  cod_tit    varchar(3),
  nombre     varchar(60),
  nota_media decimal(4,2),
  constraint pk_titulacion primary key(cod_tit)
);

insert into titulaciones(cod_tit, nombre) values (1, 'c.f.g.s. desarrollo de aplicaciones web');
insert into titulaciones(cod_tit, nombre) values (2, 'c.f.g.s. desarrollo de aplicaciones multiplataforma');
insert into titulaciones(cod_tit, nombre) values (3, 'c.f.g.s. administraci�n de sistemas inform�ticos en red');

create table cursos
(
  cod_tit    varchar(3),
  num_curso  varchar(1),
  nota_media decimal(4,2),
  constraint pk_curso primary key(cod_tit, num_curso),
  constraint fk_cursos_titulacion foreign key (cod_tit) references titulaciones (cod_tit)
);

insert into cursos(cod_tit, num_curso) values (1, 1);
insert into cursos(cod_tit, num_curso) values (1, 2);
insert into cursos(cod_tit, num_curso) values (2, 1);
insert into cursos(cod_tit, num_curso) values (2, 2);
insert into cursos(cod_tit, num_curso) values (3, 1);
insert into cursos(cod_tit, num_curso) values (3, 2);

create table asignaturas
(
  cod_tit      varchar(3),
  num_curso    varchar(1),
  cod_asig     varchar(2),
  nombre       varchar(50),
  nparciales   tinyint unsigned,
  nmatriculas  tinyint unsigned,
  npresentados tinyint unsigned,
  naprobados   tinyint unsigned,
  nota_media   decimal(4,2),
  constraint pk_asignatura primary key(cod_tit, num_curso, cod_asig),
  constraint fk_asignaturas_curso foreign key (cod_tit, num_curso) references cursos(cod_tit, num_curso)
);

insert into asignaturas(cod_tit, num_curso, cod_asig, nombre, nparciales) values(1, 1, 1, 'bases de datos', 3);
insert into asignaturas(cod_tit, num_curso, cod_asig, nombre, nparciales) values(2, 1, 1, 'bases de datos', 3);
insert into asignaturas(cod_tit, num_curso, cod_asig, nombre, nparciales) values(3, 1, 2, 'dise�o de bases de datos', 2);
insert into asignaturas(cod_tit, num_curso, cod_asig, nombre, nparciales) values(1, 2, 3, 'acceso a datos', 2);
insert into asignaturas(cod_tit, num_curso, cod_asig, nombre, nparciales) values(1, 2, 4, 'desarrollo de interfaces', 2);
insert into asignaturas(cod_tit, num_curso, cod_asig, nombre, nparciales) values(2, 2, 4, 'dise�o de interfaces web', 1);
insert into asignaturas(cod_tit, num_curso, cod_asig, nombre, nparciales) values(2, 2, 5, 'proyecto de desarrollo de aplicaciones web', 2);


create table matriculas
(
  cod_tit      varchar(3),
  num_curso    varchar(1),
  cod_asig     varchar(2),
  expediente   varchar(3),
  nota         decimal(4,2),
  constraint pk_matricula primary key (cod_tit, num_curso, cod_asig, expediente),
  constraint fk_matriculas_alumnos foreign key (expediente) references alumnos(expediente),
  constraint fk_matriculas_asignaturas foreign key (cod_tit, num_curso, cod_asig) references asignaturas(cod_tit, num_curso, cod_asig)
);

insert into matriculas (cod_tit, num_curso, cod_asig, expediente, nota) values(1, 1, 1, 1, -1);
insert into matriculas (cod_tit, num_curso, cod_asig, expediente, nota) values(1, 2, 3, 1, -1);
insert into matriculas (cod_tit, num_curso, cod_asig, expediente, nota) values(2, 1, 1, 2, -1);
insert into matriculas (cod_tit, num_curso, cod_asig, expediente, nota) values(3, 1, 2, 3, -1);
insert into matriculas (cod_tit, num_curso, cod_asig, expediente, nota) values(2, 2, 4, 4, -1);
insert into matriculas (cod_tit, num_curso, cod_asig, expediente, nota) values(2, 2, 5, 4, -1);



create table calificaciones
(
  cod_tit      varchar(3),
  num_curso    varchar(1),
  cod_asig     varchar(2),
  expediente   varchar(3),
  num_examen   varchar(1),
  tipo         varchar(1),
  calificacion decimal(4,2),
  constraint pk_calificacion primary key (cod_tit, num_curso, cod_asig, expediente, num_examen),
  constraint fk_calificaciones_matriculas foreign key (cod_tit, num_curso, cod_asig, expediente) references matriculas(cod_tit, num_curso, cod_asig, expediente)
);

insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(1, 1, 1, 1, 1, 'p', 6);
insert into calificaciones (cod_tit, num_curso , cod_asig, expediente, num_examen, tipo, calificacion) values(1, 1, 1, 1, 2, 'p', 2);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(1, 1, 1, 1, 3, 'p', 2);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(1, 1, 1, 1, 4, 'f', 4);

insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(1, 2, 3, 1, 1, 'p', 5);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(1, 2, 3, 1, 2, 'p', 5);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(1, 2, 3, 1, 3, 'f', 7);

insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(2, 1, 1, 2, 1, 'p', 4);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(2, 1, 1, 2, 2, 'p', 5);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(2, 1, 1, 2, 3, 'p', 7);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(2, 1, 1, 2, 4, 'f', 9);

insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(3, 1, 2, 3, 1, 'p', 9);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(3, 1, 2, 3, 2, 'p', 8);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(3, 1, 2, 3, 3, 'f', 7);

insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(2, 2, 4, 4, 1, 'p', 9);
insert into calificaciones (cod_tit, num_curso, cod_asig, expediente, num_examen, tipo, calificacion) values(2, 2, 4, 4, 2, 'f', 7);


delimiter $$
create procedure sp_getalumno(in exp varchar(3))
begin
	select a.expediente, a.nombre from alumnos as a where a.expediente=exp;
end$$

delimiter ;

delimiter $$
create function fun_getalumno ( nom varchar(100)) 
returns varchar( 3 ) 
reads sql data
begin
	declare expediente varchar( 3 ) default "";  
    select a.expediente into expediente from alumnos a where a.nombre=nom;  
return expediente;  
end$$
delimiter ;



DELIMITER $$
CREATE FUNCTION fun_AlumnosNoPresentados( tit varchar(3), cur varchar(1), asig varchar(2)) RETURNS int
    READS SQL DATA
begin
	declare contador int;
    
	select COUNT(*) into contador from matriculas M 
    LEFT OUTER JOIN CALIFICACIONES C 
    ON M.expediente=C.EXPEDIENTE AND M.COD_TIT=C.COD_TIT AND M.NUM_CURSO=C.NUM_CURSO AND M.COD_ASIG=C.COD_ASIG
    WHERE C.EXPEDIENTE IS NULL
    AND M.COD_TIT=tit
    AND M.NUM_CURSO=cur
    AND M.COD_ASIG=asig;
return contador;  
end
$$

delimiter ;
