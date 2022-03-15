CREATE TABLE IF NOT EXISTS todos(
    id integer NOT NULL AUTO_INCREMENT,
    user_id integer NOT NULL,
    title varchar(128) NOT NULL,
    created_at datetime NOT NULL,
    PRIMARY KEY(id)
);
