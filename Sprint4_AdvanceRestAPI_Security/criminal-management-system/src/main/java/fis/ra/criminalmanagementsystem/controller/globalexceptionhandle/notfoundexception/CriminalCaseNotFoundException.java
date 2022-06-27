package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception;

public class CriminalCaseNotFoundException extends RuntimeException {
    public CriminalCaseNotFoundException(Long id) {
        super("Could not find criminal case with id = " + id);
    }

    public CriminalCaseNotFoundException(String number) {
        super("Could not find criminal case with case number = " + number);
    }
}
