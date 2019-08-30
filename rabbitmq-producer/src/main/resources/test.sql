/*
MySQL Backup
Database: test
Backup Time: 2019-06-05 13:15:34
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `test`.`broker_message_log`;
DROP TABLE IF EXISTS `test`.`t_order`;
CREATE TABLE `broker_message_log` (
  `message_id` varchar(128) NOT NULL,
  `message` varchar(4000) DEFAULT NULL,
  `try_count` int(4) DEFAULT '0',
  `status` varchar(10) DEFAULT '',
  `next_retry` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `t_order` (
  `id` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `message_id` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `test`.`broker_message_log` WRITE;
DELETE FROM `test`.`broker_message_log`;
INSERT INTO `test`.`broker_message_log` (`message_id`,`message`,`try_count`,`status`,`next_retry`,`create_time`,`update_time`) VALUES ('1559710981215$f272a047-7f7d-4d7e-aa7c-76d8d6ba7b08', '{\"id\":2018092101,\"messageId\":\"1559710981215$f272a047-7f7d-4d7e-aa7c-76d8d6ba7b08\",\"name\":\"测试订单1\"}', 3, '2', '2019-06-05 05:04:01', '2019-06-05 05:03:02', '2019-06-05 05:03:41'),('1559711486759$35b76325-bca0-4f64-bd18-fd096dc8a398', '{\"id\":\"5c5de6d2-2806-4c4c-b48a-484df197c0eb\",\"messageId\":\"1559711486759$35b76325-bca0-4f64-bd18-fd096dc8a398\",\"name\":\"测试订单1\"}', 0, '1', '2019-06-05 05:12:27', '2019-06-05 05:11:27', '2019-06-05 05:11:27'),('1559711620685$842a20b0-bf1e-4cf9-b5b1-0c6ddf3307bb', '{\"id\":\"4b23e830-a270-40ad-bd16-2d43ae824873\",\"messageId\":\"1559711620685$842a20b0-bf1e-4cf9-b5b1-0c6ddf3307bb\",\"name\":\"测试订单1\"}', 3, '2', '2019-06-05 05:14:41', '2019-06-05 05:13:41', '2019-06-05 05:14:12');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `test`.`t_order` WRITE;
DELETE FROM `test`.`t_order`;
INSERT INTO `test`.`t_order` (`id`,`name`,`message_id`) VALUES ('2018092101', '测试订单1', '1559710981215$f272a047-7f7d-4d7e-aa7c-76d8d6ba7b08'),('4b23e830-a270-40ad-bd16-2d43ae824873', '测试订单1', '1559711620685$842a20b0-bf1e-4cf9-b5b1-0c6ddf3307bb'),('5c5de6d2-2806-4c4c-b48a-484df197c0eb', '测试订单1', '1559711486759$35b76325-bca0-4f64-bd18-fd096dc8a398');
UNLOCK TABLES;
COMMIT;
