package by.yukhnevich.carsharing.carsharing.controller;

import java.io.*;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.controller.command.CommandType;
import by.yukhnevich.carsharing.carsharing.model.connection.ConnectionPool;
import by.yukhnevich.carsharing.carsharing.model.connection.exception.ConnectionPoolException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents main controller of the application as the {@link HttpServlet}
 */
@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Process the request
     *
     * @param request contains the command that will be executed
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandType.defineCommand(name);
        command.execute(request, response);
    }
}