create table employee_table(employee_id int not null primary key auto_increment,employee_name varchar(50),department_name varchar(50),
email_id varchar(50), address varchar(300),contact_number bigint,alternative_contact_number bigint);


select * from employee_table;


-- ------------------------------------------------------------------------------------

select * from complaint_raise  order by status desc; -- //10 to 9

select * from complaint_raise order by status asc; -- //1 to 10

select * from complaint_raise order by status desc limit 3; 

select * from complaint_raise;




-- SELECT * FROM Employees
-- ORDER BY EmployeeID
-- OFFSET 10 ROWS FETCH NEXT 5 ROWS ONLY; its not suppport in mysql..it support postre


-- The error you're encountering likely indicates that the SQL dialect you're using doesn't support the OFFSET and FETCH clauses, 
-- which are typically used in databases like SQL Server (since 2012), PostgreSQL, or Oracle (with some variations). 
-- If you're using MySQL or an older version of SQL Server, the approach will be different.

-- SELECT *
-- FROM complaint_raise
-- ORDER BY status DESC
-- LIMIT 1;

SELECT *
FROM Employees
ORDER BY EmployeeID
LIMIT 10, 5;


select * from complaint_raise order by cmplt_id limit 10,2;



-- foreign key 

-- Alter table employee_table ADD COLUMN department_id INT,ADD CONTRAINT fk_department FOREIGN KEY(department_id) 
-- REFERENCES department_table(department_id);

ALTER TABLE employee_table ADD COLUMN department_id INT,ADD CONSTRAINT fk_department FOREIGN KEY (department_id)
REFERENCES department_table(department_id);


alter table employee_table add column(otp long);


-- to get only 1st record (min)  

select * from complaint_raise where complaint_id=(select min(complaint_id)  from  complaint_raise);

-- to get last record (max)
select * from complaint_raise where complaint_id=(select max(complaint_id) from complaint_raise);



SELECT *
FROM EmpInfo
LIMIT (SELECT COUNT(*) / 2 FROM EmpInfo);


SELECT *
FROM EmpInfo
ORDER BY RANDOM()
LIMIT (SELECT COUNT(*) / 2 FROM EmpInfo);



SELECT *
FROM EmpInfo
ORDER BY RANDOM()
LIMIT 10;
