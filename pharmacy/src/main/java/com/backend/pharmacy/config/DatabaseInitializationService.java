package com.backend.pharmacy.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

@Component
public class DatabaseInitializationService {

    private final DataSourceConfig dataSourceConfig;

    public DatabaseInitializationService(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    @PostConstruct
    public void initializeDatabases() {
        Map<Object, Object> dataSources = dataSourceConfig.getDataSources();
        
        
        dataSources.forEach((tenantId, dataSource) -> {
            try {
                if (!isTablePresent((DataSource) dataSource, "product")) {
                    System.out.println("Initializing schema for tenant: " + tenantId);
                    executeSqlScript((DataSource) dataSource, "init/schema.sql");
                } else {
                    System.out.println("Tables already exist for tenant: " + tenantId);
                }
            } catch (Exception e) {
                System.err.println("Failed to initialize schema for tenant " + tenantId + ": " + e.getMessage());
            }
        });
    }

    private boolean isTablePresent(DataSource dataSource, String tableName) throws Exception {
        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet tables = metaData.getTables(null, null, tableName, null)) {
                return tables.next();  // Returns true if table exists
            }
        }
    }

    private void executeSqlScript(DataSource dataSource, String scriptPath) throws Exception {
        ClassPathResource resource = new ClassPathResource(scriptPath);
        if (!resource.exists()) {
            System.err.println("SQL file not found: " + scriptPath);
            return;
        }

        try (Connection connection = DataSourceUtils.getConnection(dataSource);
             Statement statement = connection.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get(resource.getURI())));
            statement.execute(sql);
            System.out.println("Executed script: " + scriptPath + " for tenant's DataSource.");
        }
    }
}
