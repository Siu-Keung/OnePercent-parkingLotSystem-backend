create table user(
id bigint auto_increment primary key,
name varchar(100),
user_name varchar(100),
password varchar(100),
email varchar(30),
phone  varchar(30),
login_flag varchar(2),--能否允许系统，0代表被冻结，1代表可以登录
);

create table role(
id bigint auto_increment primary key,
name varchar(100)

);

create table user_role(
id bigint auto_increment primary key,
user_id bigint,
role_id bigint
);


create table parking_lot(
id bigint auto_increment primary key,
name varchar(50),
total_size int,
spare_size int,
available bool,
coordinator_id bigint
);