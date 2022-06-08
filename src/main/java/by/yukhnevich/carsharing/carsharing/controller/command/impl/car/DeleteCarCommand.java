package by.yukhnevich.carsharing.carsharing.controller.command.impl.car;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Deletes from database
 *
 * @see Command
 * @see by.yukhnevich.carsharing.carsharing.model.entity.car.Car
 */
public class DeleteCarCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String GO_TO_CARS_PAGE = "Controller?command=gotocarspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
            SERVICE_PROVIDER.getCarService().deleteById(id);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        response.sendRedirect(GO_TO_CARS_PAGE);
    }
}
