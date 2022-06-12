package dao;

import dao.validation.DecimalValidation;
import dao.validation.iValidate;

public class InputComponent {
    private iValidate validate;
    private String data;

    public void setData(String data) {
        this.data = data;
    }

    public void setValidate(iValidate validate) {
        this.validate = validate;
    }

    public iValidate getValidate() {
        return this.validate;
    }

    void display(){
        System.out.println(data);
    }

    boolean validate(){
        return validate.validate(this.data);
    }
}
