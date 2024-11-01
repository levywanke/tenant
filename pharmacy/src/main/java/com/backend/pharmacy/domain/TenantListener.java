package com.backend.pharmacy.domain;

import com.backend.pharmacy.tenant.TenantContext;
import jakarta.persistence.PrePersist;

public class TenantListener {

    @PrePersist
    
    public void setTenantId(Product product) {
       
        String tenantId = TenantContext.getTenantId();
        product.setTenantId(tenantId);
    }
}
