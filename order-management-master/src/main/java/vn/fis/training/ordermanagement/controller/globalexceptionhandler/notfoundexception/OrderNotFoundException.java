package vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super("Can not find customer with id = " + orderId);
    }
}
