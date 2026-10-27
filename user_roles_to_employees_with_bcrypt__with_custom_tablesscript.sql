USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

CREATE TABLE `members`(
	`user_id` varchar(50) not null,
    `pw` char(68) not null,
    `active` tinyint NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `members` 
VALUES
('pedro', '{bcrypt}$2a$12$.ri7VH7dcL97ojNMZMZ8yeiR/ErPmVtBnv6UFcYNsRWqLsngIabZe',1),
('rita', '{bcrypt}$2a$12$.ri7VH7dcL97ojNMZMZ8yeiR/ErPmVtBnv6UFcYNsRWqLsngIabZe',1),
('joao', '{bcrypt}$2a$12$.ri7VH7dcL97ojNMZMZ8yeiR/ErPmVtBnv6UFcYNsRWqLsngIabZe',1);

CREATE TABLE `roles` (
`user_id` varchar(50) not null,
`role` varchar(50) not null,
UNIQUE KEY `authorities5_idx_1` (`user_id`, `role`),
CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `roles` 
VALUES
('pedro', 'ROLE_EMPLOYEE'),
('rita', 'ROLE_EMPLOYEE'),
('joao', 'ROLE_EMPLOYEE'),
('rita', 'ROLE_MANAGER'),
('joao', 'ROLE_MANAGER'),
('joao', 'ROLE_ADMIN');
