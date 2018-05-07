
--2.1 Select all records from the Employee table
select * from employee;
/
--2.1 Select all records from the Employee table where last name is King
select * from employee where lastname = 'King';
/
--2.1 Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL
select * from employee where firstname = 'Andrew' and reportsto is null;
/
--2.2 Select all albums in Album table and sort result set in descending order by title
select * from album order by title desc;
/
--2.2 Select first name from Customer and sort result set in ascending order by city
Select firstname from customer order by city;
/
--2.3 Insert two records into Genre table
Insert All
into genre
values (100,'Country')
into genre
values (101, 'Boy Band')
select * from dual;
/
--2.3 Insert two new records into the Employee table
Insert All
into employee (employeeid, lastname, firstname)
values (102,'Mansfield', 'Dave')
into employee (employeeid, lastname, firstname)
values (103, 'Rogers', 'John')
select * from dual;
/
--2.3 Insert two new records into Customer table
Insert All
into customer(customerid, firstname, lastname, email)
values (100, 'Jerry-Beans','McGee', 'jbm@hotmail.com' )
into customer(customerid, firstname, lastname, email)
values (101, 'Hallabit','Carpson', 'Fishermanundertheocean123@gmail.com' )
select * from dual;
/
--2.4 Update Aaron Mitchell in Customer table to Robert Walter
Update customer set firstname = 'Robert', lastname = 'Walter'
where firstname = 'Aaron' and lastname = 'Mitchell';
/
--2.4 Update name of artis in the Artist table "Creedence Clearwater Revival" to "CCR"
update artist set name = 'CCR'
where name = 'Creedence Clearwater Revival';
/
--2.5 Select all invoices with a billing address like "T%"
select * from invoice
where billingaddress like 'T%';
/
--2.6 Select all invoices that have a total between 15 and 50
select * from invoice
where total between 15 and 50;
/
--2.6 Select all employees hired between 1st of June 2003 and 1st of March 2004
select * from employee
where hiredate between to_date('2003-06-01', 'yyyy-mm-dd') and to_date('2004-03-01', 'yyyy-mm-dd');
/
--2.7 delete a record in Customer table where the name is Robert Walter
delete from invoiceline
where invoiceid in (select invoiceid from invoice 
where customerid in (select customerid from customer where firstname = 'Robert' and lastname = 'Walter'));
delete from invoice
where customerid in (select customerid from customer where firstname = 'Robert' and lastname = 'Walter')  ;
delete from customer
where firstname = 'Robert' and lastname = 'Walter';
/
--3.1 Create a function that returns the current time
create or replace function currentTime
return timestamp is timeIsNow timestamp;
begin
    timeIsNow := current_timestamp();
    return timeIsNow;
end;
/
--3.1 create a function that returns the length of name in MEDIATYPE table
create or replace function lengthMediaType
return integer is nameLength integer;
begin
    select max(length(name)) into nameLength
    from mediatype;
    return nameLength;
end;
/
--3.2 Create a function that returns the average total of all invoices 
create or replace function avgTotalInvoice
return number is averageTotal number(10,2);
begin
    select avg(total) into averageTotal
    from invoice;
    return averageTotal;
end;
/
--3.2 Create a function that returns the most expensive track
create or replace function mostExpensiveTrack
return number is mostExpensive number(10,2);
begin
    select max(unitprice) into mostExpensive
    from track;
    return mostExpensive;
end;
/
--3.3 Create a function that returns the average price of invoiceline items in the invoiceline table
create or replace function avgPrice
return number is averagePrice number(11,2);
begin
    select avg(unitprice) into averagePrice
    from invoiceline;
    return averagePrice;
end;
/
--3.4 Create a function that returns all employees who are born after 1968
declare
    type employeeRow is record (
        employeeID employee.employeeid%type := 0,
        firstName employee.firstname%type := 'sam',
        lastName employee.lastname%type := 'game');
    type employeeTable is table of employeeRow;
    beforeTable employeeTable;
    cursor employeeIterator IS
        select employeeid, firstname, lastname bulk collect into beforeTable
        from employee
        where birthdate > to_date('31/12/1968 00:00:00', 'mm-dd-yyyy hh24:mi:ss');
function before1969 return employeeTable is
begin
    open employeeIterator;
    for i in 1..12 loop
        fetch employeeIterator into beforeTable(i);
    end loop;
    close employeeIterator;
    return beforeTable;
end;
begin
    null;
end;
/
--4.1 Create a stored procedure that selects the first and last names of all the employees
create or replace type employee_record as object(
    firstname varchar2(20),
    lastname varchar2(20));
/
create or replace type employee_table as table of employee_record;
/

create or replace procedure sp_names_employee(my_employees out employee_table)
is
    i number := 1;
    fname varchar2(20);
    lname varchar2(20);
    my_employee_record employee_record;
begin
    my_employees := employee_table();
    for employee in (select firstname, lastname from employee) loop
        my_employee_record := employee_record(employee.firstname, employee.lastname);
        my_employees.extend();
        my_employees(my_employees.count) := my_employee_record;
    end loop;
end;

create or replace procedure get_employee_name (employee_names out sys_refcursor)
as
begin
    open employee_names for
        select concat(concat(firstname, ' '),lastname) as employee_name from employee;
end;

declare
    employee_cursor sys_refcursor;
    employee_name varchar2(20);
begin
    get_employee_name(employee_cursor);
    loop
        fetch employee_cursor into employee_name;
        dbms_output.put_line(employee_name);
        exit when employee_cursor%notfound;
    end loop;
end;
/
create or replace procedure find_employee_names
is
    cursor employee_cursor is
        select firstname, lastname
        from employee;
    fname varchar2(20);
    lname varchar2(20);
begin
    open employee_cursor;
    loop
        fetch employee_cursor into fname, lname;
        dbms_output.put_line(fname || ' ' || lname);
        exit when employee_cursor%notfound;
    end loop;
end;
/
begin
    find_employee_names;
end;
/
--4.2 Create a stored procedure that updates the personal information of an employee
create or replace procedure sp_update_manager(id in number)
is
begin
    update employee set reportsto = 1 where employeeid = id;
end;
/
--4.2 Create a stored procedure that returns the managers of an employee
create or replace procedure sp_find_managers(id in number, manager out varchar2)
is
begin
    select b.firstname || ' ' || b.lastname into manager from employee a join employee b on a.reportsto = b.employeeid where a.employeeid = id;
end;

declare
    manager varchar2(20);
begin
    sp_find_managers(3, manager);
    dbms_output.put_line(manager);
end;
/
--4.3 Create a stored procedure that returns the name and company of a customer
create or replace procedure sp_name_company(id in number, customer_name out varchar2, customer_company out varchar2)
is
begin
    select concat(concat(firstname, ' '), lastname), company into customer_name, customer_company from customer where customerid = id;
end;

declare
    customer_name varchar2(20);
    customer_company varchar2(20);
begin
    sp_name_company(19, customer_name, customer_company);
    dbms_output.put_line(customer_name || ' ' || customer_company);
end;
--5.0 Create a transaction that given a invoiceId will delete that invoice
--/
create or replace procedure sp_delete_invoice(invoice_id in number)
is
    delete_cascade exception;
    pragma exception_init(delete_cascade, -02292);
begin
    savepoint s1;
    begin
    delete from invoice where invoiceid = invoice_id;
    exception when delete_cascade then
            rollback to s1;
            delete from invoiceline where invoiceid = invoice_id;
            delete from invoice where invoiceid = invoice_id;
    end;
    commit;
end;
/
declare
    invoice_id number;
begin
    invoice_id := 420;
    sp_delete_invoice(invoice_id);
end;
/
--6.1 Create an after insert trigger on the employee table fired after a new record is inserted into the table
create or replace trigger tr_insert_employee
after insert on customer
begin
    RAISE_APPLICATION_ERROR (-20000, 'Bad insert');
end;
/
--6.1 Create an after update trigger on the album table that fires after a row is inserted into the table
create or replace trigger tr_update_album
after update on album
begin
    RAISE_APPLICATION_ERROR (-20001, 'Bad update');
end;
/
--6.1 Create an after delete trigger on the customer table that fires after a row is deleted from the table.
create or replace trigger tr_delete_customer
after delete on customer
begin
    RAISE_APPLICATION_ERROR (-20002, 'Bad delete');
end;
/
--7.1 Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select concat(concat(firstname, ' '), lastname) as employeeName, invoiceid
from customer inner join invoice on customer.customerid = invoice.customerid;
/
--7.2 Create an outer join that joins the customer and invoice table,
--        specifying the CustomerId, firstname, lastname, invoiceId, and total
select customer.customerid, firstname, lastname, invoiceid, total 
from customer full outer join invoice on customer.customerid = invoice.customerid
order by customer.customerid;
/
--7.3 Create a right join that joins album and artist specifying artist name and title
select title, name
from album right join artist on album.artistid = artist.artistid;
--7.4 Create a cross join that joins album and artist and sorts by artist name in ascending order
select *
from album cross join artist
order by name asc;
--7.5 Perform a self-join on the employee table, joining on the reportsto column
select *
from employee a join employee b on a.reportsto = b.employeeid;