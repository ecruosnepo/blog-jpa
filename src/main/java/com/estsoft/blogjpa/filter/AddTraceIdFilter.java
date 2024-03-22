package com.estsoft.blogjpa.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class AddTraceIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AddTraceIdFilter init() - {}", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setAttribute("traceId", UUID.randomUUID().toString());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("AddTraceIdFilter destroy()");
    }
}
