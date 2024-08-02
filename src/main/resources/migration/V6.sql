CREATE TABLE IF NOT EXISTS fonctionscenario (
    fonction_id int,
    scenario_id int,
    FOREIGN KEY (fonction_id) REFERENCES fonction (id),
    FOREIGN KEY (scenario_id) REFERENCES scenario (id) ON DELETE CASCADE
);

INSERT INTO fonctionscenario (fonction_id, scenario_id) VALUES
   (1, 1),
   (2, 2),
   (3, 3);

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

INSERT INTO contextexecution (pansi_id, env, infocomp, tirperf_id) VALUES
   (1, 'dev', 'Windows 10', 1),
   (2, 'qa', 'Ubuntu 20.04', 2),
   (3, 'prod', 'Red Hat Enterprise Linux 8', 3);

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

INSERT INTO rapportexecution (callsnumber, successrate, errors, duration, tirperf_id) VALUES
      (100, 95, '5 errors', 10, 1),
      (200, 98, '2 errors', 15, 2),
      (300, 92, '8 errors', 20, 3);