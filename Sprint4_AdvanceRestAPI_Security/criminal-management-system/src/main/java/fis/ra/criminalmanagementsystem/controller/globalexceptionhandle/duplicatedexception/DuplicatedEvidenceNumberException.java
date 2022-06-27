package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception;

import javax.validation.constraints.NotEmpty;

public class DuplicatedEvidenceNumberException extends RuntimeException {
    public DuplicatedEvidenceNumberException(@NotEmpty(message = "No evidence number") String number) {
        super("This evidence number = " + number + " is duplicated, please change!");
    }
}
