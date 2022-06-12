package exception.not_found_exception;

import exception.ApplicationException;
import service.ApplicationConstant;

public class DetectiveNotFoundException extends ApplicationException {
    public DetectiveNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return ApplicationConstant.NOT_FOUND_DETECTIVE;
    }
}
