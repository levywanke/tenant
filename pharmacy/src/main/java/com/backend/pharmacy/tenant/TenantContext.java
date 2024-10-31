package com.backend.pharmacy.tenant;

public class TenantContext {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
       // System.out.println("Setting Tenant ID in Context: " + tenantId);  
        CONTEXT.set(tenantId);
    }

    public static String getTenantId() {
        String tenantId = CONTEXT.get();
       // System.out.println("Retrieved Tenant ID from Context: " + tenantId);  
        return tenantId;
    }

    public static void clear() {
       // System.out.println("Clearing Tenant ID from Context");  
        CONTEXT.remove();
    }
}
