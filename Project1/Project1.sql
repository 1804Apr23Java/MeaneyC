drop table resets;
drop table reimbursements;
drop table manager;
drop table employee;
drop table login;
drop sequence sq_resets;
drop sequence sq_login;
drop sequence sq_employee;
drop sequence sq_manager;
drop sequence sq_reimbursements;

create table Login (
login_id number(7,2),
username varchar2(20)UNIQUE,
password varchar2(20),
isManager number(1),
constraint pk_login primary key(login_id),
constraint chk_isManager check(isManager between 0 and 1));
/
create table EMPLOYEE(
employee_id number(7,2),
employee_first varchar2(20),
employee_last varchar2(20),
employee_email varchar2(60),
login_id number(7,2),
constraint fk_login foreign key (login_id) references login(login_id),
constraint pk_employee primary key(employee_id));
/

create table MANAGER(
manager_id number(7,2),
manager_first varchar2(20),
manager_last varchar2(20),
manager_email varchar2(60),
login_id number(7,2),
constraint fk_manager_login foreign key (login_id) references login(login_id),
constraint pk_manager primary key(manager_id));
/
create table REIMBURSEMENTS(
reimbursement_id number primary key,
employee_id number(7,2),
amount number(7,2),
image_location varchar2(60),
state number(1),
resolving_manager number(7,2),
constraint fk_manager foreign key(resolving_manager) references manager(manager_id),
constraint fk_employee_id foreign key(employee_id) references employee(employee_id),
constraint chk_state check(state between 0 and 2)); -- 0 is pending, 1 is approved, 2 is denied
/
create table RESETS(
reset_id number primary key,
username varchar2(20) UNIQUE,
constraint fk_username foreign key(username) references login(username));
/
create sequence sq_login increment by 1 start with 0 minvalue 0;
/
create sequence sq_employee increment by 1 start with 1 minvalue 1;
/
create sequence sq_manager increment by 1 start with 0 minvalue 0;
/
create sequence sq_reimbursements increment by 1 start with 1 minvalue 1;
/
create sequence sq_resets increment by 187 start with 11195 minvalue 1;
/
create or replace trigger tr_insert_login
before insert on login
for each row
begin
    select sq_login.nextval into :new.login_id from dual;
end;
/
create or replace trigger tr_insert_employee
before insert on employee
for each row
begin
    select sq_employee.nextval into :new.employee_id from dual;
end;
/
create or replace trigger tr_insert_manager
before insert on manager
for each row
begin
    select sq_manager.nextval into :new.manager_id from dual;
end;
/
create or replace trigger tr_insert_reimbursement
before insert on reimbursements
for each row
begin
    select sq_reimbursements.nextval into :new.reimbursement_id from dual;
end;
/
create or replace trigger tr_insert_resets
before insert on resets
for each row
begin
    select sq_resets.nextval into :new.reset_id from dual;
end;
/
insert all
into login(username, password, ismanager)
values('dummy', 'zxcvfdsaqwer', 1)
into login(username, password, ismanager)
values('whelton', 'password1', 1)
into login(username, password, ismanager)
values('dbrewer', 'password2', 1)
into login(username, password, ismanager)
values('jmubang', 'password3', 1)
into login(username, password, ismanager)
values('cmeaney', 'password4', 0)
into login(username, password, ismanager)
values('awang', 'password5', 0)
into login(username, password, ismanager)
values('dseo', 'password6', 0)
into login(username, password, ismanager)
values('kmagno', 'password7', 0)
into login(username, password, ismanager)
values('rtruong', 'password8', 0)
select * from dual;
/
insert all
into employee(employee_first, employee_last, employee_email, login_id)
values('Collin', 'Meaney', 'collinmeaney375@gmail.com', 4)
into employee(employee_first, employee_last, employee_email, login_id)
values('Angela', 'Wang', 'anqi.wang2014@gmail.com', 5)
into employee(employee_first, employee_last, employee_email, login_id)
values('Danny', 'Seo', 'seo.danny@gmail.com', 6)
into employee(employee_first, employee_last, employee_email, login_id)
values('Kevin', 'Magno', 'kmagno94@gmail.com', 7)
into employee(employee_first, employee_last, employee_email, login_id)
values('Robert', 'Truong', 'rrobtruong@gmail.com', 8)
select * from dual;
/
insert all
into manager(manager_first, manager_last, manager_email, login_id)
values('Dummy', 'Manager', 'dummymanager@gmail.com', 0)
into manager(manager_first, manager_last, manager_email, login_id)
values('William', 'Helton', 'williamlhelton@gmail.com', 1)
into manager(manager_first, manager_last, manager_email, login_id)
values('Dave', 'Brewer', 'dave.guido.brewer@gmail.com', 2)
into manager(manager_first, manager_last, manager_email, login_id)
values('John', 'Mubang', 'jmubang@gmail.com', 3)
select * from dual;
/
commit;