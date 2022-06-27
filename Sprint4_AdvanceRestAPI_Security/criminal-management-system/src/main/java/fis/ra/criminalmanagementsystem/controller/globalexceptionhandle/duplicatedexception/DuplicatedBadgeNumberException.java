package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception;

import javax.validation.constraints.NotEmpty;

public class DuplicatedBadgeNumberException extends RuntimeException {
    public DuplicatedBadgeNumberException(@NotEmpty(message = "No badge number") String badgeNumber) {
        super("This badge number = " + badgeNumber + " is duplicated, please change!");
    }
}
