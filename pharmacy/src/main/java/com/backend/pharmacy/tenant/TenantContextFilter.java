package com.backend.pharmacy.tenant;

import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Component
public class TenantContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tenantId = ((HttpServletRequest) request).getHeader("X-Tenant-ID");
        
        if (tenantId != null && !tenantId.isEmpty()) {
            TenantContext.setTenantId(tenantId);
        } else {
            throw new ServletException("Tenant ID is missing in the request header");
        }

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear(); 
        }
    }

    @Override
    public void destroy() {
        
    }
}
