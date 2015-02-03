use cs320stu13;
drop table if exists customers;
drop table if exists rewards;
create table rewards(
code varchar(4) primary key,
reward varchar(255),
claimed_by varchar(255) unique references customers(email)
);
create table customers(
email varchar(255) primary key references rewards(claimed_by),
name varchar(255)
);

insert into rewards values('2576', 'a Tv', 'john@hotmail.com');
insert into rewards(code) values('2886');
insert into rewards(code, reward) values('2567','an iPod');
insert into rewards(code) values('2625');

insert into customers values('john@hotmail.com', 'John');