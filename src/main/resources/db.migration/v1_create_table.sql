CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT AUTO_INCREMENT,
    email      VARCHAR(256) NOT NULL UNIQUE,
    password   VARCHAR(256) NOT NULL,
    first_name VARCHAR(256) NOT NULL,
    last_name  VARCHAR(256) NOT NULL,
    role       VARCHAR(256) NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS events
(
    id         BIGINT AUTO_INCREMENT,
    user_id    BIGINT       NOT NULL,
    event_name VARCHAR(256) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);


ALTER TABLE events
    ADD FOREIGN KEY (user_id) REFERENCES users (id);


CREATE TABLE IF NOT EXISTS files
(
    id                BIGINT AUTO_INCREMENT,
    event_id          BIGINT       NOT NULL,
    location          VARCHAR(256) NOT NULL,
    file_name         VARCHAR(256) NOT NULL UNIQUE,
    time_of_creating  TIMESTAMP,
    PRIMARY KEY(id)
);


ALTER TABLE files
    ADD FOREIGN KEY (event_id) REFERENCES events (id);

INSERT INTO public.users(email, password, first_name, last_name, role)
VALUES ('admin@mail.com', '$2a$12$75H4ERkrNDhEvaNrjA/G6O5Acl2mJSgM1ulDIJuC54M6CkRt6IZTa', 'Ivan', 'Petrov', 'ADMIN'),
       ('moderator@mail.com', '$2a$12$jLPfrV4hCXmc26HsDl2xWuo0W621MDd0BTEAk3Wb9r1V0/ofzTCge', 'Maksim', 'Sokolov', 'MODERATOR'),
       ('user@mail.com', '$2a$12$3GoUqcnTpyI9APdRr5GWr.1Sm6JcvwdslU77kVx2BXi1erlqueEwK', 'Oleg', 'Karpov', 'USER');

-- BCrypt(12)
-- admin = $2a$12$75H4ERkrNDhEvaNrjA/G6O5Acl2mJSgM1ulDIJuC54M6CkRt6IZTa
-- moderator = $2a$12$jLPfrV4hCXmc26HsDl2xWuo0W621MDd0BTEAk3Wb9r1V0/ofzTCge
-- user = $2a$12$3GoUqcnTpyI9APdRr5GWr.1Sm6JcvwdslU77kVx2BXi1erlqueEwK