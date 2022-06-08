package by.yukhnevich.carsharing.carsharing.model.dao;

import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Basic interface of all DAO.
 */
public interface Dao<T extends Entity> {
    Optional<T> getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    void add(T entity) throws DaoException;

    void deleteById(int id) throws DaoException;

    List<T> executeForManyResults(PreparedStatement statement) throws SQLException;

    Optional<T> executeForSingleResult(PreparedStatement statement) throws SQLException;
}
