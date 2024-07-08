package org.example.Helpers;

import org.flywaydb.core.Flyway;

public class FlywayConfig {
    private Flyway flyway;

    public FlywayConfig() {
        try {
            flyway = Flyway.configure()
                    .dataSource("jdbc:postgresql://localhost:5432/tirperf", "postgres", "postgres")
                    .locations("classpath:/migration")
                    .load();
        } catch (Exception e) {
            System.err.println("Error initializing Flyway: " + e.getMessage());
            flyway = null;
        }
    }

    public void migrate() {
        if (flyway == null) {
            System.err.println("Flyway object is null, cannot migrate");
            return;
        }
        try {
            flyway.migrate();
        } catch (Exception e) {
            System.err.println("Error during migration: " + e.getMessage());
        }
    }
}