package com.backend.pharmacy.service;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pharmacy.config.DataSourceConfig;

import javax.sql.DataSource;
import java.util.Map;

@Service
public class FlywayMigrationService {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    public void migrateTenants() {
        Map<Object, Object> dataSources = dataSourceConfig.getDataSources();

        for (Map.Entry<Object, Object> entry : dataSources.entrySet()) {
            String tenantId = (String) entry.getKey();
            DataSource dataSource = (DataSource) entry.getValue();

            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations("classpath:db/init")
                    .baselineOnMigrate(true) 
                    .load();

            try {
                flyway.migrate();
                System.out.println("Migrations applied for tenant: " + tenantId);
            } catch (Exception e) {
                System.err.println("Error applying migrations for tenant " + tenantId + ": " + e.getMessage());
            }
        }
    }
}
