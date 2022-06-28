package vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception;

public class DuplicatedMobileException extends RuntimeException {
    public DuplicatedMobileException(String mobile) {
        super("This mobile has already assigned, please change");
    }
}
