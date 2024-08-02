CREATE TABLE IF NOT EXISTS scenario (
    id SERIAL,
    description TEXT,
    applicatif_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (applicatif_id) REFERENCES applicatif (id) ON DELETE CASCADE
);

INSERT INTO scenario (description, applicatif_id) VALUES
    ('Login and register user', 1),
    ('Search for products', 2),
    ('Place an order', 1);