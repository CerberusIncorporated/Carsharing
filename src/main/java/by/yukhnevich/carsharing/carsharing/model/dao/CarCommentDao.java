package by.yukhnevich.carsharing.carsharing.model.dao;

import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.car.CarComment;

import java.util.List;

public interface CarCommentDao extends Dao<CarComment> {
    List<CarComment> getAllByCarId(int carId) throws DaoException;

    int getDataAmount(int carId) throws DaoException;
    List<CarComment> getCommentsForPage(int carId, int limit, int offset) throws DaoException;
}
