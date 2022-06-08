package by.yukhnevich.carsharing.carsharing.controller.command.impl.payment;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.Order;
import by.yukhnevich.carsharing.carsharing.model.service.OrderService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.PriceCalculator;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;

import static by.yukhnevich.carsharing.carsharing.util.RequestUntil.processRequestErrors;

/**
 * Forwards to the payment page
 *
 * @see by.yukhnevich.carsharing.carsharing.model.entity.Payment
 * @see Command
 * @see RequestDispatcher
 */
public class GoToPaymentPage implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PAYMENT_PAGE = "/WEB-INF/jsp/payment.jsp";
    private static final String ERROR_PAGE = "error.jsp";
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;

        int orderId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));
        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        PriceCalculator priceCalculator = new PriceCalculator();

        processRequestErrors(request);
        try {
            Order order = orderService.getById(orderId).get();
            BigDecimal totalPrice = priceCalculator.calculatePrice(order);
            request.setAttribute(RequestParameter.TOTAL_PRICE, totalPrice);
            request.setAttribute(RequestParameter.DATA, order);
            dispatcher = request.getRequestDispatcher(PAYMENT_PAGE);
        } catch (ServiceException e) {
            LOGGER.error(e);
            dispatcher = request.getRequestDispatcher(ERROR_PAGE);
        }
        dispatcher.forward(request, response);
    }
}
