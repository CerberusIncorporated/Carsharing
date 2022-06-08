package by.yukhnevich.carsharing.carsharing.model.service;

import by.yukhnevich.carsharing.carsharing.model.entity.car.CarComment;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;

import java.util.List;

public interface CarCommentService {
    List<CarComment> getAll() throws ServiceException;

    List<CarComment> getAllByCarId(int carId) throws ServiceException;

    void add(CarComment entity) throws ServiceException, InvalidDataException;

    void deleteById(int id) throws ServiceException;

    int getDataAmount(int carId) throws ServiceException;

    List<CarComment> getCommentsForPage(int carId, int recordsPerPage, int currentPage) throws ServiceException;
}
