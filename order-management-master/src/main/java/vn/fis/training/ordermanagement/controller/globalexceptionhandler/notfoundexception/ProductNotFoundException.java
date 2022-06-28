package vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Can not find product with id = " + productId);
    }
}
