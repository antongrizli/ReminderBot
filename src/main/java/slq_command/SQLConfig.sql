--Create table for storage user data
CREATE TABLE IF NOT EXISTS UserDetails(ID bigint auto_increment PRIMARY KEY, USER_ID BIGINT NOT NULL, FIRST_NAME VARCHAR(250), LAST_NAME VARCHAR(250), MOB_NUMBER VARCHAR(15) not null);
--Insert INTO USERDETAILS
insert into USERDETAILS (USER_ID, FIRST_NAME, LAST_NAME, MOB_NUMBER) values ('1234567890', 'Anton', 'Konikov', '+380533816456');
--Create table for REMINDS
CREATE TABLE IF NOT EXISTS REMIND_INFO(CHAT_ID bigint not null, USER_ID bigint not null, REMIND_DATE DATETIME not null, REMIND_MESSAGE CLOB, FOREIGN KEY (USER_ID) REFERENCES USERDETAILS(USER_ID))


