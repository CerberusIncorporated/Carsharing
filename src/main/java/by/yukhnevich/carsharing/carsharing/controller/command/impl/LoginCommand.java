package by.yukhnevich.carsharing.carsharing.controller.command.impl;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.UserService;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;
/**
 * Checking whether the user is in the database and redirects to the news page
 */
public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String GO_TO_LOGIN_PAGE = "Controller?command=gotologinpage&error=%s&validation=%s";
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandResult = GO_TO_NEWS_PAGE;

        String email;
        String password;

        email = request.getParameter(RequestParameter.EMAIL);
        password = request.getParameter(RequestParameter.PASSWORD);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        HttpSession session = request.getSession();

        Optional<User> user;

        try {
            user = userService.findUserByEmailAndPassword(email, password);
            if (!user.isPresent()) {
                throw new InvalidDataException();
            }
            session.setAttribute(SessionAttribute.USER, user.get());
        } catch (ServiceException e) {
            LOGGER.fatal(e);
            commandResult = String.format(GO_TO_LOGIN_PAGE, true, null);
        } catch (InvalidDataException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_LOGIN_PAGE, null, e.getMessage());
        }
        response.sendRedirect(commandResult);
    }
}
