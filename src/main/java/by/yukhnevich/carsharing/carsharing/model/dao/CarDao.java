package by.yukhnevich.carsharing.carsharing.model.dao;

import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.car.Car;
import by.yukhnevich.carsharing.carsharing.model.entity.car.CarClass;

import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> getCarsByName(String criteria) throws DaoException;

    List<Car> getCarsByYear(String year) throws DaoException;

    List<Car> getCarsByClass(CarClass carClass) throws DaoException;

    void update(Car entity) throws DaoException;
}
