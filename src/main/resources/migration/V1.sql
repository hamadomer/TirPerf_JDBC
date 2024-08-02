CREATE TABLE IF NOT EXISTS fonction (
    id SERIAL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pansi (
    id SERIAL,
    PRIMARY KEY (id)
);

INSERT INTO pansi DEFAULT VALUES;
INSERT INTO pansi DEFAULT VALUES;
INSERT INTO pansi DEFAULT VALUES;

INSERT INTO fonction (name) VALUES
   ('Login'),
   ('Search'),
   ('Order');