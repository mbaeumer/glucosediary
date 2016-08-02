DROP TABLE IF EXISTS USERTYPE;
CREATE TABLE USERTYPE (ID INT PRIMARY KEY, NAME VARCHAR(64), DESCRIPTION VARCHAR(64));
insert into USERTYPE (id, name, description) values (1, 'User', 'a normal user');
insert into USERTYPE (name, description) values ('Admin', 'an admin user');
