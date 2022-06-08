package by.yukhnevich.carsharing.carsharing.model.service.impl;

import by.yukhnevich.carsharing.carsharing.model.dao.DaoHelper;
import by.yukhnevich.carsharing.carsharing.model.dao.UserDao;
import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.user.Passport;
import by.yukhnevich.carsharing.carsharing.model.entity.user.User;
import by.yukhnevich.carsharing.carsharing.model.entity.user.UserDetail;
import by.yukhnevich.carsharing.carsharing.model.service.UserService;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;
import by.yukhnevich.carsharing.carsharing.validation.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final UserDao USER_DAO = DaoHelper.getInstance().getUserDao();
    private static final UserValidator VALIDATOR = new UserValidator();

    @Override
    public Optional<User> getById(Integer id) throws ServiceException {
        try {
            return USER_DAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException, InvalidDataException {
        if (!VALIDATOR.isValidEmail(email) || !VALIDATOR.isValidPassword(password)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            password = DigestUtils.md5Hex(password);
            return USER_DAO.findUserByEmailAndPassword(email, password);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void registerUser(String email, String password, UserDetail details, Passport passport) throws ServiceException, InvalidDataException {
        if (!VALIDATOR.isValidEmail(email) || !VALIDATOR.isValidPassword(password)) {
            throw new InvalidDataException(VALIDATOR.getMessage());
        }

        try {
            password = DigestUtils.md5Hex(password);
            USER_DAO.registerUser(email, password, details, passport);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
