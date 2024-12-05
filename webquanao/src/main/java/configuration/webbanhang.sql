create table account(
                        uID int primary key auto_increment,
                        user varchar(50) not null ,
                        pass varchar(255) not null

);
create table categories(
                           id int primary key ,
                           name varchar(100) not null ,
                           description text not null
);
create table product(
                        id int auto_increment primary key ,
                        name varchar(100) not null ,
                        description text not null ,
                        price decimal(10,2),
                        title text,
                        image varchar(255),
                        idCategory int ,
                        foreign key (idCategory) REFERENCES  categories(id)
);
CREATE TABLE orders (
                        orderID INT AUTO_INCREMENT PRIMARY KEY,
                        uID INT NOT NULL,
                        orderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
                        total DECIMAL(10,2) NOT NULL,
                        status VARCHAR(50) DEFAULT 'Pending',
                        FOREIGN KEY (uID) REFERENCES account(uID)
);
CREATE TABLE order_details (
                               orderDetailID INT AUTO_INCREMENT PRIMARY KEY,
                               orderID INT NOT NULL,
                               productID INT NOT NULL,
                               quantity INT NOT NULL,
                               price DECIMAL(10,2) NOT NULL,
                               FOREIGN KEY (orderID) REFERENCES orders(orderID),
                               FOREIGN KEY (productID) REFERENCES product(id)
);
select *from product