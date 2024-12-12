package com.wolfcode.multitenancy.multitenancy;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantFilter implements Filter {


    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String tenantId = httpRequest.getHeader("X-TenantId");

        boolean isRegisterEndpoint = requestURI.equalsIgnoreCase("/my-shop/register");
        boolean isActuatorEndpoint = requestURI.startsWith("/my-shop/actuator");

        if (isRegisterEndpoint || isActuatorEndpoint) {
            chain.doFilter(request, response);
            return;
        }

        if (tenantId == null || tenantId.isBlank()) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing tenantId header");
            return;
        }

        TenantContext.setCurrentTenant(tenantId);

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}