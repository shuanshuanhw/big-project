create table user_test
(
    id int primary key auto_increment,
    username char(30) not null unique
)engine=Innodb default charset=utf8 COMMENT='员工测试表' ;

delete from user_test;
select * from user_test;