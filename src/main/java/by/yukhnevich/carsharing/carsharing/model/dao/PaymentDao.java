package by.yukhnevich.carsharing.carsharing.model.dao;

import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.Payment;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public interface PaymentDao extends Dao<Payment> {
    Optional<Payment> getById(int id) throws DaoException;

    List<Payment> getAll() throws DaoException;

    void add(Payment entity) throws DaoException;

    void deleteById(int id) throws DaoException;

    List<Payment> executeForManyResults(PreparedStatement statement);

    Optional<Payment> executeForSingleResult(PreparedStatement statement);
}
