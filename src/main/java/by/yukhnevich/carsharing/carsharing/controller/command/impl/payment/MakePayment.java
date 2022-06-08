package by.yukhnevich.carsharing.carsharing.controller.command.impl.payment;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.model.entity.Payment;
import by.yukhnevich.carsharing.carsharing.model.entity.status.PaymentStatus;
import by.yukhnevich.carsharing.carsharing.model.service.PaymentService;
import by.yukhnevich.carsharing.carsharing.model.service.ServiceProvider;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.util.DateUtil;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import by.yukhnevich.carsharing.carsharing.validation.PaymentValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create and saves {@link Payment} in the database
 *
 * @see Command
 */
public class MakePayment implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final DateUtil DATE_UTILS = new DateUtil();
    private static final String CHANGE_STATUS = "Controller?command=changeorderstatus&status=paid&data_id=%d";
    private static final String GO_TO_PAYMENT_PAGE = "Controller?command=gotopaymentpage&data_id=%d&error=%s&validation=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.DATA_ID));

        String commandResult = String.format(CHANGE_STATUS, orderId);

        try {
            String cardNumber = request.getParameter(RequestParameter.CARD_NUMBER);
            String cvv = request.getParameter(RequestParameter.CVV);
            Date expiryDate = DATE_UTILS.parseDate(request.getParameter(RequestParameter.EXPIRY_DATE));

            PaymentValidator validator = new PaymentValidator();
            if (!validator.isCardNumberValid(cardNumber)
                    || !validator.isCvvNumberValid(cvv)
                    || !validator.isExpirationDateValid(expiryDate)) {
                throw new InvalidDataException(validator.getMessage());
            }

            BigDecimal totalPrice = new BigDecimal(request.getParameter(RequestParameter.TOTAL_PRICE));

            // here should be actual payment process

            PaymentService paymentService = ServiceProvider.getInstance().getPaymentService();
            Payment payment = new Payment(orderId, PaymentStatus.APPROVED, totalPrice, null);
            paymentService.add(payment);
            LOGGER.debug("Payment added: ");
        } catch (ServiceException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_PAYMENT_PAGE, orderId, true, null);
        } catch (InvalidDataException e) {
            LOGGER.error(e);
            commandResult = String.format(GO_TO_PAYMENT_PAGE, orderId, null, e.getMessage());
        }
        LOGGER.debug("Command result: " + commandResult);
        response.sendRedirect(commandResult);
    }
}
