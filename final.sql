use cs320stu13;
drop table if exists instructors;
drop table if exists office_hours;
drop table if exists days;
drop table if exists hours;

create table instructors(
    name varchar(255) primary key
);
create table office_hours (
    name varchar(255) references instructors(name),
    day varchar(20) references days(day),
    hour varchar(20) references hours(hour)
);
create table days(
    day varchar(20)
);
create table hours(
    hour varchar(20)
);

insert into instructors values('Sun');

insert into office_hours values('Sun','Tuesday','13:00-14:00');
insert into office_hours values('Sun','Thursday','13:00-14:00');
insert into office_hours values('Sun','Monday','16:00-17:00');

insert into days values('Monday');
insert into days values('Tuesday');
insert into days values('Wednesday');
insert into days values('Thursday');
insert into days values('Friday');

insert into hours values('8:00-9:00');
insert into hours values('9:00-10:00');
insert into hours values('10:00-11:00');
insert into hours values('11:00-12:00');
insert into hours values('12:00-13:00');
insert into hours values('13:00-14:00');
insert into hours values('14:00-15:00');
insert into hours values('15:00-16:00');
insert into hours values('16:00-17:00');