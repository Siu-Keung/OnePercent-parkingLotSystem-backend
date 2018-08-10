create table user(
id bigint auto_increment primary key,
name varchar(100),
user_name varchar(100),
password varchar(100),
email varchar(30),
phone  varchar(30),
login_flag varchar(2)
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

create table indent(
id bigint auto_increment primary key ,
receipt_no varchar(200) ,
car_no varchar(100),
coordinator_id bigint,
parking_lot_id bigint,
status varchar(20),
create_date datetime default now()
);

create table leaving(
id bigint auto_increment primary key,
employee_id bigint,
start_date datetime,
end_date datetime,
reason varchar(300),
status varchar(10),
comment varchar(300),
create_date datetime,
terminated int
);