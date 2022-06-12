package core;

import fis.javacore.minitest.core.Student;
import  org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getCode() {
        Student student = new Student(1, "Nguyen Thanh Phuoc", new Date());
        assertEquals(1, student.getCode());

    }

    @Test
    void setCode() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getBirthDate() {
    }

    @Test
    void setBirthDate() {
    }

    @Test
    void testToString() {
    }

    @Test
    void compareTo() {
    }
}