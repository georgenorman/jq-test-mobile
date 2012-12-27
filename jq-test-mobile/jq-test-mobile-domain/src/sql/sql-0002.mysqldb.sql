
--
-- MySQL
--

CREATE TABLE testy_schema.TEXT_ENVELOPE (
    TEXT_ENVELOPE_ID int NOT NULL,
    PATH varchar(1024) NOT NULL,
    NAME varchar(256) NOT NULL,
    DATA varchar(32768) NOT NULL,
  PRIMARY KEY (TEXT_ENVELOPE_ID)
) ENGINE=INNODB;


CREATE TABLE testy_schema.GUEST_BOOK_MESSAGE (
    GUEST_BOOK_MESSAGE_ID int NOT NULL,
    TITLE varchar(256) NOT NULL,
    GUEST_NAME varchar(256) NOT NULL,
    LOCATION varchar(256) NOT NULL,
    MESSAGE varchar(1024) NOT NULL,
    POST_DATE DATE NOT NULL,
  PRIMARY KEY (GUEST_BOOK_MESSAGE_ID)
) ENGINE=INNODB;


CREATE TABLE testy_schema.USER_DETAILS (
    USER_DETAILS_ID int NOT NULL,
    FIRST_NAME varchar(255),
    MIDDLE_NAME varchar(255),
    LAST_NAME varchar(255),
    PRIMARY_EMAIL varchar(255),
    SECONDARY_EMAIL varchar(255),
    WORK_EMAIL varchar(255),
    PRIMARY_PHONE varchar(255),
    SECONDARY_PHONE varchar(255),
    WORK_PHONE varchar(255),
  PRIMARY KEY (USER_DETAILS_ID)
) ENGINE=INNODB;


CREATE TABLE testy_schema.REGISTERED_USER (
    REGISTERED_USER_ID int NOT NULL,
    LOGIN_ID varchar(255) NOT NULL,
    PASSWORD varchar(255),
    TEMPORARY_PASSWORD varchar(255),
    INVALID_LOGIN_COUNT int NOT NULL,
    INVALID_LOGIN_LOCKOUT_TIME bigint NOT NULL,
    LAST_LOGIN_DATE DATE,
    STATUS varchar(255),
    DETAILS int NOT NULL,
    DATA_STORE_INFO varchar(4095) NOT NULL,
  FOREIGN KEY (DETAILS) REFERENCES USER_DETAILS(USER_DETAILS_ID),
  PRIMARY KEY (REGISTERED_USER_ID)
) ENGINE=INNODB;


CREATE TABLE testy_schema.PERMISSION (
    PERMISSION_ID int NOT NULL,
    DOMAIN varchar(255) NOT NULL,
    USER_ACTIONS varchar(255) NOT NULL,
    DESCRIPTION varchar(1024),
  PRIMARY KEY (PERMISSION_ID)
) ENGINE=INNODB;


CREATE TABLE testy_schema.REGISTERED_USER_PERMISSION_ASSOC (
    REGISTERED_USER_ID int NOT NULL,
    PERMISSION_ID int NOT NULL,
  PRIMARY KEY (REGISTERED_USER_ID, PERMISSION_ID),
  FOREIGN KEY (REGISTERED_USER_ID) REFERENCES REGISTERED_USER(REGISTERED_USER_ID),
  FOREIGN KEY (PERMISSION_ID) REFERENCES PERMISSION(PERMISSION_ID)
) ENGINE=INNODB;

