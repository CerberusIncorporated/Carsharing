package by.yukhnevich.carsharing.carsharing.controller.command.impl;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import by.yukhnevich.carsharing.carsharing.util.SessionAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;
/**
 * Command for change language
 */
public class ChangeLanguageCommand implements Command {
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String localeParameter = request.getParameter(RequestParameter.LOCALE);
        Locale.Builder builder = new Locale.Builder();
        builder.setLanguageTag(localeParameter);
        Locale locale = builder.build();
        request.getSession().setAttribute(SessionAttribute.LANGUAGE, locale);
        String header = request.getHeader(REFERER);
        response.sendRedirect(header);
    }
}
