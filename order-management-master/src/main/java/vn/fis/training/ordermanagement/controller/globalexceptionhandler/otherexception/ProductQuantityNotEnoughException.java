package vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception;

public class ProductQuantityNotEnoughException extends RuntimeException {
    public ProductQuantityNotEnoughException(Long productId) {
        super("This product doesn't have enough quantity to order!");
    }
}
