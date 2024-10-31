create database student_management;
use student_management;

create table Class(
	id int,
    name varchar(50)
);

create table Teacher(
	id int,
	name varchar(50),
    age int,
    country varchar(255)
);

insert into Class (id ,name)
value(1,"Khanh"),
	 (2,"Khanh đẹp trai"),
	 (3,"Khanh đẹp trai 2");
     
insert into Teacher(id,name,age,country)
value(1,"Khanh",18,"Đà Nẵng"),
	 (2,"Khanh đẹp trai",19,"Đà Nẵng"),
	 (3,"Khanh đẹp trai",19,"Đà Nẵng");