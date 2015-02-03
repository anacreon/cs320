insert into users values (1,'csun','abcd','Chengyu', 'Sun','csun@calstatela.edu');
insert into users(username, password, first_name, last_name) 
values('srice','abcd','Scott', 'Rice');

insert into wiki_pages values(1, '/wiki/index',1);
insert into wiki_pages(path, revision) values('/wiki/mypage',2);

insert into revisions values(1, 1,'Welcome to cs320wiki',2, now());
insert into revisions(page, content, author, date) 
values(2,'test page', 1, now());
insert into revisions(page, content, author, date)
values(1,'Welcome to the wiki', 2, now());
