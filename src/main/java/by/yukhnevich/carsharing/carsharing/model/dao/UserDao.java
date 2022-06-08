package by.yukhnevich.carsharing.carsharing.model.dao;

import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.user.Passport;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.entity.user.UserDetail;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    void registerUser(String email, String password, UserDetail details, Passport passport) throws DaoException;
    void registerUser(String email, String password, UserDetail details, Passport passport, int role) throws DaoException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException;
}
