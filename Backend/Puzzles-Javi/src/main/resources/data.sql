
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('miguel@email.com', 'Miguel Campos Rivera','12345678','mcampos',true, true, true, true, false, false, CURRENT_DATE, NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('fernandezhernandez@email.com', 'Antonio Fernández Hernández','12345678','antoniofernandez',true, true,true, true,false, false, CURRENT_DATE, NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('ruizgarcia@email.com', 'Francisca Ruiz García','12345678','franciscaruiz',true, true,true, true,false, false, CURRENT_DATE, NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('rodriguezmartinez@email.com', 'Javier Rodríguez Martínez','12345678','javierrodriguez', true, true,true, true,false, false, CURRENT_DATE,NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('ramosramos@email.com', 'Javier Ramos Ramos','12345678','javierramos',true, true,true, true, false, false, CURRENT_DATE,NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('castrorubio@email.com', 'Jose Castro Rubio','12345678','josecastro', true,true, true, true,false, false, CURRENT_DATE,NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('sanzgarcia@email.com', 'Marta Sanz García','12345678','martasanz', true, true,true, true,false, false, CURRENT_DATE,NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('delgadomolina@email.com', 'Manuel Delgado Molina','12345678','manueldelgado', true,true, true, true,false, false, CURRENT_DATE,NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('vazquezmarin@email.com', 'María Ángeles Vázquez Marín','12345678','maríaángelesvazquez', true,true,true, true, false, false, CURRENT_DATE,NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('garciadiaz@email.com', 'Ana García Díaz','12345678','anagarcia', true,true, true, true, false, true CURRENT_DATE,NEXTVAL('hibernate_sequence'));
--INSERT INTO USUARIO(email, nombre_completo, passwd, username, activo, credential_is_non_expired, non_expired, non_locked, vip,fecha_alta, admin, id) VALUES('lopezgarcia@email.com', 'María Teresa López García','12345678','maríateresalopez', true,true,true, true,false, true, CURRENT_DATE, NEXTVAL('hibernate_sequence'));
INSERT INTO USUARIO(activo, admin, credential_is_non_expired, email, fecha_alta, nombre_completo, non_expired, non_locked, passwd, username, vip, id) VALUES(true, false, true, 'lopezgarcia@email.com', CURRENT_DATE, 'María Teresa López García', true, true, '12345678', 'maríateresalopez', false,  NEXTVAL('hibernate_sequence'));
INSERT INTO USUARIO(activo, admin, credential_is_non_expired, email, fecha_alta, nombre_completo, non_expired, non_locked, passwd, username, vip, id) VALUES(true, true, true, 'garciadiaz@email.com', CURRENT_DATE, 'Ana García Díaz', true, true, '12345678', 'anagarcia', false,  NEXTVAL('hibernate_sequence'));
INSERT INTO USUARIO(activo, admin, credential_is_non_expired, email, fecha_alta, nombre_completo, non_expired, non_locked, passwd, username, vip, id) VALUES(true, false, true, 'vazquezmarin@email.com', CURRENT_DATE, 'María Ángeles Vázquez Marín', true, true, '12345678', 'maríaángelesvazquez', false,  NEXTVAL('hibernate_sequence'));

INSERT INTO usuario_roles (usuario_id, roles) VALUES(1, 'USER');
INSERT INTO usuario_roles (usuario_id, roles) VALUES(2, 'ADMIN');
INSERT INTO usuario_roles (usuario_id, roles) VALUES(3, 'USER');
--INSERT INTO usuario_roles (usuario_id, roles) VALUES(4, 'USER');
--INSERT INTO usuario_roles (usuario_id, roles) VALUES(5, 'USER');
--INSERT INTO usuario_roles (usuario_id, roles) VALUES(6, 'USER');
--INSERT INTO usuario_roles (usuario_id, roles) VALUES(7, 'USER');
--INSERT INTO usuario_roles (usuario_id, roles) VALUES(8, 'USER');
--INSERT INTO usuario_roles (usuario_id, roles) VALUES(9, 'USER');
--INSERT INTO usuario_roles (usuario_id, roles) VALUES(10, 'ADMIN');
--INSERT INTO usuario_roles (usuario_id, roles) VALUES(11, 'ADMIN');

INSERT INTO puzzle(nombre, descripcion, precio,  numero_piezas, categoria, id) VALUES('Fortaleza', 'Puzzle de 500 Piezas',15.00, 500 , '500 Piezas', NEXTVAL('hibernate_sequence'));
INSERT INTO puzzle(nombre, descripcion, precio,  numero_piezas, categoria, id) VALUES('Pirata', 'Puzzle de 1000 Piezas',17.00, 1000 , '1000 Piezas', NEXTVAL('hibernate_sequence'));
INSERT INTO puzzle(nombre, descripcion, precio,  numero_piezas, categoria, id) VALUES('Baile', 'Puzzle de 2000 Piezas',19.00, 2000 , '2000 Piezas', NEXTVAL('hibernate_sequence'));
INSERT INTO puzzle(nombre, descripcion, precio,  numero_piezas, categoria, id) VALUES('Castillo', 'Puzzle de 3000 Piezas',21.00, 3000 , '3000 Piezas', NEXTVAL('hibernate_sequence'));
INSERT INTO puzzle(nombre, descripcion, precio,  numero_piezas, categoria, id) VALUES('Barco', 'Puzzle de 4000 Piezas',23.00, 4000 , '4000 Piezas', NEXTVAL('hibernate_sequence'));
INSERT INTO puzzle(nombre, descripcion, precio,  numero_piezas, categoria, id) VALUES('Bosque', 'Puzzle de 6000 Piezas',25.00, 6000 , '6000 Piezas', NEXTVAL('hibernate_sequence'));
INSERT INTO puzzle(nombre, descripcion, precio,  numero_piezas, categoria, id) VALUES('Selva', 'Puzzle de 8000 Piezas',35.00, 8000 , '8000 Piezas', NEXTVAL('hibernate_sequence'));

insert into imagen_puzzle(id, data_id, delete_hash, puzzle_id) values (NEXTVAL('hibernate_sequence'), 'zi3AIuX', 'Oo55EkuFayvfLf0',4);
insert into imagen_puzzle(id, data_id, delete_hash, puzzle_id) values (NEXTVAL('hibernate_sequence'), 'siFBvVd', 'Vga32C9yMrchIzt',5);
insert into imagen_puzzle(id, data_id, delete_hash, puzzle_id) values (NEXTVAL('hibernate_sequence'), 'ypp9kSE', 'UqZ2l3c4hs6HW1v',6);
insert into imagen_puzzle(id, data_id, delete_hash, puzzle_id) values (NEXTVAL('hibernate_sequence'), 'HucINH1', 'FuzGKB27RDdSWmH',7);
insert into imagen_puzzle(id, data_id, delete_hash, puzzle_id) values (NEXTVAL('hibernate_sequence'), 'zi3AIuX', 'Oo55EkuFayvfLf0',8);
insert into imagen_puzzle(id, data_id, delete_hash, puzzle_id) values (NEXTVAL('hibernate_sequence'), 'siFBvVd', 'Vga32C9yMrchIzt',9);
insert into imagen_puzzle(id, data_id, delete_hash, puzzle_id) values (NEXTVAL('hibernate_sequence'), 'ypp9kSE', 'UqZ2l3c4hs6HW1v',10);
