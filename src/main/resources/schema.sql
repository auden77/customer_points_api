DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
customer_id INT NOT NULL primary key auto_increment,
first_name varchar(255) not null,
last_name varchar(255) not null
);

DROP TABLE IF EXISTS transaction;

CREATE TABLE transaction (
TRANSACTION_ID INT NOT NULL primary key auto_increment,
CUSTOMER_ID INT not null,
PURCHASE_AMOUNT DECIMAL not null,
TRANSACTION_TIME timestamp default(CURRENT_TIME)
)