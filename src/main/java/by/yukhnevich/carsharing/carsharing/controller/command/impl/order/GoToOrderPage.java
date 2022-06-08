package by.yukhnevich.carsharing.carsharing.controller.command.impl.order;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.car.Car;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.service.CarService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

import static by.yukhnevich.carsharing.carsharing.util.RequestUntil.processRequestErrors;

/**
 * Forwards to order page where user choose the dates of order
 * @see Command
 */
public class GoToOrderPage implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String ORDER_PAGE = "/WEB-INF/jsp/order.jsp";
    private static final String LOGIN_PAGE = "/login";
    private static final String ERROR_PAGE = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;

        try {
            CarService carService = SERVICE_PROVIDER.getCarService();
            Optional<Car> car = carService.getById(Integer.parseInt(request.getParameter(RequestParameter.DATA_ID)));

            car.ifPresent(value -> request.setAttribute(RequestParameter.CAR, value));

            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            if (user != null) {
                requestDispatcher = request.getRequestDispatcher(ORDER_PAGE);
            } else {
                requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            }
            processRequestErrors(request);
        } catch (ServiceException e) {
            LOGGER.error(e);
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}
