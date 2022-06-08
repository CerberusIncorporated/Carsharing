package by.yukhnevich.carsharing.carsharing.controller.command.impl;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Removes user from the session and redirects to the news page
 *
 * @see Command
 * @see HttpServletResponse
 */
public class SignOutCommand implements Command {
    private static final String GO_TO_NEWS_PAGE = "Controller?command=gotonewspage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(SessionAttribute.USER);
        response.sendRedirect(GO_TO_NEWS_PAGE);
    }

}
