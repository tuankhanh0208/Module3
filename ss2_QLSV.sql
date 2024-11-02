create database QLSV;
use QLSV;

create table Class (
	ClassID int not null auto_increment primary key,
    ClassName varchar(60) not null,
    StartDate datetime not null,
    Status Bit 
);

create table Student(
	StudentID int not null auto_increment primary key,
    StudentName varchar(30) not null,
    Address varchar(50),
    Phone varchar(20),
    Status Bit,
    ClassID int not null,
    foreign key (ClassID) references Class(ClassID)
);

create table `Subject`(
	SubID int not null primary key auto_increment,
    SubName varchar(30) not null,
    Credit tinyint not null default 1 check(Credit >=1),
    Status Bit default 1
);

create table Mark(
	MarkID int not null primary key auto_increment,
    SubID int not null,
    StudentID int not null,
    Mark float default 0 check( Mark between 0 and 100),
    ExamTimes tinyint default 1,
    unique(SubID,StudentID),
    foreign key (SubID) references `Subject`(SubID),
    foreign key (StudentID) references Student(StudentID)
);

insert into Class
value(1,"A1","2008-12-20",1), 
	(2,"A1","2008-12-20",1), 
	(3,"A1","2008-12-20",1);
    
insert into Student (StudentName, Address, Phone, Status, ClassId)
values('Hung', 'Ha Noi', '0912113113', 1, 1),
('Hanh', 'Ha Noi', '0912113113', 1, 1),
('Hong', 'Ha Noi', '0912113113', 1, 1),
('Khanh', 'Ha Noi', '0912113113', 1, 1),
('Tung', 'Ha Noi', '0912113113', 1, 1),
    ('Manh', 'HCM', '0123123123', 0, 2);
    
insert into Subject
values (1, 'CF', 5, 1),
 (2, 'C', 6, 1),
 (3, 'HDJ', 5, 1),
 (4, 'RDBMS', 10, 1);
 
insert into Mark (SubId, StudentId, Mark, ExamTimes)
VALUES (1, 1, 8, 1),
 (1, 2, 10, 2),
 (2, 1, 12, 1);
 
 select * from Student;
 
 select* from Student 
 where Status = true;
 
 select * from `Subject`
 where Credit <10;
 
 select S.StudentID,S.StudentName,C.ClassName
 from Student S join Class C on S.ClassID=C.ClassID
 where C.ClassName ="A1";
 
 select S.StudentID,S.StudentName,Sub.SubName,M.Mark
 from Student S join Mark M on S.StudentID = M.StudentID join `Subject` Sub on M.SubID = Sub.SubID
 where Sub.SubName ="CF";
 
 -- Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’-- 
 select * 
 from Student
 where StudentName like "h%";
 
--  Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12.
 select *
 from Class
 where  month(StartDate) =12;
 
 -- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.
 select * from `Subject`
 where Credit > 2 and Credit < 6;
 
 -- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2. 
 SET SQL_SAFE_UPDATES = 0;
 update Student
 set ClassId = 2
 where StudentName = "Hung";
 
-- Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.
select Student.StudentName,`Subject`.SubName,Mark.Mark
from Student 
join Mark on Student.StudentId = Mark.StudentId
join `Subject` on Mark.SubId = `Subject`.SubId
order by Mark desc , Student.StudentName asc;