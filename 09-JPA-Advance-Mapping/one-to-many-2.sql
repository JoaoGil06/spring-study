DROP SCHEMA IF EXISTS `hb-03-one-to-many`;

CREATE SCHEMA `hb-03-one-to-many`;

use `hb-03-one-to-many`;

DROP TABLE IF EXISTS `instructor_detail`;

CREATE TABLE `instructor_detail` (
	`id` int NOT NULL auto_increment,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby` varchar(45) DEFAULT NULL,
    primary key (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=latin1;

DROP TABLE IF EXISTS `instructor`;

CREATE TABLE `instructor` (
	`id` int NOT NULL auto_increment,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    `instructor_detail_id` int DEFAULT NULL,
    PRIMARY KEY(`id`),
    KEY `FK_DETAIL_idx` (`instructor_detail_id`),
    CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=latin1;
    

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
	`id` int NOT NULL auto_increment,
    `title` varchar(128) DEFAULT NULL,
    `instructor_id` int DEFAULT NULL,
   
    PRIMARY KEY(`id`),
    UNIQUE KEY `TITLE_UNIQUE` (`title`),
    KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
    CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=latin1;
    
DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
	`id` int NOT NULL auto_increment,
    `comment` varchar(256) DEFAULT NULL,
    `course_id` int DEFAULT NULL,
   
    PRIMARY KEY(`id`),
    KEY `FK_COURSE_ID_idx` (`course_id`),
    CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=latin1;
    
    SET FOREIGN_KEY_CHECKS=1;