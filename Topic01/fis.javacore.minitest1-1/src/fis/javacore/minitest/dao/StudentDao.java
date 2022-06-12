package fis.javacore.minitest.dao;

import fis.javacore.minitest.core.Student;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentDao implements iStudentDao{
    private ArrayList<Student> studentList;
    private static final int MAX = 100;
    private int count;
    private ISortStrategy sortStrategy;

    public StudentDao() {
        this.count = 0;
        studentList = new ArrayList<Student>();
        this.sortStrategy = new SelectionSortStrategy();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public void addStudent(Student student) {
        this.studentList.add(student);
    }

    @Override
    public Student remove(int code) {
        int pos = 0;
        for (Student student: studentList) {
            if (student.getCode() == code) {
                pos = studentList.indexOf(student);
            }
        }
        return this.studentList.remove(pos);
    }

    @Override
    public void display() {
        for (Student student: studentList) {
            System.out.println(student.toString());
        }
    }

    @Override
    public void setSortStrategy(ISortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public ISortStrategy getSortStrategy() {
        return sortStrategy;
    }

    @Override
    public void sort() {
        Comparable[] tmp = (Comparable[]) studentList.toArray(new Student[studentList.size()]);
        sortStrategy.sort(tmp, count);
        studentList = new ArrayList(Arrays.asList(tmp));
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }
}
