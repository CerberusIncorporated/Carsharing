package by.yukhnevich.carsharing.carsharing.controller.command.impl.news;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.News;
import by.yukhnevich.carsharing.carsharing.model.service.NewsService;
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
 * Forwards to the page with all news
 *
 * @see News
 * @see Command
 * @see RequestDispatcher
 */
public class GoToNewsPage implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    private static final String NEWS_PAGE = "/WEB-INF/jsp/news.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;

        try {
            NewsService newsService = SERVICE_PROVIDER.getNewsService();
            List<News> news = newsService.getAll();
            request.setAttribute(RequestParameter.NEWS, news);
            requestDispatcher = request.getRequestDispatcher(NEWS_PAGE);
        } catch (ServiceException e) {
            LOGGER.error(e);
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        requestDispatcher.forward(request, response);
    }
}
