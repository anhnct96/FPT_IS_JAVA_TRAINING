package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle;

import java.sql.SQLIntegrityConstraintViolationException;

public class LeadingCriminalCaseDetectiveException extends SQLIntegrityConstraintViolationException {
    public LeadingCriminalCaseDetectiveException(Long id) {
        super("The detective with id = "+ id + " is leading a case, can not delete!");
    }
}
