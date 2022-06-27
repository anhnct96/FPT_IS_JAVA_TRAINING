package fis.ra.criminalmanagementsystem.controller.globalexceptionhandle;

import fis.ra.criminalmanagementsystem.constant.ExceptionConstant;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.duplicatedexception.*;
import fis.ra.criminalmanagementsystem.controller.globalexceptionhandle.notfoundexception.*;
import lombok.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseBody
    @ExceptionHandler(DetectiveNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorMessage> detectiveNotFoundException(DetectiveNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code(ExceptionConstant.DETECTIVE_NOT_FOUND).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(CriminalCaseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorMessage> criminalCaseNotFoundException(CriminalCaseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code(ExceptionConstant.CRIMINAL_CASE_NOT_FOUND).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(StorageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorMessage> storageNotFoundException(StorageNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code(ExceptionConstant.STORAGE_NOT_FOUND).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(EvidenceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorMessage> evidenceNotFoundException(EvidenceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code(ExceptionConstant.EVIDENCE_NOT_FOUND).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(TrackEntryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorMessage> trackEntryNotFoundException(TrackEntryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.builder().code(ExceptionConstant.TRACK_ENTRY_NOT_FOUND).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(LeadingCriminalCaseDetectiveException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    ResponseEntity<ErrorMessage> leadingCriminalCaseDetectiveException(LeadingCriminalCaseDetectiveException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ErrorMessage.builder().code(ExceptionConstant.DELETE_LEADING_CRIMINAL_CASE_DETECTIVE_EXCEPTION).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler({
            DuplicatedUsernameException.class,
            DuplicatedBadgeNumberException.class,
            DuplicatedCriminalCaseNumberException.class,
            DuplicatedStorageNameException.class,
            DuplicatedEvidenceNumberException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorMessage> duplicatedException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.builder().code(ExceptionConstant.FIELDS_DUPLICATED).message(ex.getMessage()).build());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ErrorMessage {
        private String code;
        private String message;
    }
}
