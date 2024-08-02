CREATE TABLE IF NOT EXISTS tirperf (
    id SERIAL,
    tirperf_date DATE,
    scenario_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (scenario_id) REFERENCES scenario (id) ON DELETE CASCADE
);

INSERT INTO tirperf (tirperf_date, scenario_id) VALUES
   ('2023-03-01', 1),
   ('2023-03-02', 2),
   ('2023-03-03', 3);