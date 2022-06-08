package by.yukhnevich.carsharing.carsharing.controller.command.impl.comment;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.service.CarCommentService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Deletes {@link by.yukhnevich.carsharing.carsharing.model.entity.car.CarComment} from database
 * @see Command
 */
public class DeleteComment implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String ERROR_PARAMETER = "&error=true";
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = request.getHeader(REFERER);

        int commentId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        CarCommentService commentService = SERVICE_PROVIDER.getCommentService();
        try {
            commentService.deleteById(commentId);
        } catch (ServiceException e) {
            LOGGER.error(e);
            commandResult += ERROR_PARAMETER;
        }
        response.sendRedirect(commandResult);
    }
}
