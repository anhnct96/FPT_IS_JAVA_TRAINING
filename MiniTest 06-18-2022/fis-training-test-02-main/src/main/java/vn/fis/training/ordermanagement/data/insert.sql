INSERT INTO `tbl_customer` VALUES
(1,'nam dinh','0125416879','Thao'),
(2,'ha noi','083145279','Ha'),
(3,'TPHCM','0911928467','Nam');

INSERT INTO `tbl_order` VALUES
(1,'2022-06-18 00:00:00.000000','PAID',340000,1),
(2,'2022-06-18 00:00:00.000000','WAITING_APPROVAL',450000,2),
(3,'2022-06-18 00:00:00.000000','CREATED',150000,3),
(4,'2022-06-18 00:00:00.000000','PAID',50000,1),
(5,'2022-06-18 00:00:00.000000','CANCELLED',250000,3),
(6,'2022-06-18 00:00:00.000000','PAID',5000,1),
(7,'2022-06-18 00:00:00.000000','PAID',2500,1);

INSERT INTO `tbl_order_item` VALUES
(1,1,1,500,5),
(2,2,2,500,5),
(3,3,3,500,5),
(4,4,4,500,5),
(5,5,5,500,5),
(6,1,5,500,5);

INSERT INTO `tbl_product` VALUES
(1,'mi tom',4000),
(2,'trung',3000),
(3,'sting',8000),
(4,'nutriboost',10000),
(5,'quay',5000);


