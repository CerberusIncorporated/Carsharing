package by.yukhnevich.carsharing.carsharing.model.entity;

import by.yukhnevich.carsharing.carsharing.model.entity.status.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

public class Payment extends Entity {
    private int id;
    private int orderId;
    private PaymentStatus status;
    private BigDecimal totalPrice;
    private Date paymentDate;

    public Payment() {
    }

    public Payment(int id, int orderId, PaymentStatus status, BigDecimal totalPrice, Date paymentDate) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.totalPrice = totalPrice;
        this.paymentDate = paymentDate;
    }

    public Payment(int orderId, PaymentStatus status, BigDecimal totalPrice, Date paymentDate) {
        this(-1, orderId, status, totalPrice, paymentDate);
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (orderId != payment.orderId) return false;
        if (status != payment.status) return false;
        if (totalPrice != null ? !totalPrice.equals(payment.totalPrice) : payment.totalPrice != null) return false;
        return paymentDate != null ? paymentDate.equals(payment.paymentDate) : payment.paymentDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", status=").append(status);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append('}');
        return sb.toString();
    }
}
