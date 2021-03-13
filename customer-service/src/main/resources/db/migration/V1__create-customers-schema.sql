CREATE TABLE customer (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    card_number VARCHAR(255) NOT NULL,
    CONSTRAINT id UNIQUE (id),
    CONSTRAINT name UNIQUE (name)
);

INSERT INTO customer (id,name,email,card_number) VALUES (1,'Christine S. Schmidt', 'a@a.com', '4485366752589003');
INSERT INTO customer (id,name,email,card_number) VALUES (2,'Mac G. Martin', 'b@b.com', '4024007106816894');
INSERT INTO customer (id,name,email,card_number) VALUES (3,'Robert M. Murphy', 'c@c.com', '5308897084222030');
INSERT INTO customer (id,name,email,card_number) VALUES (4,'Joe K. Hansen', 'd@d.com', '5267651035843272');
INSERT INTO customer (id,name,email,card_number) VALUES (5,'Michelle A. Deboer', 'e@e.com', '6304731348923269');