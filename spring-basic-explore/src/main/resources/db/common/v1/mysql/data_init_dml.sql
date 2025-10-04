TRUNCATE test_code;
TRUNCATE test_user;
TRUNCATE test_order;
TRUNCATE `order`;
TRUNCATE `user`;

INSERT INTO test_code VALUES (1, 1001, 'test code 1');
INSERT INTO test_code VALUES (2, 2001, 'test code 2');

INSERT INTO test_user VALUES (1, 'Alice', 'alice@example.com');
INSERT INTO test_user VALUES (2, 'Bob', 'bob@example.com');

INSERT INTO test_order VALUES (1, 1, 99.99);
INSERT INTO test_order VALUES (2, 2, 199.50);

INSERT INTO `user` (username, birthday, password) VALUES ('Alice', '1990-01-01', 'password1');
INSERT INTO `user` (username, birthday, password) VALUES ('Bob', '1992-02-02', 'password2');

INSERT INTO `order` (user_id, name) VALUES (1, 'Order1');
INSERT INTO `order` (user_id, name) VALUES (1, 'Order2');
INSERT INTO `order` (user_id, name) VALUES (2, 'Order3');