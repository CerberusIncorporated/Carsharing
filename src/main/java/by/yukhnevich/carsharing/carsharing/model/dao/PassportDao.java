package by.yukhnevich.carsharing.carsharing.model.dao;

import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.user.Passport;

import java.util.Optional;

public interface PassportDao {
    void add(Passport entity) throws DaoException;

    Optional<Passport> getByPassportNumber(String passportNumber) throws DaoException;
}
