package by.yukhnevich.carsharing.carsharing.model.service;

import by.yukhnevich.carsharing.carsharing.model.entity.Payment;
import by.yukhnevich.carsharing.carsharing.model.service.exception.ServiceException;

public interface PaymentService {
    void add(Payment entity) throws ServiceException;
}
