package com.backend.pharmacy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.backend.pharmacy.service.FlywayMigrationService;

@Component
public class DatabaseSetupRunner implements CommandLineRunner {

    private final FlywayMigrationService flywayMigrationService;

    public DatabaseSetupRunner(FlywayMigrationService flywayMigrationService) {
        this.flywayMigrationService = flywayMigrationService;
    }

    @Override
    public void run(String... args) {
        flywayMigrationService.migrateTenants();
    }
}

