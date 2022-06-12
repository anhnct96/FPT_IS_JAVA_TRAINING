package dao.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidation implements iValidate{

    @Override
    public boolean validate(String data) {
        if (data.trim().equals("")) {
            return true;
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            try
            {
                Date date = sdf.parse(data);
            }
            catch (ParseException e)
            {
                System.out.println("Invalid date!");
                return false;
            }
            return true;
        }
    }
}
