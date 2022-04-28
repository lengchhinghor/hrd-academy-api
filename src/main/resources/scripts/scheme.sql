DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users_roles;

CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);

CREATE TABLE roles
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE articles
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL
);

CREATE TABLE ha_slide
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    url VARCHAR(250) NOT NULL

);




-- CREATE SEQUENCE users_id;
-- CREATE TABLE users
-- (
--     id       INT NOT NULL DEFAULT nextval('users_id'),
--     username VARCHAR(250) NOT NULL,
--     password VARCHAR(250) NOT NULL
-- );
-- ALTER SEQUENCE users_id
--     OWNED BY users.id;
--
--     CREATE SEQUENCE roles_id;
--     CREATE TABLE roles
--     (
--         id       INT NOT NULL DEFAULT nextval('roles_id'),
--         name VARCHAR(250) NOT NULL
--     );
--     ALTER SEQUENCE roles_id
--         OWNED BY roles.id;