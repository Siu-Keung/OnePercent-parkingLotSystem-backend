insert into user values(1, 'admin','admin', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','xwh123456@oocl.com','15903242354','1');
insert into user values(2, 'admin1','admin1', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','leon1590432@oocl.com','13657393405','1');
insert into user values(3, 'boy1','boy1', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','guoabby@oocl.com','18923453465','1');
insert into user values(4, 'boy2','boy2', '$2a$10$UJW/Q1BGPctlUrLznCt/XOe/5zOL2TTpIaDxiEX.iA6fQKqc859jy','dylan@oocl.com','12398340543','1');

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
insert into parking_lot values(7, '停车场7', 100, 100, true, 3);
insert into parking_lot values(8, '停车场8', 100, 19, true, 4);
insert into parking_lot values(9, '停车场9', 100, 70, true, 2);
insert into parking_lot values(10, '停车场10', 100, 23, true, 2);
insert into parking_lot values(11, '停车场11', 100, 23, true, 4);
insert into parking_lot values(12, '停车场12', 100, 54, true, 2);
insert into parking_lot values(13, '停车场13', 100, 53, true, 2);
insert into parking_lot values(14, '停车场14', 100, 75, true, 2);
insert into parking_lot values(15, '停车场15', 100, 23, true, 3);
insert into parking_lot values(16, '停车场16', 100, 86, true, 2);
insert into parking_lot values(17, '停车场17', 100, 46, true, 3);
insert into parking_lot values(18, '停车场18', 100, 26, true, 2);
insert into parking_lot values(19, '停车场19', 100, 50, true, 3);
insert into parking_lot values(20, '停车场20', 100, 87, true, 2);


insert into parking_lot values(21, '停车场21', 100, 100, true, null);
insert into parking_lot values(22, '停车场22', 100, 100, true,null );
insert into parking_lot values(23, '停车场23', 100, 100, true, null);





insert into indent(id, receipt_no, car_no, status) values(1, 'ita123456', '粤T8zq95', 'pending');
insert into indent(id, receipt_no, car_no, status) values(2, 'ita123457', '粤T53425', 'pending');
insert into indent(id, receipt_no, car_no, status, coordinator_id) values(3, 'ita123458', '粤T29e55', 'accepted', 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(4, 'ita123459', '粤T276495', 'parked', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(5, 'ita133456', '粤T232ty5', 'waitingToRetrieve', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(6, 'ita133556', '粤T08z5295', 'retrieving', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(7, 'ita132456', '粤T189j55', 'finished', 1, 2);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(8, 'ita132475', '粤T18ke95', 'finished', 2, 12);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(9, 'ita139256', '粤T18m695', 'finished', 3, 19);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(10, 'ita174456', '粤T18p395', 'finished', 3, 17);
insert into indent(id, receipt_no, car_no, status, coordinator_id) values(11, 'ita123498', '粤T29er5', 'accepted', 3);
insert into indent(id, receipt_no, car_no, status, coordinator_id) values(12, 'ita123328', '粤T29e3t5', 'accepted', 2);
insert into indent(id, receipt_no, car_no, status, coordinator_id) values(13, 'ita223458', '粤A29e5m', 'accepted', 3);
insert into indent(id, receipt_no, car_no, status, coordinator_id) values(14, 'ita193458', '粤A29eq5', 'accepted', 2);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(15, 'ita323459', '粤T276495', 'parked', 1, 1);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(16, 'ita343459', '粤T27E645', 'parked', 2, 12);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(17, 'ita453459', '粤T274V95', 'parked', 3, 17);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(18, 'ita134356', '粤T232tw5', 'waitingToRetrieve', 2, 12);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(19, 'ita903456', '粤T582ty5', 'waitingToRetrieve', 3, 17);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(20, 'ita563556', '粤TL8z5295', 'retrieving', 2, 12);
insert into indent(id, receipt_no, car_no, status, coordinator_id, parking_lot_id) values(21, 'ita783556', '粤TG8z5295', 'retrieving', 3, 17);

insert into leaving(id, employee_id, start_date, end_date, reason, status, comment, create_date) values(1, 3, now(), now(), '生病了', 'pending', '', now());
insert into leaving(id, employee_id, start_date, end_date, reason, status, comment, create_date) values(2, 3, now(), now(), '喝喜酒', 'approval', '同意请假', now());
insert into leaving(id, employee_id, start_date, end_date, reason, status, comment, create_date) values(3, 3, now(), now(), '喝喜酒', 'rejected', '拒绝', now());



