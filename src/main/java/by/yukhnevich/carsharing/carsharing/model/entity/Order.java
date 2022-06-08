package by.yukhnevich.carsharing.carsharing.model.entity;

import by.yukhnevich.carsharing.carsharing.model.entity.car.Car;
import by.yukhnevich.carsharing.carsharing.model.entity.status.OrderStatus;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;

import java.util.Date;

public class Order extends Entity {
    private int id;
    private User user;
    private Car car;
    private OrderStatus status;
    private Date startDate;
    private Date endDate;
    private String rejectionComment;
    private String returnComment;

    public Order() {
    }

    public Order(int id, User user, Car car, OrderStatus status, Date startDate, Date endDate,
                 String rejectionComment, String returnComment) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rejectionComment = rejectionComment;
        this.returnComment = returnComment;
    }

    public Order(User user, Car car, OrderStatus status,
                 Date startDate, Date endDate, String rejectionComment, String returnComment) {
        this(-1, user, car, status, startDate, endDate, rejectionComment, returnComment);
    }

    public Order(int id, User user, Car car, OrderStatus status, Date startDate, Date endDate) {
        this(id, user, car, status, startDate, endDate, null, null);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getRejectionComment() {
        return rejectionComment;
    }

    public String getReturnComment() {
        return returnComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (car != null ? !car.equals(order.car) : order.car != null) return false;
        if (status != order.status) return false;
        if (startDate != null ? !startDate.equals(order.startDate) : order.startDate != null) return false;
        if (endDate != null ? !endDate.equals(order.endDate) : order.endDate != null) return false;
        if (rejectionComment != null ? !rejectionComment.equals(order.rejectionComment) : order.rejectionComment != null)
            return false;
        return returnComment != null ? returnComment.equals(order.returnComment) : order.returnComment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (rejectionComment != null ? rejectionComment.hashCode() : 0);
        result = 31 * result + (returnComment != null ? returnComment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", car=").append(car);
        sb.append(", status=").append(status);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", rejectionComment='").append(rejectionComment).append('\'');
        sb.append(", returnComment='").append(returnComment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
