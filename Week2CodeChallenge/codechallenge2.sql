drop table employee;
/
drop table department;
/
create table department(
department_id integer,
department_name varchar2(20),
constraint pk_department primary key(department_id));
/
create table employee(
employee_id integer,
emp_firstname varchar2(20),
emp_lastname varchar2(20),
department_id integer,
salary number(8,2),
emp_email varchar2(40),
constraint pk_employee primary key(employee_id),
constraint fk_employee foreign key(department_id) references department(department_id));
/
drop sequence sq_employee;
/
drop sequence sq_department;
/
create sequence sq_employee increment by 1;
/
create sequence sq_department increment by 1;
/
create or replace trigger tr_insert_employee
before insert on employee
for each row
begin
    select sq_employee.nextval into :new.employee_id from dual;
end;
/
create or replace trigger tr_insert_department
before insert on department
for each row
begin
    select sq_department.nextval into :new.department_id from dual;
end;
/
insert all
into department(department_name)
values('Electronics')
into department(department_name)
values('Sporting goods')
into department(department_name)
values('Home')
select * from dual;
/
insert into employee(emp_firstname, emp_lastname, department_id, salary, emp_email)
values('Dave', 'Michaels', 2, 50000, 'dave.michaels@yahoo.com');
/
insert into employee(emp_firstname, emp_lastname, department_id, salary, emp_email)
values('Sally', 'Fields', 1, 40000, 'sally.fields@yahoo.com');
/
insert into employee(emp_firstname, emp_lastname, department_id, salary, emp_email)
values('Joan', 'Wilder', 3, 80000, 'joan.wilder@yahoo.com');
/
insert into employee(emp_firstname, emp_lastname, department_id, salary, emp_email)
values('Ben', 'Stevenson', 2, 10000, 'ben.stevenson@yahoo.com');
/
insert into employee(emp_firstname, emp_lastname, department_id, salary, emp_email)
values('Doc', 'Holiday', 1, 190000, 'doc.holiday@yahoo.com');
/
insert into employee(emp_firstname, emp_lastname, department_id, salary, emp_email)
values('Susan', 'Storm', 3, 18000, 'susan.storm@yahoo.com');
/
create procedure sp_give_raise(id in integer, avg_salary out number, valid_department out boolean)
is
begin
    update employee
        set salary = salary * 1.1
        where department_id = id;
    select avg(salary)
        from employee
        where department_id = id;
end;

declare
    avg_salary number(8,2);
    valid_department boolean := false;
begin
    sp_give_raise(1,avg_salary,valid_department);
end;

select department_name, avg(salary) from department inner join employee on department.department_id = employee.department_id;