package by.yukhnevich.carsharing.carsharing.util;

import by.yukhnevich.carsharing.carsharing.model.entity.Order;

import java.math.BigDecimal;
import java.util.Date;

public class PriceCalculator {
    private static final DateUtil DATE_UTILS = new DateUtil();

    public BigDecimal calculatePrice(Order order) {
        BigDecimal carPricePerDay = order.getCar().getPricePerDay();

        Date startDate = order.getStartDate();
        Date endDate = order.getEndDate();

        BigDecimal daysInTotal = new BigDecimal(DATE_UTILS.calculateDaysBetweenDates(startDate, endDate) + 1);
        return daysInTotal.multiply(carPricePerDay);
    }
}
