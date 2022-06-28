package vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception;

import vn.fis.training.ordermanagement.model.OrderStatus;

import javax.validation.constraints.NotNull;

public class PermissionDeniedByStatusException extends RuntimeException {
    public PermissionDeniedByStatusException(@NotNull(message = "status must not be null") OrderStatus status) {
        super("Can not take action with status = " + status + " .Permission denied");
    }
}
