package com.example.login_spring.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.debug("Request URL: {} {}", request.getMethod(), request.getRequestURL());
        logger.debug("Remote Address: {}", request.getRemoteAddr());
        logger.info("Request URL: {} {}", request.getMethod(), request.getRequestURL());
        logger.info("Remote Address: {}", request.getRemoteAddr());
        return true;
    }
}
