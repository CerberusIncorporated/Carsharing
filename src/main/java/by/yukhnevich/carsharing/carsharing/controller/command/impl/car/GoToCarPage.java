package by.yukhnevich.carsharing.carsharing.controller.command.impl.car;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.car.Car;
import by.yukhnevich.carsharing.carsharing.model.service.CarService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Forwards to the page with cars
 *
 * @see Command
 * @see Car
 * @see RequestDispatcher
 */
public class GoToCarPage implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    private static final String CARS_PAGE = "/WEB-INF/jsp/cars.jsp";
    private static final String ERROR_PAGE = "error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        try {
            CarService carService = SERVICE_PROVIDER.getCarService();

            String criteria = request.getParameter(RequestParameter.SEARCH);
            List<Car> cars;
            if (criteria != null && !criteria.isEmpty()) {
                cars = SERVICE_PROVIDER.getCarService().getCarsByName(criteria);
            } else {
                cars = carService.getAll();
            }
            request.setAttribute(RequestParameter.CARS, cars);
            requestDispatcher = request.getRequestDispatcher(CARS_PAGE);
        } catch (ServiceException e) {
            LOGGER.error(e);
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}
