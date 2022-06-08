package by.yukhnevich.carsharing.carsharing.controller.command.impl.news;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.News;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.service.NewsService;
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
 * Edits {@link News} information and update it in the database
 *
 * @see Command
 */
public class EditNewsCommand implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";
    private static final String GO_TO_NEWS_EDIT_PAGE = "Controller?command=gotonewseditpage&data_id=%d&validation=%s&error=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandResult = GO_TO_NEWS_PAGE;

        int id = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        int userId = ((User) request.getSession().getAttribute(SessionAttribute.USER)).getId();
        String header = request.getParameter(RequestParameter.HEADER_EDITOR);
        String content = request.getParameter(RequestParameter.CONTENT_EDITOR);
        String imagePath = (String) request.getAttribute(RequestParameter.IMAGE_PATH);

        try {
            NewsService newsService = SERVICE_PROVIDER.getNewsService();
            newsService.update(new News(id, userId, header, content, imagePath));
        } catch (ServiceException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_NEWS_EDIT_PAGE, id, null, e.getMessage());
        } catch (InvalidDataException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_NEWS_EDIT_PAGE, id, e.getMessage(), null);
        }
        response.sendRedirect(commandResult);
    }
}
