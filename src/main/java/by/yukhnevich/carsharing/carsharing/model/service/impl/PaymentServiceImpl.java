package by.yukhnevich.carsharing.carsharing.model.service.impl;

import by.yukhnevich.carsharing.carsharing.model.dao.DaoHelper;
import by.yukhnevich.carsharing.carsharing.model.dao.PaymentDao;
import by.yukhnevich.carsharing.carsharing.model.dao.exception.DaoException;
import by.yukhnevich.carsharing.carsharing.model.entity.Payment;
import by.yukhnevich.carsharing.carsharing.model.service.PaymentService;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;

public class PaymentServiceImpl implements PaymentService {
    private static final PaymentDao paymentDao = DaoHelper.getInstance().getPaymentDao();

    @Override
    public void add(Payment entity) throws ServiceException {
        try {
            paymentDao.add(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
