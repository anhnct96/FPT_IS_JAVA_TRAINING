package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception;

import javax.validation.constraints.NotEmpty;

public class DuplicatedStorageNameException extends RuntimeException{
    public DuplicatedStorageNameException(@NotEmpty(message = "No storage name") String name) {
        super("This storage name = " + name + " is duplicated, please change!");
    }
}
