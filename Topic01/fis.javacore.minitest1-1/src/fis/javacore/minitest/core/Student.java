package fis.javacore.minitest.core;

import java.util.Date;

public class Student implements Comparable<Student> {
    private int code;
    private String name;
    private Date birthDate;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Student() {

    }

    public Student(int code, String name, Date birthDate) {
        this.code = code;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (code != student.code) return false;
        if (!name.equals(student.name)) return false;
        return birthDate.equals(student.birthDate);
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + name.hashCode();
        result = 31 * result + birthDate.hashCode();
        return result;
    }

    @Override
    public int compareTo(Student o) {
        if (this.code < o.code) {
            return 1;
        }
        else if (this.code > o.code) {
            return -1;
        }
        else return 0;
    }
}
