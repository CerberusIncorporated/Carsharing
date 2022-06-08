package by.yukhnevich.carsharing.carsharing.model.dao;

import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.News;

public interface NewsDao extends Dao<News> {
    void update(News entity) throws DaoException;
}
