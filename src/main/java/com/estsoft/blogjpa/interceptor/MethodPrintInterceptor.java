package com.estsoft.blogjpa.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class MethodPrintInterceptor implements HandlerInterceptor {

    // 인터셉터에서 컨트롤러로 요청이 들어가기 전에 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        log.info("MethodPrintInterceptor prehandle()");
        return true;
    }

    // 컨트롤러에서 인터셉터로 요청이 들어온 직후에 실행
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        log.info("MethodPrintInterceptor postHandle() - {}", handlerMethod.getMethod().getName());
    }

    //
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("MethodPrintInterceptor afterCompletion()");
    }
}