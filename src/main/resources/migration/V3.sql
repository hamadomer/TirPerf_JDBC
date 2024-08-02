CREATE TABLE IF NOT EXISTS apppansi (
    pansi_id int,
    app_id int,
    app_version VARCHAR(255),
    FOREIGN KEY (pansi_id) REFERENCES pansi (id),
    FOREIGN KEY (app_id) REFERENCES applicatif (id) ON DELETE CASCADE
);