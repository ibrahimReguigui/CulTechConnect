/*
package com.example.web.chatservice.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@WebFilter
@Order(1) // Define the order of this filter
public class RequestLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Log request headers
        System.out.println("Request Headers for Microservice:");
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> {
                    System.out.println(headerName + ": " + request.getHeader(headerName));
                });

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
*/
