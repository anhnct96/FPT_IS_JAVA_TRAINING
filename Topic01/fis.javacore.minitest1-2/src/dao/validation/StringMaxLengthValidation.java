package dao.validation;

public class StringMaxLengthValidation implements iValidate{
    int length;

    @Override
    public boolean validate(String data) {
        if (data.length() <= length) {
            return true;
        }
        return false;
    }
}
