
CREATE USER 'testy'@'localhost' IDENTIFIED BY 'S3cr3t';

CREATE SCHEMA testy_schema DEFAULT CHARACTER SET latin1;

GRANT SELECT,INSERT,UPDATE,DELETE ON testy_schema.* TO 'testy'@'localhost'
