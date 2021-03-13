CREATE TABLE product (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    CONSTRAINT id UNIQUE (id),
    CONSTRAINT name UNIQUE (name)
);

INSERT INTO product (id,name,price) VALUES (1,'Produto A', 10.99);
INSERT INTO product (id,name,price) VALUES (2,'Produto B', 5.25);
INSERT INTO product (id,name,price) VALUES (3,'Produto C', 100.85);
INSERT INTO product (id,name,price) VALUES (4,'Produto D', 250.00);
INSERT INTO product (id,name,price) VALUES (5,'Produto E', 330.25);