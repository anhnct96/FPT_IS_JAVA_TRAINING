package exception.invalid_exception;

import exception.ApplicationException;

public class InvalidDetective extends ApplicationException {
    public InvalidDetective(String message) {
        super(message);
    }
}
