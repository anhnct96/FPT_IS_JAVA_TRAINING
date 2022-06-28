package vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("Can not find customer with id = " + customerId);
    }
}
