use cs320stu13;
drop table if exists users;
drop table if exists pages;
drop table if exists revisions;

create table users (
    username varchar(255) primary key,
    password varchar(255) not null,
    name varchar(255)
);
create table pages (
    id Integer auto_increment primary key,
    name varchar(255) not null,
    path varchar(255) not null unique,
    content varchar(255),
    date varchar(255)    
);

create table revisions (
    id Integer auto_increment primary key,
    page integer references pages (id),
    content varchar(255),
    author varchar(255) references users (username),
    date varchar(30) not null
);

insert into users values ('csun','abcd','Chengyu Sun');
insert into users values('jdoe','abcd','John Doe');
insert into users values('srice','abcd','Scott Rice');


insert into pages values(1, 'John Doe', 'index', 
'Welcome to CS320Wiki. To see all the pages currently hosted on the wiki, please click <a href ="/cs320stu13/PageList">Page List</a>',
now());

insert into pages(name, path, content, date) 
values('Scott Rice','mypage','My Page',now());

insert into revisions values(1, 1,
'Welcome to CS320Wiki. To see all the pages currently hosted on the wiki, please click <a href ="/cs320stu13/PageList">Page List</a>',
'jdoe', now());

insert into revisions(page, content, author, date)
values(2, 'My page', 'srice', now());