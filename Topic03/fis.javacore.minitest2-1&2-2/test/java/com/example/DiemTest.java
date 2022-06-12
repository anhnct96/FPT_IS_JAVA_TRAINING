package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiemTest {

    DiemTest() throws CloneNotSupportedException {

    }

    @Test void testDiemConstructor() {
        try {
            Diem diem = new Diem(new MonHoc("Toan",1,1), -1);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void testEquals() {
        // arrangement
        Diem thisDiem = new Diem(new MonHoc("Hoa",1,1),1);
        Diem thatDiem = new Diem(new MonHoc("Hoa",1,1),2);

        // action
        boolean equalsTest = thisDiem.equals(thatDiem);

        // assertion
        assertTrue(equalsTest);
    }

    @Test
    void compareTo() {
        // arrangement
        Diem thisDiem = new Diem(new MonHoc("Hoa",1,1),8);
        Diem thatDiem = new Diem(new MonHoc("Ly",1,3),8);

        // action
        int compareToTest = thatDiem.compareTo(thisDiem);

        // assertion
        assertEquals(0,compareToTest);
    }

    @Test
    void testHashCode() {
        // arrangement
        Diem thisDiem = new Diem(new MonHoc("Hoa",1,1),8);
        Diem thatDiem = new Diem(new MonHoc("Ly",1,3),8);

        // action
        boolean testHashCode = thisDiem.hashCode() == thatDiem.hashCode();

        // assertion
        assertEquals(false, testHashCode);
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        // arrangement
        Diem thisDiem = new Diem(new MonHoc("Hoa",1,1),1);
        Diem diemClone = thisDiem.clone();

        // action
        boolean testDiemBetweenThisDiemAndItsClone = thisDiem.getDiem() == diemClone.getDiem();
        boolean testMonHocBetweenThisDiemAndItsClone = thisDiem.getMon().equals(diemClone.getMon());

        // arrangement
        assertEquals(true, testDiemBetweenThisDiemAndItsClone);
        assertEquals(true, testMonHocBetweenThisDiemAndItsClone);

        //action: Change elements of thisDiem
        thisDiem.setDiem(10);
        thisDiem.setMon(new MonHoc("Toan",2,2));
        boolean testDiem = thisDiem.getDiem() == diemClone.getDiem();
        boolean testMonHoc = thisDiem.getMon() == diemClone.getMon();
        // assert
        assertEquals(false, testDiem);
        assertEquals(false, testMonHoc);
    }
}