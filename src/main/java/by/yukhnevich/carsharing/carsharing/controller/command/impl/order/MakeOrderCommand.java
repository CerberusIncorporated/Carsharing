package by.yukhnevich.carsharing.carsharing.controller.command.impl.order;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.Order;
import by.yukhnevich.carsharing.carsharing.model.entity.car.Car;
import by.yukhnevich.carsharing.carsharing.model.entity.status.OrderStatus;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.service.CarService;
import by.yukhnevich.carsharing.carsharing.model.service.OrderService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.DateUtil;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Date;

/**
 * Create and saves {@link Order} in the database
 *
 * @see Command
 */
public class MakeOrderCommand implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final DateUtil DATE_UTILS = new DateUtil();
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage";
    private static final String GO_TO_ORDER_PAGE = "Controller?command=gotoorderpage&data_id=%d&error=%s&validation=%s";
    private static final String ERROR_MESSAGE = "Something went wrong";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = GO_TO_ORDERS_PAGE;

        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        CarService carService = SERVICE_PROVIDER.getCarService();
        User user = ((User) request.getSession().getAttribute(SessionAttribute.USER));

        try {
            Date startDate = DATE_UTILS.parseDate(request.getParameter(RequestParameter.START_DATE));
            Date endDate = DATE_UTILS.parseDate(request.getParameter(RequestParameter.END_DATE));
            Car car = carService.getById(carId).get();
            Order order = new Order(user, car, OrderStatus.NEW, startDate, endDate, "", "");
            orderService.add(order);
        } catch (ServiceException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_ORDER_PAGE, carId, ERROR_MESSAGE, null);
        } catch (InvalidDataException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_ORDER_PAGE, carId, null, e.getMessage());
        }
        response.sendRedirect(commandResult);
    }
}
