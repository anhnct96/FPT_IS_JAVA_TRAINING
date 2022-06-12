package exception.not_found_exception;

import exception.ApplicationException;
import service.ApplicationConstant;

public class PersonNotFoundException extends ApplicationException {
    public PersonNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return ApplicationConstant.NOT_FOUND_PERSON;
    }

}
