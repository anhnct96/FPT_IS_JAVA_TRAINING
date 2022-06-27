package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception;

import javax.validation.constraints.NotEmpty;

public class DuplicatedCriminalCaseNumberException extends RuntimeException {
    public DuplicatedCriminalCaseNumberException(@NotEmpty(message = "No case number") String number) {
        super("This criminal case number = " + number + " is duplicated, please change!");
    }
}
