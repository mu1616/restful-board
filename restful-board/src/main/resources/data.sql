insert into user (join_date, name, password, ssn) values (sysdate(), 'user1', 'test', '970816-1111111')
insert into user (join_date, name, password, ssn) values (sysdate(), 'user2', 'test', '960816-1111111')
insert into user (join_date, name, password, ssn) values (sysdate(), 'user3', 'test', '950816-1111111')


insert into post (description, user_id) values ('My first post', 1)
insert into post (description, user_id) values ('My second post', 1)
