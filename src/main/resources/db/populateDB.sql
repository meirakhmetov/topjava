DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User','user@yandex.kz','password'),
('Admin','admin@admin.kz','adminpassword');

INSERT INTO user_roles(role, user_id) VALUES
('ROLE_USER',100000),
('ROLE_ADMIN',100001);