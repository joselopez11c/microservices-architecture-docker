INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('andres','$2a$10$/4kZUX/jWLXuN250Dwq2BOwZBjt1iuaZQk0lbmYzuE.YpSnmh3MDO',true, 'dockerStywar', 'vargas','Stywar.vargas@aforo255.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$ZZa/5LP0jQHPMdqBqZRKuuozlinrMyo3arYxO9ixQObl4lEQSR5Z2',true, 'Franco', 'chino','Franco.chino@aforo255.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('stywar','$2a$10$swlfCD3IabBISX985nmqbe5GGQAi3kdw/YzlArx/e5zYFXeGuLYFy',true, 'Stywar', 'vargas','stywar1.vargas@aforo255.com');
INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3, 2);