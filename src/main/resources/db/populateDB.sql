DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  ('100000', '2016-04-11 10:35:58', 'ужин', '1000'),
  ('100000', '2016-04-11 10:35:58', 'завтрак', '600'),
  ('100001', '2016-04-12 10:35:58', 'обед', '3000'),
  ('100000', '2016-04-13 10:35:58', 'полудник', '500'),
  ('100001', '2016-04-13', 'жор', '2000');
