DROP TABLE IF EXISTS EMPLOYEE ;
CREATE TABLE EMPLOYEE (id INT NOT NULL PRIMARY KEY,FIRST_NAME VARCHAR(255), LAST_NAME VARCHAR(255), MOBILE_NUMBER VARCHAR(100) UNIQUE, ID_NUMBER VARCHAR(100) UNIQUE, PHYSICAL_ADDRESS VARCHAR(255));