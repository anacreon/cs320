use cs320stu13;
drop table if exists users;
drop table if exists wiki_pages;
drop table if exists revisions;
create table users (
    id Integer auto_increment primary key,
    username varchar(255) not null unique,
    password varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255)
);
create table wiki_pages (
    id Integer auto_increment primary key,
    path varchar(255) not null unique,
    revision integer references revisions (id)
);

create table revisions (
    id Integer auto_increment primary key,
    page integer references wiki_pages (id),
    content varchar(255),
    author integer references users (id),
    date varchar(30) not null
);