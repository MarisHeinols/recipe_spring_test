insert into users (id,name) values ('1','John');
insert into users (id,name) values  ('2','Allan');
insert into users (id,name) values  ('3','Jeff');

insert into recipes (id,name,recipe_text,user_id) values ('1','Doctor','Doctors apointment','1');
insert into recipes (id,name,recipe_text,user_id) values ('2','Vacation','Vacation to Ibiza','2');
insert into recipes (id,name,recipe_text,user_id) values('3','Shopping','Shopping in Rimi','3');

insert into comment (id,comment_text,recipe_id,user_id) values ('1','This was very expensive','1','1');
insert into comment (id,comment_text,recipe_id,user_id) values ('2','I will return','2','2');
insert into comment (id,comment_text,recipe_id,user_id) values('3','Spent all of my money','3','3');