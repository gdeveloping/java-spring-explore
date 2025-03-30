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
    amount  DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES test_user(id)
);