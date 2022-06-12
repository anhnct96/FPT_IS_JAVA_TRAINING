package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinhVienTest {

    @Test
    void tinhDiemTrungBinh() {
        //arrangement
        SinhVien sv = new SinhVien("1", "Ha");
        Diem diem1 = new Diem (new MonHoc("Toan",1,1),10);
        Diem diem2 = new Diem (new MonHoc("Ly",4,4),5);
        sv.themDiem(diem1);
        sv.themDiem(diem2);

        //action
        double diemTB = sv.tinhDiemTrungBinh();

        //assertion
        assertEquals(6,diemTB);
    }

    @Test
    void bangDiem() {
        //arrangement
        SinhVien sv = new SinhVien("1", "Ha");
        Diem diem1 = new Diem (new MonHoc("Toan",1,1),10);
        Diem diem2 = new Diem (new MonHoc("Ly",4,4),5);
        sv.themDiem(diem1);
        sv.themDiem(diem2);

        //action + assertion
        System.out.println(sv.bangDiem());


    }

    @Test
    void xepLoai() {
        //arrangement
        SinhVien sv = new SinhVien("1", "Ha");
        Diem diem1 = new Diem (new MonHoc("Toan",1,1),10);
        Diem diem2 = new Diem (new MonHoc("Ly",4,4),5);
        sv.themDiem(diem1);
        sv.themDiem(diem2);

        //action
        String xepLoai = sv.xepLoai();

        //assertion
        assertEquals("TB-KHA", xepLoai);
    }
}