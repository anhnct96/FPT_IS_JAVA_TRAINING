package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonHocTest {

    @Test
    void testEquals() {
        // arrangement
        MonHoc toan = new MonHoc("Toan", 1, 2);
        MonHoc toanNangCao = new MonHoc("Toan",2,2);

        // action
        boolean testEquals = toan.equals(toanNangCao);

        // assertion
        assertEquals(true, testEquals);
    }

    @Test
    void testHashCode() {
        // arrangement
        MonHoc toan = new MonHoc("Toan", 1, 2);
        MonHoc toanNangCao = new MonHoc("Toan",1,1);

        // action
        boolean testHashCode = toan.hashCode() == toanNangCao.hashCode();

        // assertion
        assertEquals(false, testHashCode);
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        // arrangement
        MonHoc toan = new MonHoc("Toan", 1, 2);
        MonHoc toanClone = toan.clone();

        // action
        boolean testTCLT = toan.getTcLT() == toanClone.getTcLT();
        boolean testTCTH = toan.getTcTH() == toanClone.getTcTH();

        // assertion
        assertEquals(true, testTCLT);
        assertEquals(true, testTCTH);

        // action
        toan.setTcLT(5);
        toan.setTcTH(10);
        toan.setTen("Toan lop 3");
        boolean testChangedTCTH = toan.getTcTH() == toanClone.getTcTH();
        boolean testChangedTCLT = toan.getTcLT() == toanClone.getTcLT();
        boolean testChangedTen = toan.getTen() == toanClone.getTen();

        // assertion
        assertEquals(false, testChangedTCTH);
        assertEquals(false, testChangedTCLT);
        assertEquals(false, testChangedTen);
    }

    @Test
    void compareTo() {
        // Arrangement
        MonHoc monToan = new MonHoc("Toan",2,3);
        MonHoc monLy = new MonHoc("Ly",2,4);
        MonHoc monToanNangCao = new MonHoc("Toan",4,4);

        // action
        int isMonToanMonLy = monToan.compareTo(monLy);
        int isMonToanNangCaoMonToan = monToanNangCao.compareTo(monToan);

        // Assertion
        assertEquals(1, isMonToanMonLy);
        assertEquals(0, isMonToanNangCaoMonToan);
    }
}