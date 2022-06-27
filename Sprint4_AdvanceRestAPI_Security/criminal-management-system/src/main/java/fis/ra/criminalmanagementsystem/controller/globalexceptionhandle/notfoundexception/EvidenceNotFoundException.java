package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception;

public class EvidenceNotFoundException extends RuntimeException{
    public EvidenceNotFoundException(Long id) {
        super("Could not find evidence with id = " + id);
    }
}
