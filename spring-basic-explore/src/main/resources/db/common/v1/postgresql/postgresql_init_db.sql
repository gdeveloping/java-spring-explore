DROP DATABASE IF EXISTS local_test_db;
CREATE DATABASE local_test_db ENCODING utf8;
CREATE USER local_debug_root PASSWORD 'local_debug_pwd';
ALTER DATABASE local_test_db OWNER TO local_debug_root;

SELECT usename, usecreatedb, usesuper FROM pg_user;





