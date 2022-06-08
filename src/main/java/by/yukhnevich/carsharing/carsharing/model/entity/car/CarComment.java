package by.yukhnevich.carsharing.carsharing.model.entity.car;

import by.yukhnevich.carsharing.carsharing.model.entity.Entity;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;

public class CarComment extends Entity {
    private int id;
    private User user;
    private Car car;
    private String content;

    public CarComment() {
    }

    public CarComment(int id, User user, Car car, String content) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.content = content;
    }

    public CarComment(User user, Car car, String content) {
        this(-1, user, car, content);
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

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarComment that = (CarComment) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (car != null ? !car.equals(that.car) : that.car != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarComment{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", car=").append(car);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
