package com.backend.pharmacy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class TenantDatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.tenants")
    public Map<String, Map<String, String>> tenantProperties() {
        return new HashMap<>();
    }

    @Bean
    public Map<String, DataSource> tenantDataSources() {
        Map<String, DataSource> dataSources = new HashMap<>();
        Map<String, Map<String, String>> tenants = tenantProperties();

       
        System.out.println("Configuring tenant data sources: " + tenants);

       
        tenants.forEach((key, properties) -> {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setUrl(properties.get("url"));
            ds.setUsername(properties.get("username"));
            ds.setPassword(properties.get("password"));
            dataSources.put(key, ds);
        });

        return dataSources;
    }

    @Bean
    public DataSource dataSource(Map<String, DataSource> tenantDataSources) {
        DataSourceRouter dataSourceRouter = new DataSourceRouter();
       
        dataSourceRouter.setTargetDataSources(new HashMap<>(tenantDataSources));
        dataSourceRouter.setDefaultTargetDataSource(tenantDataSources.get("tenant_a")); 

        return dataSourceRouter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.medicinabackend.pharmacy.domain");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());


        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        em.setJpaPropertyMap(properties);

        return em;
    }
}
