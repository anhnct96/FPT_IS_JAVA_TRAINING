package exception.not_found_exception;

import exception.ApplicationException;
import service.ApplicationConstant;

public class TrackEntryNotFoundException extends ApplicationException {
    public TrackEntryNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return ApplicationConstant.NOT_FOUND_TRACK_ENTRY;
    }

}
