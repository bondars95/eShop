CREATE DATABASE shopdb;
create user 'eclipse'  IDENTIFIED BY 'eclipse_1';
GRANT ALL PRIVILEGES ON * . * TO 'eclipse';

USE shopdb;

CREATE TABLE USERS(
id int NOT NULL AUTO_INCREMENT primary key,
fname varchar(25) NOT NULL,
lname varchar(25) NOT NULL,
email varchar(30) NOT NULL,
birthdate date NOT NULL,
city varchar(30) NOT NULL,
sex varchar(1) NOT NULL,
phone varchar(10) NOT NULL,
role smallint NOT NULL,
password varchar(20) not null
);

CREATE TABLE CATEGORY(
id int NOT NULL AUTO_INCREMENT primary key,
parentId int,
categoryName varchar(30) NOT NULL
);

CREATE TABLE PRODUCT(
id int NOT NULL AUTO_INCREMENT primary key,
categoryId int ,
productName varchar(30) NOT NULL,
description varchar(250) NOT NULL,
country varchar(30) NOT NULL,
price decimal(5,2) NOT NULL, 
rest int NOT NULL,
imgPath varchar(100),
FOREIGN KEY (categoryId) REFERENCES CATEGORY(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

create trigger before_category_delete
	before delete on category
    
	for each row 
	update product
    set product.categoryId = 1
    where product.categoryId = OLD.id;


CREATE TABLE ordersDetails(
id int NOT NULL AUTO_INCREMENT primary key,
userId int,
status smallint,
orderDate date,
FOREIGN KEY (userId) REFERENCES USERS(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);


CREATE TABLE orderItems(
id int NOT NULL AUTO_INCREMENT primary key,
orderId int NOT NULL,
productId int NOT NULL,
lineItem smallint NOT NULL,
qty int NOT NULL,
FOREIGN KEY (productId) REFERENCES PRODUCT(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (orderId) REFERENCES ordersdetails(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
UNIQUE(orderId,lineItem)
);

create table article(
id int(4) NOT NULL AUTO_INCREMENT primary key,
header varchar(80) NOT NULL,
content text,
imgPath varchar(100) default 'default.jpg'      
);

INSERT INTO Category(id,parentId,categoryName) Values
(1,null,'creams'),
(2,1,'creams for hands'),
(3,1,'creams for legs'),
(4,1,'creams for face'),
(5,null,'sponges'),
(6,5,'sponges for body'),
(7,5,'sponges for face'),
(8,null,'others');

INSERT INTO product(id, categoryId,productname,description,country,price,rest,imgPath) 
values 
(1,6,'Luffa','Sponge for body','Israel',120.5,13,'1_prod_1.jpg'),
(2, 7,'Sponge','Artisan sponge','Turkey',56,16,'2_prod_1.jpg'),
(3, 6,'Sponge-Scrab','Best way to keep your skin smoothy','Israel',73.5,12,'3_prod_1.jpg'),
(4, 7,'Sponge Gift Box','Sponge for face','Israel',103.5,12,'4_prod_3.jpg'),
(5, 6,'Natural sponge','Best way to keep your skin smoothy','Israel',123.5,12,'5_prod_1.jpg'),
(7, 6,'Kit for skin care','Best way to keep your skin smoothy','Israel',273.5,12,'7_prod_1.jpg'),
(8, 6,'Sponge-Scrab','Best way to keep your skin smoothy','Israel',73.5,12,'8_prod_1.jpg'),
(15, 6,'Sponge-Scrab','Best way to keep your skin smoothy','Israel',73.5,12,'15_prod_1.jpg'),
(17, 6,'Sponge','Best way to keep your skin smoothy','Israel',73.5,12,'17_prod_1.jpg');

insert into users 
(fname, lname, email, birthdate, city, sex, phone, role, password)
values 
('User1', 'Grigoruk', 'serg07.01.95@mail.ru', '1993-11-11', 'Kiyv', 'M', '099123456', 1, 123456),
('User2', 'Dedov', 'a.dedov@gmail.com', '1984-01-13', 'Kiyv', 'M', '099123456', 1, 123456),
('User3', 'Guk', 'd.guk@gmail.com', '1994-05-03', 'L`viv', 'M', '099123456', 1, 123456),
('Admin', 'Admin', 'admin', '1994-05-03', 'L`viv', 'M', '099123456', 2, 123456);

insert into article 
(id, header, content, imgPath)
values 
(3, '50 % discount', 'Discount due to upcoming holidays', 'article_3.jpg'),
(5, 'Health', 'Luffa is a genus of tropical and subtropical vines in the cucumber (Cucurbitaceae) family.', 'article_5.jpeg'),
(6, 'Health', 'Luffa is a genus of tropical and subtropical vines in the cucumber (Cucurbitaceae) family.', 'article_8.jpg');