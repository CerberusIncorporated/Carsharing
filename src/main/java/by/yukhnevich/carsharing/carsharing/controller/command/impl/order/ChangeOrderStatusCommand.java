package by.yukhnevich.carsharing.carsharing.controller.command.impl.order;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.status.OrderStatus;
import by.yukhnevich.carsharing.carsharing.model.service.OrderService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Changes order status and
 * saves return comments and rejection comments in the database
 * @see Command
 * @see OrderStatus
 */
public class ChangeOrderStatusCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final String GO_TO_ORDERS_PAGE = "Controller?command=gotoorderspage&error=%s&validation=%s";
    private static final String GO_TO_ORDERS_COMMAND = "command=gotoorderspage";
    private static final String REFERER = "referer";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String header = request.getHeader(REFERER);
        String commandResult = header.contains(GO_TO_ORDERS_COMMAND) ? header : GO_TO_ORDERS_PAGE;

        OrderStatus status = OrderStatus.valueOf(request.getParameter(RequestParameter.STATUS).toUpperCase());
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        String rejectionComment = request.getParameter(RequestParameter.REJECTION_COMMENT);
        String returnComment = request.getParameter(RequestParameter.RETURN_COMMENT);

        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        try {
            orderService.changeStatus(orderId, status);
            if (rejectionComment != null) {
                orderService.addRejectionComment(orderId, rejectionComment);
            }
            if (returnComment != null) {
                orderService.addReturnComment(orderId, returnComment);
            }
        } catch (ServiceException e) {
            LOGGER.fatal(e);
            commandResult = String.format(GO_TO_ORDERS_PAGE, true, null);
        }
        response.sendRedirect(commandResult);
    }
}
