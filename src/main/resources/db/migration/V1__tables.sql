create table coordinator(
id bigint auto_increment primary key,
name varchar(100),
password varchar(100)
);

create table parking_lot(
id bigint auto_increment primary key,
name varchar(50),
total_size int,
spare_size int,
available bool,
coordinator_id bigint
);