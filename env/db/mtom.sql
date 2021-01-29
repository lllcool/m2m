
CREATE DATABASE `mysql-mtom` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use `mysql-mtom`;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
    `id` varchar(32) COMMENT '主键ID',
    `subject` varchar(10) DEFAULT NULL COMMENT '科目',
    `teacher_name` varchar(10) DEFAULT NULL COMMENT '老师姓名',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='老师';

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
    `id` varchar(32) COMMENT '主键ID',
    `name` varchar(32) NOT NULL DEFAULT '无' COMMENT '姓名',
    `age` int(3) NOT NULL DEFAULT '0' COMMENT '年龄',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生';

DROP TABLE IF EXISTS `stu_ref_teach`;

CREATE TABLE `stu_ref_teach` (
    `id` varchar(32) COMMENT '主键ID',
    `stu_id` varchar(32) DEFAULT NULL COMMENT '主键ID',
    `teacher_id` varchar(32) DEFAULT NULL COMMENT '老师',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='老师学生关系表';

DROP TABLE IF EXISTS `ball`;

CREATE TABLE `ball` (
    `id` varchar(32) COMMENT '主键ID',
    `ball_name` varchar(10) NOT NULL DEFAULT '无' COMMENT '球类名称',
    `price` int(5) DEFAULT NULL COMMENT '价钱',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='球';

DROP TABLE IF EXISTS `teama`;

CREATE TABLE `teama` (
    `id` varchar(32) COMMENT '主键ID',
    `team_name` varchar(20) DEFAULT NULL COMMENT '团队名称',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团队';

DROP TABLE IF EXISTS `team_group`;

CREATE TABLE `team_group` (
    `id` varchar(32) COMMENT '主键ID',
    `count` varchar(5) DEFAULT NULL COMMENT '数量',
    `team_id` varchar(32) DEFAULT NULL COMMENT '团队',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团队组成';



SET FOREIGN_KEY_CHECKS = 1;
