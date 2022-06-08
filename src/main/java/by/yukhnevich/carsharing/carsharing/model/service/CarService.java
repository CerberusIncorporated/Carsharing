package by.yukhnevich.carsharing.carsharing.model.service;

import by.yukhnevich.carsharing.carsharing.model.entity.car.Car;
import by.yukhnevich.carsharing.carsharing.model.entity.car.CarClass;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> getById(int id) throws ServiceException;

    List<Car> getAll() throws ServiceException;

    List<Car> getCarsByName(String criteria) throws ServiceException;

    List<Car> getCarsByYear(String year) throws ServiceException;

    List<Car> getCarsByClass(CarClass carClass) throws ServiceException;

    void update(Car entity) throws ServiceException, InvalidDataException;

    void add(Car car) throws ServiceException, InvalidDataException;

    void deleteById(int id) throws ServiceException;
}
