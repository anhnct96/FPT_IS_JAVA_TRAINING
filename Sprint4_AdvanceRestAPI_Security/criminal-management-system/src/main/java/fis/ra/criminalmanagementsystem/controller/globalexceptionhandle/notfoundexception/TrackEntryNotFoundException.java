package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception;

public class TrackEntryNotFoundException extends RuntimeException{
    public TrackEntryNotFoundException(Long id) {
        super("Could not find track entry with id = " + id);
    }
}
