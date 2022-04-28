INSERT INTO roles (name)
VALUES ('ROLE_USER');

INSERT INTO users (username, password)
VALUES ('ksga', '$2a$10$RZBk0j2RTzjv3IJB84u25OLw5NjX4xlu/EbG.91vWsJJ1pR0DkIvu');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);

INSERT INTO articles (title)
VALUES ('Article 1');

INSERT INTO articles (title)
VALUES ('Article 2');