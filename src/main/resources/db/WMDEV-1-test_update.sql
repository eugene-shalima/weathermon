CREATE TABLE IF NOT EXISTS  users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS street_type
(
    id SERIAL
        PRIMARY KEY,
    name VARCHAR(128)
        NOT NULL
);

CREATE TABLE IF NOT EXISTS street
(
    id SERIAL
        PRIMARY KEY,
    type_id INTEGER
        NOT NULL
        REFERENCES street_type (id),
    name VARCHAR(128)
        NOT NULL
);