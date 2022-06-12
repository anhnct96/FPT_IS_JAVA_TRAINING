package dao;

import model.Student;
import model.Transaction;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import model.TransactionType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ReadTransactionExcelDao implements DAO{

    private List<Transaction> transactionList= new ArrayList<>();
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public List<Transaction> readTransactionExcel(String excelFilePath) throws IOException {
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Transaction transaction = new Transaction();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case Column.COLUMN_TRANSACTION_TYPE:
                        transaction.setType(TransactionType.valueOf(((String) getCellValue(cell)).toUpperCase()));
                        break;
                    case Column.COLUMN_BANK_ACCOUNT:
                        transaction.setBankAccount((String) getCellValue(cell));
                        break;
                    case Column.COLUMN_AMOUNT:
                        transaction.setAmount(new BigDecimal((Double) cellValue).doubleValue());
                        break;
                    case Column.COLUMN_MESSAGE:
                        transaction.setMessage((String) getCellValue(cell));
                        break;
                    case Column.COLUMN_TRANSACTION_DATE:
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
                        transaction.setTimeTransaction(sdf.format(cell.getDateCellValue()));
                        break;
                    default:
                        break;
                }
            }
            transactionList.add(transaction);
        }

        workbook.close();
        inputStream.close();

        return transactionList;
    }

    public String splitMessage(String message) {
        String splitpMsg[] = message.split("_");
        return splitpMsg[1];
    }

    @Override
    public ArrayList<Student> fullyPaidAccount(Double tuition) {
        ArrayList<Student> studentList = new ArrayList<>();
        transactionList
                .stream()
                .filter((transaction) -> {
                        return (transaction.getType().equals(TransactionType.TRANSFER)
                                && transaction.getAmount().equals(tuition));
                    })
                .collect(Collectors.toList()).forEach(new Consumer<Transaction>() {
                    @Override
                    public void accept(Transaction transaction) {
                        studentList.add(new Student(splitMessage(transaction.getMessage()),transaction.getBankAccount()));
                    }
                });
        return studentList;
    }

    @Override
    public Map<Student, Double> notFullyPaidAccount(Double tuition) {
        Map<Student, Double> studentMap = new HashMap<>();
        transactionList
                .stream()
                .filter((transaction) -> {
                    return (transaction.getType().equals(TransactionType.TRANSFER)
                            && transaction.getAmount() < tuition);
                })
                .collect(Collectors.toList()).forEach(transaction -> {
                    studentMap.put(
                                new Student(splitMessage(transaction.getMessage()),transaction.getBankAccount()), (transaction.getAmount() - tuition));
                });
        return studentMap;
    }

    //@Override


    // Get Workbook
    private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }
        return cellValue;
    }
}
