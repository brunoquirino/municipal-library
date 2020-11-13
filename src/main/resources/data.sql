DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS lendings;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id long primary key AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    age integer NOT NULL,
    phone VARCHAR(250) NOT NULL
);

CREATE TABLE lendings (
    id long primary key AUTO_INCREMENT,
    user_id long not null references users(id),
    days integer not null,
    date_delivery datetime,
    date_devolution datetime
);

CREATE TABLE books (
    id long primary key AUTO_INCREMENT,
    title VARCHAR(250) NOT NULL,
    summary TEXT NOT NULL,
    isbn VARCHAR(45) NOT NULL,
    author VARCHAR(255),
    year integer,
    lending_id long references lendings(id)
);