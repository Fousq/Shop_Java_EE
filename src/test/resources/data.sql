DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS user_account;

CREATE TABLE IF NOT EXISTS category(
    name varchar(100) primary key not null
);

CREATE TABLE IF NOT EXISTS product(
    id int primary key auto_increment,
    name varchar(100),
    category_name varchar(100),
    FOREIGN KEY (category_name) REFERENCES category(name)
);

CREATE TABLE IF NOT EXISTS user_account(
    id int primary key auto_increment,
    username varchar(100),
    password varchar(100)
);

INSERT INTO category(name) VALUES ('category1');
INSERT INTO category(name) VALUES ('category2');

INSERT INTO product(name, category_name) VALUES ('product1', 'category1');
INSERT INTO product(name) VALUES ('product2');

INSERT INTO user_account(username, password) VALUES ('user', 'pass');