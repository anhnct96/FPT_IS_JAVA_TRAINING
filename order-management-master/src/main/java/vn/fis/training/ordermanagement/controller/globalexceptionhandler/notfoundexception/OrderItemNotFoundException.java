package vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(Long orderItemId) {
        super("Can not find order item with id = " + orderItemId);
    }
}
