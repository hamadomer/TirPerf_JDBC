CREATE TABLE IF NOT EXISTS fonction (
    id SERIAL,
    name VARCHAR(255),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS pansi (
    id SERIAL,
    version VARCHAR(255),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS applicatif (
    id SERIAL,
    version VARCHAR(255),
    intitule VARCHAR(255),
    fonction_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (fonction_id) REFERENCES fonction (id)
    );

CREATE TABLE IF NOT EXISTS scenario (
    id SERIAL,
    description TEXT,
    applicatif_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (applicatif_id) REFERENCES applicatif (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS tirperf (
    id SERIAL,
    tirperf_date DATE,
    scenario_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (scenario_id) REFERENCES scenario (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS fonctionscenario (
    fonction_id int,
    scenario_id int,
    FOREIGN KEY (fonction_id) REFERENCES fonction (id),
    FOREIGN KEY (scenario_id) REFERENCES scenario (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS apppansi (
    pansi_id int,
    app_id int,
    app_version VARCHAR(255),
    FOREIGN KEY (pansi_id) REFERENCES pansi (id),
    FOREIGN KEY (app_id) REFERENCES applicatif (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS contextexecution (
    id SERIAL,
    pansi_id int,
    env VARCHAR(255),
    infocomp VARCHAR(255),
    tirperf_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (pansi_id) REFERENCES pansi (id),
    FOREIGN KEY (tirperf_id) REFERENCES tirperf (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS rapportexecution (
    id SERIAL,
    callsnumber int,
    successrate int,
    errors TEXT,
    duration int,
    tirperf_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (tirperf_id) REFERENCES tirperf (id) ON DELETE CASCADE
    );


INSERT INTO fonction (name) VALUES
  ('Login'),
  ('Search'),
  ('Order');

INSERT INTO applicatif (version, intitule, fonction_id) VALUES
  ('1.0.0', 'MyApp', 1),
  ('2.1.3', 'MyAppWeb', 2),
  ('1.2.5', 'MyAppMobile', 3);

INSERT INTO scenario (description, applicatif_id) VALUES
  ('Login and register user', 1),
  ('Search for products', 2),
  ('Place an order', 1);

INSERT INTO tirperf (tirperf_date, scenario_id) VALUES
  ('2023-03-01', 1),
  ('2023-03-02', 2),
  ('2023-03-03', 3);

INSERT INTO fonctionscenario (fonction_id, scenario_id) VALUES
  (1, 1),
  (2, 2),
  (3, 3);

INSERT INTO pansi (version) VALUES
  ('1.0'),
  ('2.0'),
  ('3.0');

INSERT INTO apppansi (pansi_id, app_id, app_version) VALUES
  (1, 1, '1.0.0'),
  (2, 2, '2.1.3'),
  (3, 3, '1.2.5');

INSERT INTO contextexecution (pansi_id, env, infocomp, tirperf_id) VALUES
  (1, 'dev', 'Windows 10', 1),
  (2, 'qa', 'Ubuntu 20.04', 2),
  (3, 'prod', 'Red Hat Enterprise Linux 8', 3);

INSERT INTO rapportexecution (callsnumber, successrate, errors, duration, tirperf_id) VALUES
  (100, 95, '5 errors', 10, 1),
  (200, 98, '2 errors', 15, 2),
  (300, 92, '8 errors', 20, 3);

