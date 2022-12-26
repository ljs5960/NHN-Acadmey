DROP TABLE IF EXISTS `Employee`;

CREATE TABLE `Employee` (
	`employee_id`	int	NOT NULL,
	`employee_name`	CHAR(20)	NULL
);

DROP TABLE IF EXISTS `Department`;

CREATE TABLE `Department` (
	`department_id`	CHAR(20)	NOT NULL,
	`department_name`	CHAR(30)	NULL
);

DROP TABLE IF EXISTS `Belonging`;

CREATE TABLE `Belonging` (
	`belonging_id`	int	NOT NULL AUTO_INCREMENT primary key,
	`employee_id`	int	NOT NULL,
	`department_id`	CHAR(20)	NOT NULL
);

ALTER TABLE `Employee` ADD CONSTRAINT `PK_EMPLOYEE` PRIMARY KEY (
	`employee_id`
);

ALTER TABLE `Department` ADD CONSTRAINT `PK_DEPARTMENT` PRIMARY KEY (
	`department_id`
);


ALTER TABLE `Belonging` ADD CONSTRAINT `FK_Employee_TO_Belonging_1` FOREIGN KEY (
	`employee_id`
)
REFERENCES `Employee` (
	`employee_id`
);

ALTER TABLE `Belonging` ADD CONSTRAINT `FK_Department_TO_Belonging_1` FOREIGN KEY (
	`department_id`
)
REFERENCES `Department` (
	`department_id`
);

