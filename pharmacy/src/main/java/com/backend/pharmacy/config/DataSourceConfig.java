package com.backend.pharmacy.config;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    private final Map<Object, Object> dataSources = new HashMap<>();

    @Bean
    public DataSource tenantDataSource() {
        DataSourceRouter routingDataSource = new DataSourceRouter();

        // Initialize each tenant DataSource
        dataSources.put("tenant1", createTenantDataSource("tenant1"));
        dataSources.put("tenant2", createTenantDataSource("tenant2"));
        dataSources.put("tenant3", createTenantDataSource("tenant3"));
        dataSources.put("tenant4", createTenantDataSource("tenant4"));

        routingDataSource.setTargetDataSources(dataSources);
        routingDataSource.setDefaultTargetDataSource(dataSources.get("tenant1"));

       
        verifyTenantDatabases(dataSources);

        return routingDataSource;
    }

    private DataSource createTenantDataSource(String tenant) {
        return DataSourceBuilder.create()
                .driverClassName("org.mariadb.jdbc.Driver")
                .url("jdbc:mariadb://localhost:3306/" + tenant + "?createDatabaseIfNotExist=true")
                .username("root")
                .password("primes..")
                .build();
    }

    private void verifyTenantDatabases(Map<Object, Object> dataSources) {
        dataSources.forEach((tenantId, dataSource) -> {
            try (Connection connection = ((DataSource) dataSource).getConnection()) {
                System.out.println("Successfully connected to database for tenant: " + tenantId);
            } catch (SQLException e) {
                System.err.println("Failed to connect to database for tenant " + tenantId + ": " + e.getMessage());
            }
        });
    }

   
    public Map<Object, Object> getDataSources() {
        return dataSources;
    }

    
}