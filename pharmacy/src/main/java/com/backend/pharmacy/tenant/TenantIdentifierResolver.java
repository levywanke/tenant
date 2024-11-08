package com.backend.pharmacy.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import lombok.Data;
@Component
@Data
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getTenantId();
        return (tenantId != null) ? tenantId : "default";  
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
