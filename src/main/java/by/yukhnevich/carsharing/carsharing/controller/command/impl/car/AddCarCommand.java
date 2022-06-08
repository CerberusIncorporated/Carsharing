package by.yukhnevich.carsharing.carsharing.controller.command.impl.car;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.car.*;
import by.yukhnevich.carsharing.carsharing.model.service.CarService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Adds car to the database
 *
 * @see Command
 * @see Car
 */
public class AddCarCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String GO_TO_CARS_PAGE = "Controller?command=gotocarspage";
    private static final String GO_TO_CAR_EDIT_PAGE = "Controller?command=gotocareditpage&error=%s&validation=%s";
    private static final String ERROR_MESSAGE = "Something went wrong";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = GO_TO_CARS_PAGE;

        String brand = request.getParameter(RequestParameter.BRAND_EDITOR);
        String model = request.getParameter(RequestParameter.MODEL_EDITOR);
        CarColor color = CarColor.valueOf(request.getParameter(RequestParameter.COLOR).toUpperCase());
        int seats = Integer.parseInt(request.getParameter(RequestParameter.SEATS));
        GearboxType gearbox = GearboxType.valueOf(request.getParameter(RequestParameter.GEARBOX_EDITOR).toUpperCase());
        String year = request.getParameter(RequestParameter.YEAR_EDITOR);
        EngineType engine = EngineType.valueOf(request.getParameter(RequestParameter.ENGINE_EDITOR).toUpperCase());
        BigDecimal price = new BigDecimal(request.getParameter(RequestParameter.PRICE_EDITOR));
        String vin = request.getParameter(RequestParameter.VIN);
        String plate = request.getParameter(RequestParameter.PLATE);
        CarClass carClass = CarClass.valueOf(request.getParameter(RequestParameter.CLASS_EDITOR).toUpperCase());
        String imagePath = (String) request.getAttribute(RequestParameter.IMAGE_PATH);

        try {
            CarService carService = SERVICE_PROVIDER.getCarService();
            Car car = new Car(brand, model, color, seats, gearbox, year, engine, price, vin, plate, carClass, imagePath);
            carService.add(car);
        } catch (ServiceException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_CAR_EDIT_PAGE, ERROR_MESSAGE, null);
        } catch (InvalidDataException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_CAR_EDIT_PAGE, null, e.getMessage());
        }
        response.sendRedirect(commandResult);
    }
}
