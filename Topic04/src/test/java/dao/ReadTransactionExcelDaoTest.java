package dao;

import model.Student;
import model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ReadTransactionExcelDaoTest {


    @Test
    void readTransactionExcelTest() throws IOException {
        ReadTransactionExcelDao readTransactionExcelDao = new ReadTransactionExcelDao();
        String excelFilePath = "transaction_history.xlsx";
        readTransactionExcelDao.setTransactionList(readTransactionExcelDao.readTransactionExcel(excelFilePath));
        for (Transaction transaction : readTransactionExcelDao.getTransactionList()) {
            System.out.println(transaction);
        }
    }

    @Test
    void fullyPaidAccountTest() throws IOException {
        ReadTransactionExcelDao readTransactionExcelDao = new ReadTransactionExcelDao();
        String excelFilePath = "transaction_history.xlsx";

        readTransactionExcelDao.setTransactionList(readTransactionExcelDao.readTransactionExcel(excelFilePath));

        ArrayList<Student> x = readTransactionExcelDao.fullyPaidAccount(30000.0);
        for (Student std: x) {
            System.out.println(std);
        }
    }

    @Test
    void notFullyPaidAccountTest() throws IOException {
        ReadTransactionExcelDao readTransactionExcelDao = new ReadTransactionExcelDao();
        String excelFilePath = "transaction_history.xlsx";

        readTransactionExcelDao.setTransactionList(readTransactionExcelDao.readTransactionExcel(excelFilePath));

        Map<Student, Double> x = readTransactionExcelDao.notFullyPaidAccount(30000.0);
        for(Map.Entry<Student,Double> mp : x.entrySet()) {
            System.out.println(mp.getKey() + " owed: " + mp.getValue());
        }
    }
}