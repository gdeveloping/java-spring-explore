CREATE DATABASE local_test_db;
SHOW VARIABLES LIKE 'validate_password%';
set global validate_password.policy=LOW;
CREATE USER `local_debug_root`@`localhost` IDENTIFIED BY 'local_debug_pwd';
set global validate_password.policy='STRONG'; -- or 'MEDIUM'
GRANT ALL PRIVILEGES ON local_test_db.* TO `local_debug_root`@`localhost`;
GRANT ALL PRIVILEGES ON mysql.* TO `local_debug_root`@`localhost`;
GRANT SELECT ON performance_schema.* TO `local_debug_root`@`localhost`;

FLUSH PRIVILEGES;

SELECT USER, HOST FROM mysql.`user` u ;

SELECT * FROM performance_schema.data_locks dl ;