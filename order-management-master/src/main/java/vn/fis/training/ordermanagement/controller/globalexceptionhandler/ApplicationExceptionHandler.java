package vn.fis.training.ordermanagement.controller.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.CustomerNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.OrderItemNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.OrderNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.ProductNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception.DuplicatedMobileException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception.PermissionDeniedByStatusException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception.ProductQuantityNotEnoughException;

import java.util.HashMap;
import java.util.Map;

import static vn.fis.training.ordermanagement.constant.Constant.*;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            OrderNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handleOrderNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(ORDER_NOT_FOUND_EXCEPTION).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            CustomerNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handleCustomerNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(CUSTOMER_NOT_FOUND_EXCEPTION).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            DuplicatedMobileException.class
    })
    protected ResponseEntity<ErrorMessage> handleDuplicatedMobileException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().code(DUPLICATED_MOBILE_EXCEPTION).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            ProductQuantityNotEnoughException.class
    })
    protected ResponseEntity<ErrorMessage> handleProductQuantityNotEnoughException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().code(PRODUCT_QUANTITY_NOT_ENOUGH_EXCEPTION).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            ProductNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handleProductNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(PRODUCT_NOT_FOUND_EXCEPTION).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            OrderItemNotFoundException.class
    })
    protected ResponseEntity<ErrorMessage> handleOrderItemNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().code(ORDER_ITEM_NOT_FOUND_EXCEPTION).message(exception.getMessage()).build());
    }

    @ExceptionHandler(value = {
            PermissionDeniedByStatusException.class
    })
    protected ResponseEntity<ErrorMessage> handleDeletionPermissionDeniedException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().code(PERMISSION_DENIED_BY_STATUS_EXCEPTION).message(exception.getMessage()).build());
    }
}
