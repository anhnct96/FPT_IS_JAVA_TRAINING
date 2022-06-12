package dao.validation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DecimalValidation implements iValidate{

    @Override
    public boolean validate(String data) {
        String rgx = "^\\d*\\.?\\d+$";
        if (data.matches(rgx)) {
            return true;
        }
        else {
            return false;
        }
    }
}
