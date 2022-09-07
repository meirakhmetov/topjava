DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User','user@yandex.ru','password'),
('Admin','admin@gmail.com','admin');

INSERT INTO user_roles(role, user_id) VALUES
('ROLE_USER',100000),
('ROLE_ADMIN',100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2022-08-30 10:00:00','Завтрак',500,100000),
       ('2022-08-30 13:00:00','Обед',1000,100000),
       ('2022-08-30 20:00:00','Ужин',500,100000),
       ('2022-08-31 10:00:00','Завтрак',500,100000),
       ('2022-08-31 13:00:00','Обед',1000,100000),
       ('2022-08-31 20:00:00','Ужин',500,100000),
       ('2022-08-31 23:00:00','Поздний ужин',500,100000),
       ('2022-09-01 14:00:00','Админ ланч',510,100001),
       ('2022-09-01 21:00:00','Админ ужин',1500,100001);
