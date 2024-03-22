package com.estsoft.blogjpa.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j

public class UrlPrintFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        log.info("url print filter init() - {}", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("do filter {}", httpServletRequest.getRequestURL().toString());
        chain.doFilter(request,response);
        request.setAttribute("transactionId", UUID.randomUUID());
    }

    @Override
    public void destroy(){
        log.info("url print filter destroy");
    }
}
