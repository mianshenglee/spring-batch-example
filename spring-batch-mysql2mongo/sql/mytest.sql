CREATE database if NOT EXISTS `mytest` default character set utf8mb4 collate utf8mb4_general_ci;
use `mytest`;

-- 创建user表
CREATE TABLE `test_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `title` varchar(32) DEFAULT NULL COMMENT '职称职别',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `gender` varchar(32) DEFAULT NULL COMMENT '性别',
  `date_of_birth` date DEFAULT NULL COMMENT '出生时间',
  `sys_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sys_create_user` varchar(255) DEFAULT NULL,
  `sys_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sys_update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7000 DEFAULT CHARSET=utf8;


insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3496','梁**','13800000000',NULL,NULL,'1','1947-11-01','2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin');
insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3497','王**','13800000000','test','test1@qq.com','1','1958-07-01','2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin');
insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3498','廖**','13800000000',NULL,NULL,'2','1958-08-01','2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin');
insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3499','宋**','13800000000',NULL,NULL,'1','1948-09-01','2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin');
insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3500','林**','13800000000',NULL,NULL,'1',NULL,'2019-03-28 14:53:44','admin','2019-05-31 11:21:47','admin');
insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3501','黄**','13800000000','test',NULL,'1','1975-05-18','2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin');
insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3504','邢**','13800000000',NULL,NULL,'1','1971-04-01','2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin');
insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3505','曾**','13800000000','test',NULL,'1','1974-08-29','2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin');
insert into `test_user` (`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `sys_create_time`, `sys_create_user`, `sys_update_time`, `sys_update_user`) values('3506','屈**','13800000000',NULL,NULL,'1','1975-05-18','2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin');
