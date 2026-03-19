-- Insert data for User entity
INSERT INTO users (id, first_name, last_name, id_number) VALUES (1, 'Lucía', 'González', '12345678A');
INSERT INTO users (id, first_name, last_name, id_number) VALUES (2, 'Manuel', 'Rodríguez', '87654321B');
INSERT INTO users (id, first_name, last_name, id_number) VALUES (3, 'Carlos', 'Fernández', '11223344C');
INSERT INTO users (id, first_name, last_name, id_number) VALUES (4, 'Daniela', 'Martínez', '44332211D');

-- Insert data for Relative entity
INSERT INTO relatives (id, first_name, last_name, id_number) VALUES (1, 'Elena', 'Pérez', '98765432E');
INSERT INTO relatives (id, first_name, last_name, id_number) VALUES (2, 'Francisco', 'López', '23456789F');
INSERT INTO relatives (id, first_name, last_name, id_number) VALUES (3, 'Gloria', 'Sánchez', '34567890G');
INSERT INTO relatives (id, first_name, last_name, id_number) VALUES (4, 'Hilda', 'Romero', '09876543H');

-- Insert data for CentreResponsible entity
INSERT INTO centre_responsibles (id, first_name, last_name, id_number) VALUES (1, 'Iván', 'Díaz', '10293847I');
INSERT INTO centre_responsibles (id, first_name, last_name, id_number) VALUES (2, 'Julia', 'Muñoz', '74839201J');
INSERT INTO centre_responsibles (id, first_name, last_name, id_number) VALUES (3, 'Kevin', 'Ruiz', '65748392K');
INSERT INTO centre_responsibles (id, first_name, last_name, id_number) VALUES (4, 'Laura', 'Jiménez', '21345678L');

-- Insert data for Metric entity
INSERT INTO metrics (id, date_time, metric_category, metric_value, user_id, centre_responsible_id) VALUES (1, '2023-01-01 10:00:00', 'WEIGHT', 70.5, 1, 1);
INSERT INTO metrics (id, date_time, metric_category, metric_value, user_id, centre_responsible_id) VALUES (2, '2023-01-01 10:00:00', 'HEIGHT', 175.0, 1, 1);
INSERT INTO metrics (id, date_time, metric_category, metric_value, user_id, centre_responsible_id) VALUES (3, '2023-01-02 11:00:00', 'WEIGHT', 65.2, 2, 2);
INSERT INTO metrics (id, date_time, metric_category, metric_value, user_id, centre_responsible_id) VALUES (4, '2023-01-02 11:00:00', 'HEIGHT', 160.0, 2, 2);

-- Insert data for JournalPage entity
INSERT INTO journal_pages (id, date, breakfast, lunch, afternoon_snack, urination, defecation, centre_comment, relatives_comment, user_id, centre_responsible_id) VALUES (1, '2023-01-01', 'GOOD', 'FAIR', 'BAD', TRUE, FALSE, 'El usuario parecía contento.', 'Visitado hoy.', 1, 1);
INSERT INTO journal_pages (id, date, breakfast, lunch, afternoon_snack, urination, defecation, centre_comment, relatives_comment, user_id, centre_responsible_id) VALUES (2, '2023-01-02', 'FAIR', 'GOOD', 'GOOD', TRUE, TRUE, 'El usuario estuvo un poco callado.', 'Sin comentarios.', 2, 2);
INSERT INTO journal_pages (id, date, breakfast, lunch, afternoon_snack, urination, defecation, centre_comment, relatives_comment, user_id, centre_responsible_id) VALUES (3, '2023-01-03', 'BAD', 'FAIR', 'GOOD', TRUE, TRUE, 'El usuario tuvo un buen día.', 'Vendré mañana.', 3, 3);
INSERT INTO journal_pages (id, date, breakfast, lunch, afternoon_snack, urination, defecation, centre_comment, relatives_comment, user_id, centre_responsible_id) VALUES (4, '2023-01-04', 'GOOD', 'GOOD', 'FAIR', TRUE, FALSE, 'El usuario estaba con energía.', 'Llamé para preguntar.', 4, 4);

-- Insert data for users_relatives join table
INSERT INTO users_relatives (user_id, relative_id) VALUES (1, 1);
INSERT INTO users_relatives (user_id, relative_id) VALUES (1, 2);
INSERT INTO users_relatives (user_id, relative_id) VALUES (2, 3);
INSERT INTO users_relatives (user_id, relative_id) VALUES (3, 3);
INSERT INTO users_relatives (user_id, relative_id) VALUES (4, 4);

-- Reset sequences for tables with IDENTITY columns
ALTER TABLE users ALTER COLUMN id RESTART WITH 5;
ALTER TABLE relatives ALTER COLUMN id RESTART WITH 5;
ALTER TABLE centre_responsibles ALTER COLUMN id RESTART WITH 5;
ALTER TABLE metrics ALTER COLUMN id RESTART WITH 5;
ALTER TABLE journal_pages ALTER COLUMN id RESTART WITH 5;
