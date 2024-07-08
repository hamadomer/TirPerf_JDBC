package org.example.Helpers;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class FlywayExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        FlywayConfig flywayConfig = new FlywayConfig();
        flywayConfig.migrate();
    }
}