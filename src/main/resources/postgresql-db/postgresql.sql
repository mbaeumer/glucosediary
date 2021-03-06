DROP TABLE IF EXISTS GLUCOSEUSER;
DROP TABLE IF EXISTS USERTYPE;
DROP TABLE IF EXISTS GLUCOSEMEASUREMENT;
DROP SEQUENCE IF EXISTS SEQ_USERTYPE;
DROP SEQUENCE IF EXISTS SEQ_GLUCOSEUSER;
DROP SEQUENCE IF EXISTS SEQ_GLUCOSEMEASUREMENT;

CREATE SEQUENCE SEQ_USERTYPE;
CREATE TABLE USERTYPE (
  ID BIGINT DEFAULT NEXTVAL('SEQ_USERTYPE') NOT NULL PRIMARY KEY,
  NAME VARCHAR(64),
  DESCRIPTION VARCHAR(64)
);
ALTER SEQUENCE SEQ_USERTYPE OWNED BY USERTYPE.ID;

CREATE SEQUENCE SEQ_GLUCOSEUSER;
CREATE TABLE GLUCOSEUSER (
  ID BIGINT DEFAULT NEXTVAL('SEQ_GLUCOSEUSER') NOT NULL PRIMARY KEY,
  FIRSTNAME VARCHAR(64),
  LASTNAME VARCHAR(64),
  USERNAME VARCHAR(64),
  EMAIL VARCHAR(64),
  PASSWORD VARCHAR(64),
  USERTYPEID INT NOT NULL,
  FOREIGN KEY (USERTYPEID) REFERENCES USERTYPE(ID)
);
ALTER SEQUENCE SEQ_GLUCOSEUSER OWNED BY GLUCOSEUSER.ID;

CREATE SEQUENCE SEQ_GLUCOSEMEASUREMENT;
CREATE TABLE GLUCOSEMEASUREMENT(
  ID BIGINT DEFAULT NEXTVAL('SEQ_GLUCOSEMEASUREMENT') NOT NULL PRIMARY KEY,
  GLUCOSEVALUE DECIMAL,
  MEASUREDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  USERID INT NOT NULL,
  FOREIGN KEY (USERID) REFERENCES GLUCOSEUSER(ID)
);
ALTER SEQUENCE SEQ_GLUCOSEMEASUREMENT OWNED BY GLUCOSEMEASUREMENT.ID;
ALTER TABLE GLUCOSEMEASUREMENT ADD COLUMN COMMENT VARCHAR(64);


insert into USERTYPE (id, name, description) values (1, 'User', 'a normal user');
insert into USERTYPE (id, name, description) values (2, 'Admin', 'an admin user');
INSERT INTO GLUCOSEUSER (ID, FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, USERTYPEID)
  VALUES (1, 'Martin', 'Bäumer', 'mba', 'martin.baeumer@gmail.com', 'mbpass', 1);

