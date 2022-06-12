package dao;

import model.Student;
import model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DAO {
    List<Transaction> readTransactionExcel(String excelFilePath) throws IOException;
    ArrayList<Student> fullyPaidAccount(Double tuition);
    Map<Student, Double> notFullyPaidAccount(Double tuition);
}
