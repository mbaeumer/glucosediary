DROP TABLE IF EXISTS GLUCOSEUSER;
DROP TABLE IF EXISTS USERTYPE;
DROP TABLE IF EXISTS GLUCOSEMEASUREMENT;
DROP SEQUENCE IF EXISTS SEQ_USERTYPE;
DROP SEQUENCE IF EXISTS SEQ_GLUCOSEUSER;
DROP SEQUENCE IF EXISTS SEQ_GLUCOSEMEASUREMENT;

CREATE SEQUENCE SEQ_USERTYPE;
CREATE TABLE USERTYPE (
  ID BIGINT DEFAULT (NEXT VALUE FOR SEQ_USERTYPE) NOT NULL PRIMARY KEY,
  NAME VARCHAR(64),
  DESCRIPTION VARCHAR(64)
);

CREATE SEQUENCE SEQ_GLUCOSEUSER;
CREATE TABLE GLUCOSEUSER (
  ID BIGINT DEFAULT (NEXT VALUE FOR SEQ_GLUCOSEUSER) NOT NULL PRIMARY KEY,
  FIRSTNAME VARCHAR(64),
  LASTNAME VARCHAR(64),
  USERNAME VARCHAR(64),
  EMAIL VARCHAR(64),
  PASSWORD VARCHAR(64),
  USERTYPEID INT NOT NULL,
  FOREIGN KEY (USERTYPEID) REFERENCES USERTYPE(ID)
);

CREATE SEQUENCE SEQ_GLUCOSEMEASUREMENT;
CREATE TABLE GLUCOSEMEASUREMENT(
  ID BIGINT DEFAULT (NEXT VALUE FOR SEQ_GLUCOSEMEASUREMENT) NOT NULL PRIMARY KEY,
  GLUCOSEVALUE DECIMAL,
  MEASUREDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  USERID INT NOT NULL,
  FOREIGN KEY (USERID) REFERENCES GLUCOSEUSER(ID)
);

insert into USERTYPE (id, name, description) values (1, 'User', 'a normal user');
insert into USERTYPE (id, name, description) values (2, 'Admin', 'an admin user');
INSERT INTO GLUCOSEUSER (ID, FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, USERTYPEID)
  VALUES (1, 'Martin', 'Bäumer', 'mba', 'martin.baeumer@gmail.com', 'mbpass', 1);
INSERT INTO GLUCOSEUSER (ID, FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, USERTYPEID)
  VALUES (2, 'Jonas', 'Jaconelli', 'jja', 'jja@softhouse.se', 'jjpass', 1);
INSERT INTO GLUCOSEUSER (ID, FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, USERTYPEID)
  VALUES (3, 'Lena', 'Green', 'leg', 'leg@softhouse.se', 'lgpass', 1);

INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID, MEASUREDATE) VALUES (5.4, 1, parsedatetime('13-08-2016 14:40', 'dd-MM-yyyy hh:mm'));
INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID, MEASUREDATE) VALUES (6.6, 1, parsedatetime('13-08-2016 09:20', 'dd-MM-yyyy hh:mm'));
INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID, MEASUREDATE) VALUES (10.3, 1, parsedatetime('12-08-2016 22:35', 'dd-MM-yyyy hh:mm'));
INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID, MEASUREDATE) VALUES (5.8, 1, parsedatetime('12-08-2016 17:45', 'dd-MM-yyyy hh:mm'));

