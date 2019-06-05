
/* cdc(change data capture) 变更数据捕获临时表 */
CREATE TABLE IF NOT EXISTS `cdc_temp` (
  `id` INT(11) NOT NULL DEFAULT '0',
  `last_update_time` DATETIME NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '上次更新的数据最新时间',
  `current_update_time` DATETIME NOT NULL DEFAULT now() COMMENT '当前更新时间',
  `roll_back_num` VARCHAR(20) NOT NULL DEFAULT '0',
  `ahead_step_num` VARCHAR(20) NOT NULL DEFAULT '0',
  `job_tag` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '作业标识',
  `comment` VARCHAR(50) DEFAULT NULL COMMENT '备注',
  `status` VARCHAR(50) DEFAULT NULL COMMENT '状态,INIT:初始状态，未进行同步,FAILED:同步失败,COMPLETED:同步完成',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='变更数据捕获(cdc)临时表';

INSERT IGNORE INTO cdc_temp VALUE(1,'2000-01-01 00:00:00',NOW(),'0','0','test_user','用户信息','INIT');

