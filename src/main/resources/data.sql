DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS USERTYPE;
DROP TABLE IF EXISTS GLUCOSEMEASUREMENT;

CREATE TABLE USERTYPE (ID INT PRIMARY KEY, NAME VARCHAR(64), DESCRIPTION VARCHAR(64));
CREATE TABLE USER (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  FIRSTNAME VARCHAR(64),
  LASTNAME VARCHAR(64),
  USERNAME VARCHAR(64),
  EMAIL VARCHAR(64),
  PASSWORD VARCHAR(64),
  USERTYPEID INT NOT NULL,
  FOREIGN KEY (USERTYPEID) REFERENCES USERTYPE(ID)
  );

CREATE TABLE GLUCOSEMEASUREMENT(
  ID INT AUTO_INCREMENT PRIMARY KEY,
  GLUCOSEVALUE DECIMAL,
  MEASUREDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  USERID INT NOT NULL,
  FOREIGN KEY (USERID) REFERENCES USER(ID));

insert into USERTYPE (id, name, description) values (1, 'User', 'a normal user');
insert into USERTYPE (id, name, description) values (2, 'Admin', 'an admin user');
INSERT INTO USER (ID, FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, USERTYPEID)
  VALUES (1, 'Martin', 'Bäumer', 'mba', 'martin.baeumer@gmail.com', 'mbpass', 1);

INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID) VALUES (6.2, 1);
INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID) VALUES (7.8, 1);
INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID) VALUES (8.7, 1);
INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID) VALUES (12.6, 1);
INSERT INTO GLUCOSEMEASUREMENT (GLUCOSEVALUE, USERID) VALUES (13.1, 1);
