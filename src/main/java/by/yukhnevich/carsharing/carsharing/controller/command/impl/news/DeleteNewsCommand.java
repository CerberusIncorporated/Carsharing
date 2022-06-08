package by.yukhnevich.carsharing.carsharing.controller.command.impl.news;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.service.NewsService;
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
 * Deletes {@link by.yukhnevich.carsharing.carsharing.model.entity.News} from the database
 *
 * @see Command
 */
public class DeleteNewsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        try {
            NewsService newsService = SERVICE_PROVIDER.getNewsService();
            newsService.deleteById(id);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        response.sendRedirect(request.getHeader(REFERER));
    }
}
