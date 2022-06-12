package exception.invalid_exception;

import exception.ApplicationException;
import service.ApplicationConstant;

public class InvalidPersonException extends ApplicationException {
    public InvalidPersonException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return ApplicationConstant.INVALID_PERSON;
    }

}
