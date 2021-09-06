CREATE TABLE IF NOT EXISTS user_roles
(
    role_id SERIAL
        PRIMARY KEY,
    role_name VARCHAR(64)
        NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    user_id SERIAL
        PRIMARY KEY,
    role_id INTEGER
        NOT NULL
        REFERENCES user_roles (role_id),
    user_name VARCHAR(128)
        NOT NULL,
    email VARCHAR(128),
    user_register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_last_login_date TIMESTAMP,
    user_password VARCHAR(128),
    user_active BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS devices
(
    device_id SERIAL
        PRIMARY KEY,
    user_id  INTEGER
        NOT NULL
        REFERENCES users (user_id),
    device_name VARCHAR(128)
        NOT NULL,
    device_mac_address VARCHAR(17),
    device_register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    device_geo_location POINT
);

CREATE TABLE IF NOT EXISTS sensors
(
    sensor_id SERIAL
        PRIMARY KEY,
    device_id  INTEGER
        NOT NULL
        REFERENCES devices (device_id),
    /* sensor_type VARCHAR or create a new table with types??? */
    sensor_name VARCHAR(128)
        NOT NULL,
    sensor_data REAL


);
