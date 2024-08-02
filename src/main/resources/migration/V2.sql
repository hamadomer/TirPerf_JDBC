CREATE TABLE IF NOT EXISTS applicatif (
    id SERIAL,
    version VARCHAR(255),
    intitule VARCHAR(255),
    fonction_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (fonction_id) REFERENCES fonction (id)
);

INSERT INTO applicatif (version, intitule, fonction_id) VALUES
   ('1.0.0', 'MyApp', 1),
   ('2.1.3', 'MyAppWeb', 2),
   ('1.2.5', 'MyAppMobile', 3);