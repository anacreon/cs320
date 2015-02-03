-- 1.
select r.content, concat(u.first_name,' ',u.last_name) as author, r.date from revisions r
left join wiki_pages w on(r.page = w.id)
left join users u on (r.author = u.id)
where (r.date = (select max(r.date) from revisions r) and w.path ='/wiki/index');
-- 2.
select r.id, concat(u.first_name,' ',u.last_name) as author, r.date from revisions r
left join wiki_pages w on(r.page = w.id)
left join users u on (r.author = u.id)
where(w.path ='/wiki/index') order by(r.date);
-- 3.
select w.id, w.path, concat(u.first_name,' ',u.last_name) as author, r.date from revisions r
left join wiki_pages w on(r.page = w.id)
left join users u on (r.author = u.id)
where (r.date = (select max(r.date) from revisions r))
order by(w.id);
-- 4.
select ifnull((select 'yes' from wiki_pages w where(w.path ='/wiki/mypage1')), 'no');
-- 5.
select concat(u.first_name,' ',u.last_name) as author, 
count(concat(u.first_name,' ',u.last_name)) as number_of_revisions from revisions r
left join users u on (r.author = u.id)