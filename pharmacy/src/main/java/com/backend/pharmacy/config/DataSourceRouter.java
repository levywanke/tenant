package com.backend.pharmacy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.backend.pharmacy.tenant.TenantContext;

public class DataSourceRouter extends AbstractRoutingDataSource {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceRouter.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String tenantId = TenantContext.getTenantId();
        
       
        if (tenantId == null) {
            logger.warn("No tenant ID found! Defaulting to the configured default tenant.");
            
            return "tenant_default"; 
        } else {
            logger.debug("Routing to tenant ID: {}", tenantId);
        }
        
        return tenantId; 
    }
}
