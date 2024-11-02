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

CREATE TABLE OrderDetail (
    oID int,
    pID int,
    odQTY int,
    PRIMARY KEY (oID, pID),
    FOREIGN KEY (oID) REFERENCES `Order`(oID),
    FOREIGN KEY (pID) REFERENCES Product(pID)
);

insert into Customer (cID,cName,cAge)
values(1,"Minh Quan",10),
	(2,"Ngoc Oanh",20),
    (3,"Hong Ha",50);
    
insert into `Order` (oID,cID,oDate,oTotalPrice)
values(1,1,"2006-3-21",null),
	  (2,2,"2006-3-23",null),
	  (3,1,"2006-3-16",null);

insert into Product (pID,pName,pPrice)
values(1,"May Giat",2),
	  (2,"Tu Lanh",1),
	  (3,"Dieu Hoa",7),
	  (4,"Quat",1),
	  (5,"Bep Dien",2);
		
insert into OrderDetail(oID,pID,odQTY)
values(1,1,3),
	(1,3,7),
	(1,4,2),
	(2,1,1),
	(3,1,8),
	(2,5,4),
	(2,3,3);

-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
select oID,oDate,oTotalPrice
from `Order`;

-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select c.cID,c.cName,o.oID,p.pID,p.pName,od.odQTY
from Customer c
join `Order` o on c.cID=o.cID
join OrderDetail od on o.oID=od.oID
join Product p on od.pID = p.pID;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select c.cID,c.cName
from Customer c
left join  `Order` o on c.cID=o.cID
left join  OrderDetail od on o.oID=od.oID
where od.odQTY=null;

-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn.Giá bán của từng loại được tính = odQTY*pPrice)
select o.oID as "MaHoaDon",o.oDate as "NgayBan",sum(od.odQTY*p.pPrice)as "GiaTien"
from `Order` o
join OrderDetail od on o.oID=od.oID
join Product p on od.pID = p.pID
group by o.oID ,o.oDate;

  
