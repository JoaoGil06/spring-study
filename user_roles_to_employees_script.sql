USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`(
	`username` varchar(50) not null,
    `password` varchar(50) not null,
    `enabled` tinyint NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` 
VALUES
('pedro', '{noop}test123',1),
('rita', '{noop}test123',1),
('joao', '{noop}test123',1);

CREATE TABLE `authorities` (
`username` varchar(50) not null,
`authority` varchar(50) not null,
UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users`(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities` 
VALUES
('pedro', 'ROLE_EMPLOYEE'),
('rita', 'ROLE_EMPLOYEE'),
('joao', 'ROLE_EMPLOYEE'),
('rita', 'ROLE_MANAGER'),
('joao', 'ROLE_MANAGER'),
('joao', 'ROLE_ADMIN');
