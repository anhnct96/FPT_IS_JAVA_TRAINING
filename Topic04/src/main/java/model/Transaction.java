package model;

import java.util.Date;

public class Transaction {
    private TransactionType type;
    private String bankAccount;
    private Double amount;
    private String message;
    private String timeTransaction;

    public Transaction() {

    };

    public Transaction(TransactionType type, String bankAccount, Double amount, String message, String timeTransaction) {
        this.type = type;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.message = message;
        this.timeTransaction = timeTransaction;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeTransaction() {
        return timeTransaction;
    }

    public void setTimeTransaction(String timeTransaction) {
        this.timeTransaction = timeTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", bankAccount='" + bankAccount + '\'' +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                ", timeTransaction=" + timeTransaction +
                '}';
    }
}
