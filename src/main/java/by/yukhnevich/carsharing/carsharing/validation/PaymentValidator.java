package by.yukhnevich.carsharing.carsharing.validation;

import by.yukhnevich.carsharing.carsharing.util.DateUtil;

import java.util.Date;
import java.util.regex.Pattern;

public class PaymentValidator extends Validator {
    private static final Pattern CARD_NUMBER_REGEX = Pattern.compile("\\d{16}");
    private static final Pattern CVV_NUMBER_REGEX = Pattern.compile("\\d{3}");

    private static final DateUtil DATE_UTILS = new DateUtil();

    public boolean isCardNumberValid(String cardNumber) {
        boolean isMatch = CARD_NUMBER_REGEX.matcher(cardNumber).matches();
        if (!isMatch) {
            message = "Invalid card number";
        }
        return isMatch;
    }

    public boolean isExpirationDateValid(Date expirationDate) {
        boolean isValid = expirationDate.after(DATE_UTILS.getCurrentDateWithoutTime());
        if (!isValid) {
            message = "Invalid expiry date";
        }
        return isValid;
    }

    public boolean isCvvNumberValid(String cvvNumber) {
        boolean isMatch = CVV_NUMBER_REGEX.matcher(cvvNumber).matches();
        if (!isMatch) {
            message = "Invalid cvv";
        }
        return isMatch;
    }
}
