package by.yukhnevich.carsharing.carsharing.controller.command.impl;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.yukhnevich.carsharing.carsharing.util.RequestUntil.processRequestErrors;
/**
 * Forward user to registration
 */
public class GoToRegisterPage implements Command {
    private static final String REGISTER_PAGE = "/register";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequestErrors(request);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(REGISTER_PAGE);
        requestDispatcher.forward(request, response);
    }
}
