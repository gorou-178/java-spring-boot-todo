CREATE TABLE IF NOT EXISTS users(
    id integer NOT NULL AUTO_INCREMENT,
    username varchar_ignorecase(255) not null,
    password varchar_ignorecase(500) not null,
    PRIMARY KEY (id)
);
