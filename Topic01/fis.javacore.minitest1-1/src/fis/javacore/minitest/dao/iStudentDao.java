package fis.javacore.minitest.dao;

import fis.javacore.minitest.core.Student;

public interface iStudentDao {
    void addStudent(Student student);

    Student remove(int code);

    void display();

    void sort();

    void setSortStrategy(ISortStrategy iSortStrategy);
}
