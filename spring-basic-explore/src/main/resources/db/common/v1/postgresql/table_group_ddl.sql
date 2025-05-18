CREATE TABLE IF NOT EXISTS test_code (
    id   INT PRIMARY KEY,
    code INT,
    note VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS test_user (
    id    INT PRIMARY KEY,
    name  VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS test_order (
    id      INT PRIMARY KEY,
    user_id INT,
    amount  DECIMAL(10, 2)
);

CREATE TABLE "user"
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    birthday DATE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE "order"
(
    id      SERIAL PRIMARY KEY,
    user_id INT,
    name    VARCHAR(50) NOT NULL
);
