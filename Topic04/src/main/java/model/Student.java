package model;

public class Student {
    private String name;
    private String bankAccount;


    public Student(String name, String bankAccount) {
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }
}
