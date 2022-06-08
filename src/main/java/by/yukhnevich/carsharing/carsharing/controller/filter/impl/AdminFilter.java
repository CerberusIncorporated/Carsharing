package by.yukhnevich.carsharing.carsharing.controller.filter.impl;

import by.yukhnevich.carsharing.carsharing.controller.filter.AbstractFilter;
import by.yukhnevich.carsharing.carsharing.model.entity.Role;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * Check if user admin.
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = {"/Controller?command=gotonewseditpage", "/Controller?command=gotocareditpage"})
public class AdminFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = getUser(servletRequest);
        if (user == null) {
            throw new ServletException("User is not authenticated");
        }
        if (user.getRole() != Role.ADMIN) {
            throw new ServletException("User is not authorized");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
