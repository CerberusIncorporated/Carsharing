package by.yukhnevich.carsharing.carsharing.controller.filter.impl;

import by.yukhnevich.carsharing.carsharing.controller.filter.AbstractFilter;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
/**
 * Check if user is user
 */
@WebFilter(filterName = "UserFilter", urlPatterns = { "/Controller?command=gotoorderspage" })
public class UserFilter extends AbstractFilter {

    private static final String LOGIN_PAGE = "/login";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = getUser(servletRequest);
        if (user == null) {
            servletRequest.getRequestDispatcher(LOGIN_PAGE).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
