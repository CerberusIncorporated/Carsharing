package by.yukhnevich.carsharing.carsharing.model.service.exception;

public class ServiceException extends Exception{
    public ServiceException() {
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
