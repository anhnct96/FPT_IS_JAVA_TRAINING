package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception;

public class DuplicatedUsernameException extends RuntimeException {
    public DuplicatedUsernameException(String username) {
        super("This username = " + username + " is duplicated, please change!");
    }
}
