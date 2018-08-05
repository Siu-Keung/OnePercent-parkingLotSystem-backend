insert into user values(1, 'admin','admin', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','10@qq.com','110','1');
insert into user values(2, 'admin1','admin1', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','11@qq.com','111','1');
insert into user values(3, 'coordinator1','coordinator1', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','2000@qq.com','10086','1');
insert into user values(4, 'coordinator2','coordinator2', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','2000@qq.com','10086','1');

insert into role values(1,'Admin');
insert into role values(2,'Manager');
insert into role values(3,'ParkingBoy');
insert into role values(4,'Employee');

insert into user_role values(1,1,1);
insert into user_role values(2,2,2);
insert into user_role values(3,3,3);
insert into user_role values(4,4,3);

insert into parking_lot values(1, '停车场1', 50, 50, true, 1);
insert into parking_lot values(2, '停车场2', 100, 45, true, 1);
insert into parking_lot values(3, '停车场3', 100, 100, true,null );
insert into parking_lot values(4, '停车场4', 100, 50, true, 2);
insert into parking_lot values(5, '停车场5', 100, 100, true, null );
insert into parking_lot values(6, '停车场6', 100, 100, true, null );
insert into parking_lot values(7, '停车场7', 100, 100, true, 2);
insert into parking_lot values(8, '停车场8', 100, 19, true, 2);
insert into parking_lot values(9, '停车场9', 100, 70, true, 2);
insert into parking_lot values(10, '停车场10', 100, 23, true, 2);
insert into parking_lot values(11, '停车场11', 100, 23, true, 2);
insert into parking_lot values(12, '停车场12', 100, 54, true, 2);
insert into parking_lot values(13, '停车场13', 100, 53, true, 2);
insert into parking_lot values(14, '停车场14', 100, 75, true, 2);
insert into parking_lot values(15, '停车场15', 100, 23, true, 2);
insert into parking_lot values(16, '停车场16', 100, 86, true, 2);
insert into parking_lot values(17, '停车场17', 100, 46, true, 2);
insert into parking_lot values(18, '停车场18', 100, 26, true, 2);
insert into parking_lot values(19, '停车场19', 100, 50, true, 2);
insert into parking_lot values(20, '停车场20', 100, 87, true, 2);
insert into parking_lot values(21, '停车场21', 100, 100, true, null);
insert into parking_lot values(22, '停车场22', 100, 100, true,null );
insert into parking_lot values(23, '停车场23', 100, 100, true, null);





insert into indent(id, receipt_no, car_no, status) values(1, 'qwertu', '粤T895', 'pending');
insert into indent(id, receipt_no, car_no, status) values(2, '14fafaa', '粤T525', 'pending');
insert into indent(id, receipt_no, car_no, status, coordinator_id) values(3, 'fadfrtyu', '粤T295', 'accepted', 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(4, 'qrweryu', '粤T2495', 'parked', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(5, 'afwetyu', '粤T2325', 'waitingToRetrieve', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(6, 'werwyu', '粤T08295', 'retrieving', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(7, 'rwereyu', '粤T1895', 'finished', 1, 2);











