package by.yukhnevich.carsharing.carsharing.model.dao;

import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.Order;
import by.yukhnevich.carsharing.carsharing.model.entity.status.OrderStatus;

import java.util.List;

public interface OrderDao extends Dao<Order>{
    List<Order> getAllByUserId(int userId) throws DaoException;

    void changeStatus(int orderId, OrderStatus status) throws DaoException;

    void addRejectionComment(int orderId, String comment) throws DaoException;
    void addReturnComment(int orderId, String comment) throws DaoException;
}
