create table employee (
	id int primary key,
    name varchar(50),
    lastname varchar(50),
    age int,
    salary decimal(19,2)
);


create table address (
	id int primary key,
    address varchar(100),
    district varchar(50),
    state varchar(50),
    pincode int,
    addresstype varchar(100),
    empid int,
    foreign key(empid) references employee(id) 
    
);