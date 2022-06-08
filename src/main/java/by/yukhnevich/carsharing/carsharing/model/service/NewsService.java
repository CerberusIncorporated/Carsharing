package by.yukhnevich.carsharing.carsharing.model.service;

import by.yukhnevich.carsharing.carsharing.model.entity.News;
import by.yukhnevich.carsharing.carsharing.model.service.exception.InvalidDataException;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Optional<News> findNewsById(int id) throws ServiceException;

    List<News> getAll() throws ServiceException;

    void update(News entity) throws ServiceException, InvalidDataException;

    void add(News news) throws ServiceException, InvalidDataException;

    void deleteById(int id) throws ServiceException;
}
