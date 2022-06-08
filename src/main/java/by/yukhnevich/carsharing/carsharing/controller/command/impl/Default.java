package by.yukhnevich.carsharing.carsharing.controller.command.impl;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
 * If command null or empty forward to error page
 */
public class Default implements Command {
    private static final String DEFAULT = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher(DEFAULT);
        requestDispatcher.forward(request, response);
    }
}
