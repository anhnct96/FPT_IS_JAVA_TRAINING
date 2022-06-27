package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception;

public class StorageNotFoundException extends RuntimeException {
    public StorageNotFoundException(Long id) {
        super("Could not find detective with id = " + id);
    }
}
