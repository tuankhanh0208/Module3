create database QuanLyBanHang;
use QuanLyBanHang;

create table Customer(
	cID int primary key,
    cName varchar(50),
    cAge int
);

create table `Order` (
	oID int primary key,
    cID int,
    oDate Date,
    oTotalPrice Decimal(10,2),
    Foreign key (cID) References Customer(cID)
);

create table Product(
	pID int primary key,
    pName varchar(50),
    pPrice decimal(10,2)
);

create table OrderDetail(
	oID int primary key ,
    pID int,
    odQTY int,
	FOREIGN KEY (oID) REFERENCES `Order`(oID),
    FOREIGN KEY (pID) REFERENCES Product(pID)
);



  