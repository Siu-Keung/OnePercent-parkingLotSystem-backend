insert into user values(1, 'admin','admin', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','10@qq.com','110','1');
insert into user values(2, 'admin1','admin1', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','11@qq.com','111','1');
insert into user values(3, 'coordinator1','coordinator1', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','2000@qq.com','10086','3');
insert into user values(4, 'coordinator2','coordinator2', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','2000@qq.com','10086','3');

insert into role values(1,'Admin');
insert into role values(2,'Manager');
insert into role values(3,'ParkingBoy');
insert into role values(4,'Employee');

insert into user_role values(1,1,1);
insert into user_role values(2,2,1);

insert into parking_lot values(1, '停车场1', 50, 0, true, 1);
insert into parking_lot values(2, '停车场2', 100, 1, true, 1);
insert into parking_lot values(3, '停车场3', 120, 2, true, 2);

insert into indent(id, receipt_no, car_no, status) values(1, 'qwertu', '粤T895', 'pending');
insert into indent(id, receipt_no, car_no, status) values(2, '14fafaa', '粤T525', 'pending');
insert into indent(id, receipt_no, car_no, status, coordinator_id) values(3, 'fadfrtyu', '粤T295', 'accepted', 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(4, 'qrweryu', '粤T2495', 'parked', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(5, 'afwetyu', '粤T2325', 'waitingToRetrieve', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(6, 'werwyu', '粤T08295', 'retrieving', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(7, 'rwereyu', '粤T1895', 'finished', 1, 2);











