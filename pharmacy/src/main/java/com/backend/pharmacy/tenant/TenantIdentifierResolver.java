package com.backend.pharmacy.tenant;

import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver {
    public String resolveTenant() {
        String tenantId = TenantContext.getTenantId();
        if (tenantId == null) {
            throw new IllegalStateException("No tenant ID resolved. Ensure that the tenant context is set.");
        }
       // System.out.println("Resolved Tenant ID: " + tenantId);
        return tenantId;  
    }
}
