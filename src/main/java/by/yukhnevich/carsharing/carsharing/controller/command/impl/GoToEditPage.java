package by.yukhnevich.carsharing.carsharing.controller.command.impl;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.controller.command.CommandType;
import by.yukhnevich.carsharing.carsharing.model.entity.News;
import by.yukhnevich.carsharing.carsharing.model.entity.Role;
import by.yukhnevich.carsharing.carsharing.model.entity.car.Car;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.service.CarService;
import by.yukhnevich.carsharing.carsharing.model.service.NewsService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

import static by.yukhnevich.carsharing.carsharing.util.RequestUntil.processRequestErrors;
/**
 * Forward user to edit page
 */
public class GoToEditPage implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String NEWS_EDIT_PAGE = "/WEB-INF/jsp/news_edit.jsp";
    private static final String CAR_EDIT_PAGE = "/WEB-INF/jsp/car_edit.jsp";
    private static final String LOGIN_PAGE = "/login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);

        CarService carService = ServiceProvider.getInstance().getCarService();
        NewsService newsService = ServiceProvider.getInstance().getNewsService();

        String dataId = request.getParameter(RequestParameter.DATA_ID);

        try {
            CommandType commandType = CommandType.valueOf(request.getParameter(RequestParameter.COMMAND).toUpperCase());
            switch (commandType) {
                case GOTOCAREDITPAGE:
                    if (dataId != null) {
                        Optional<Car> car = carService.getById(
                                Integer.parseInt(dataId));
                        request.setAttribute(RequestParameter.DATA, car.get());
                    }
                    processRequestErrors(request);
                    executeCommandResult(request, response, user, CAR_EDIT_PAGE);
                    break;
                case GOTONEWSEDITPAGE:
                    if (dataId != null) {
                        Optional<News> news = newsService.findNewsById(
                                Integer.parseInt(dataId));
                        request.setAttribute(RequestParameter.DATA, news.get());
                    }
                    processRequestErrors(request);
                    executeCommandResult(request, response, user, NEWS_EDIT_PAGE);
                    break;
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
    }


    /**
     * Logic of command result
     *
     * @param user is used to choose the page to forward
     * @param page page path
     */
    private void executeCommandResult(HttpServletRequest request, HttpServletResponse response, User user, String page)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        if (user != null && user.getRole() == Role.ADMIN) {
            requestDispatcher = request.getRequestDispatcher(page);
        } else if (user == null) {
            requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        } else {
            throw new ServletException("User is not authenticated");
        }
        requestDispatcher.forward(request, response);
    }
}
