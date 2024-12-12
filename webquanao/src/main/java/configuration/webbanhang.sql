create database webbanhang;
use webbanhang;
create table account(
                        uID int primary key auto_increment,
                        user varchar(50) not null ,
                        pass varchar(255) not null,
                        role varchar(10) not null

);
create table categories(
                           cid int primary key  auto_increment ,
                           name varchar(100) not null
);

create table product(
                        id int auto_increment primary key ,
                        name varchar(100) not null ,
                        description text not null ,
                        price decimal(10,2),
                        title text,
                        image varchar(255),
                        idCategory int ,
                        foreign key (idCategory) REFERENCES  categories(cid)
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
INSERT INTO categories (cid, name) VALUES
                                                    (1, 'Men'),
                                                    (2, 'Women'),
                                                    (3, 'Kids'),
                                                    (4, 'Shoes'),
                                                    (5, 'Accessories');


INSERT INTO product (id, name, description, price, title, image, idCategory) VALUES
                                                                                 (1, 'T-Shirt', 'Comfortable cotton T-shirt', 19.99, 'Best for casual wear', 'images/tshirt.jpg', 1),
                                                                                 (2, 'Dress', 'Elegant evening dress', 49.99, 'Perfect for parties', 'images/dress.jpg', 2),
                                                                                 (3, 'Toy Car', 'Durable and colorful toy car', 9.99, 'Great gift for kids', 'images/toycar.jpg', 3),
                                                                                 (4, 'Running Shoes', 'Lightweight running shoes', 79.99, 'Ideal for athletes', 'images/runningshoes.jpg', 4),
                                                                                 (5, 'Sunglasses', 'Stylish sunglasses with UV protection', 15.99, 'Cool look for sunny days', 'images/sunglasses.jpg', 5);

select *from product
