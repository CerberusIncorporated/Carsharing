package by.yukhnevich.carsharing.carsharing.controller.command.impl.order;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.Order;
import by.yukhnevich.carsharing.carsharing.model.entity.Role;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.service.OrderService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Forwards to the page with all orders if user's role is admin
 * and only orders that made by user itself if role is user
 *
 * @see Command
 * @see Role
 * @see Order
 */
public class GoToUserOrderPage implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String ORDERS_PAGE = "/WEB-INF/jsp/user_orders.jsp";
    private static final String LOGIN_PAGE = "/login";
    private static final String ERROR_PAGE = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders;
        RequestDispatcher requestDispatcher;
        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);

        try {
            if (user != null) {
                requestDispatcher = request.getRequestDispatcher(ORDERS_PAGE);
                orders = user.getRole() == Role.ADMIN
                        ? orderService.getAll()
                        : orderService.getAllByUserId(user.getId());
                request.setAttribute(RequestParameter.ORDERS, orders);
            } else {
                requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}
