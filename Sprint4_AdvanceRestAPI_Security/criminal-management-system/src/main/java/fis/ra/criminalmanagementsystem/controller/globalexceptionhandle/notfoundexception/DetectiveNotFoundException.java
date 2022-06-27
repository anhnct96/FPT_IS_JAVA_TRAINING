package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception;

public class DetectiveNotFoundException extends RuntimeException {
    public DetectiveNotFoundException(Long id) {
        super("Could not find detective with id = " + id);
    }

    public DetectiveNotFoundException(String username) {
        super("Could not find detective with username = " + username);
    }
}
