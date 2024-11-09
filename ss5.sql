create database demoo;
use demoo;

create table products(
	id int primary key not null auto_increment,
    productCode varchar(50) not null,
    productName varchar(50) not null,
    productPrice decimal(10,2) not null,
    productAmount varchar(50) not null,
    productDescription varchar(225) not null,
    productStatus varchar(50) not null
);
insert into products (
    productCode, 
    productName, 
    productPrice, 
    productAmount, 
    productDescription, 
    productStatus
) 
values
    ('P001', 'Apple', 1.50, '100 kg', 'Fresh red apples', 'Available'),
    ('P002', 'Banana', 0.80, '150 kg', 'Organic bananas', 'Available'),
    ('P003', 'Orange', 1.20, '200 kg', 'Sweet oranges', 'Available'),
    ('P004', 'Mango', 2.00, '50 kg', 'Tropical mangoes', 'Out of stock'),
    ('P005', 'Grapes', 1.80, '120 kg', 'Seedless grapes', 'Available'),
    ('P006', 'Strawberry', 2.50, '75 kg', 'Fresh strawberries', 'Available'),
    ('P007', 'Pineapple', 3.00, '60 kg', 'Sweet pineapples', 'Available'),
    ('P008', 'Watermelon', 1.00, '90 kg', 'Juicy watermelons', 'Out of stock'),
    ('P009', 'Kiwi', 2.20, '30 kg', 'Exotic kiwis', 'Available'),
    ('P010', 'Blueberry', 3.50, '40 kg', 'Fresh blueberries', 'Available');


select * from products;

create unique index id_unique_index_products on products(productCode);
create index id_productName_productPrice on products(productName,productPrice);

select * from products where productName = 'Apple' and productPrice = 1.50;
explain select * from products where productName = 'Apple' and productPrice = 1.50;

create view product_view as
select  productCode, productName, productPrice, productStatus
from products;

create or replace view product_view as
select productCode, productName, productPrice, productStatus
from products;
drop view if exists product_view;

DELIMITER // 
create procedure getAllProducts()
begin
	select * from products;
end //
DELIMITER ;
call getAllProducts();

DELIMITER //
create procedure addProducts( 
	in p_productCode varchar(50) ,
	in p_productName varchar(50) ,
    in p_productPrice decimal(10,2) ,
    in p_productAmount varchar(50) ,
    in p_productDescription varchar(225) ,
    in p_productStatus varchar(50))
    begin 
    insert into products (productCode, productName, productPrice, productAmount, productDescription, productStatus)
    values (p_productCode,p_productName,p_productPrice,p_productAmount,p_productDescription,p_productStatus);
    end // 
DELIMITER ;
CALL addProducts('P011', 'Pear', 1.25, '80 kg', 'Fresh green pears', 'Available');

delimiter //
create procedure updateProducts(
		in p_id int ,
		in p_productCode varchar(50) ,
		in p_productName varchar(50) ,
		in p_productPrice decimal(10,2) ,
		in p_productAmount varchar(50) ,
		in p_productDescription varchar(225) ,
		in p_productStatus varchar(50))
    begin
    update products
    set productCode = p_productCode,
		productName = p_productName,
		productPrice = p_productPrice,
		productAmount= p_productAmount,
		productDescription = p_productDescription  ,
		productStatus= p_productStatus
    where id = p_id;
    end //
    delimiter // 
 
 CALL updateProducts(1, 'P001', 'Apple', 1.75, '120 kg', 'Updated fresh red apples', 'Available');
  
delimiter // 
create procedure deleteProducts(in p_id int )
begin
	delete from products where id = p_id;
    end //
    delimiter ;
call deleteProducts(1);
