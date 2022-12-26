SET foreign_key_checks = 0;

DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (
    `employee_id`	int	        NOT NULL,
    `employee_name`	CHAR(20)    NULL,
    PRIMARY KEY (employee_id)
);

DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department` (
    `department_id`	    CHAR(20)	NOT NULL,
    `department_name`	CHAR(30)	NULL,
    PRIMARY KEY (department_id)
);

DROP TABLE IF EXISTS `Belonging`;
CREATE TABLE `Belonging` (
    `belonging_id`	int	        NOT NULL AUTO_INCREMENT,
    `employee_id`	int	        NOT NULL,
    `department_id`	CHAR(20)	NOT NULL,
    PRIMARY KEY (belonging_id),
    FOREIGN KEY (employee_id) REFERENCES Employee (employee_id),
    FOREIGN KEY (department_id) REFERENCES Department (department_id)
);

SET foreign_key_checks = 1;

INSERT Into Employee VALUES (20202201, "김이름");
INSERT Into Employee VALUES (20202202, "김이름");

INSERT INTO Department VALUES ("L1001", "백엔드1팀");
INSERT INTO Department VALUES ("L1002", "백엔드2팀");
INSERT INTO Department VALUES ("L1003", "백엔드3팀");
INSERT INTO Department VALUES ("L1004", "백엔드4팀");
INSERT INTO Department VALUES ("L1005", "백엔드5팀");

INSERT INTO Belonging(employee_id, department_id) VALUES (20202201, "L1001");
INSERT INTO Belonging(employee_id, department_id) VALUES (20202202, "L1002");
INSERT INTO Belonging(employee_id, department_id) VALUES (20202201, "L1003");
INSERT INTO Belonging(employee_id, department_id) VALUES (20202201, "L1004");
INSERT INTO Belonging(employee_id, department_id) VALUES (20202202, "L1005");
INSERT INTO Belonging(employee_id, department_id) VALUES (20202202, "L1001");

