package by.yukhnevich.carsharing.carsharing.controller.filter;

import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
/**
 * Abstract filter for check users
 */
public abstract class AbstractFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    protected User getUser(ServletRequest servletRequest) {
        HttpSession session = getSession(servletRequest);
        return (User) session.getAttribute(SessionAttribute.USER);
    }

    protected HttpSession getSession(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getSession();
    }
}
