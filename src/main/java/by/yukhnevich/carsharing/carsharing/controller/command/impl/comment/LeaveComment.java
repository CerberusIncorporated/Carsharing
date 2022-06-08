package by.yukhnevich.carsharing.carsharing.controller.command.impl.comment;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.car.Car;
import by.yukhnevich.carsharing.carsharing.model.entity.car.CarComment;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.service.CarCommentService;
import by.yukhnevich.carsharing.carsharing.model.service.CarService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Adds user's car comment in the database
 *
 * @see Command
 * @see CarComment
 */
public class LeaveComment implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    private static final String GO_TO_COMMENTS_PAGE = "Controller?command=gotocarcommentspage&data_id=%d&error=%s&validation=%s";
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = request.getHeader(REFERER);

        String content = request.getParameter(RequestParameter.CONTENT_EDITOR);
        int carId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        CarService carService = SERVICE_PROVIDER.getCarService();
        CarCommentService commentService = SERVICE_PROVIDER.getCommentService();
        try {
            Car car = carService.getById(carId).get();
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            CarComment comment = new CarComment(user, car, content);
            commentService.add(comment);
        } catch (ServiceException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_COMMENTS_PAGE, carId, true, null);
        } catch (InvalidDataException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_COMMENTS_PAGE, carId, null, e.getMessage());
        }
        response.sendRedirect(commandResult);
    }
}
