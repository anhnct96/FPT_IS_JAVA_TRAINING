package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LopHocTest {

    @Test
    void inDiem() {
        // arrangement
        LopHoc lh = new LopHoc("OOP","Ha");
        SinhVien sv1 = new SinhVien("1", "Ha");
        Diem diem1sv1 = new Diem (new MonHoc("Toan",1,1),10);
        Diem diem2sv1 = new Diem (new MonHoc("Ly",4,4),5);
        sv1.themDiem(diem1sv1);
        sv1.themDiem(diem2sv1);
        SinhVien sv2 = new SinhVien("2", "Dat");
        Diem diem1sv2 = new Diem (new MonHoc("Toan",1,1),4);
        Diem diem2sv2 = new Diem (new MonHoc("Ly",4,4),3);
        sv2.themDiem(diem1sv2);
        sv2.themDiem(diem2sv2);
        lh.them(sv1);
        lh.them(sv2);

        // action + "assertion"
        System.out.println(lh.inDiem());
    }

    @Test
    void top10() {
        // arrangement
        LopHoc lh = new LopHoc("OOP","Ha");
        SinhVien sv1 = new SinhVien("1", "Ha");
        Diem diem1sv1 = new Diem (new MonHoc("Toan",1,1),10);
        Diem diem2sv1 = new Diem (new MonHoc("Ly",4,4),5);
        sv1.themDiem(diem1sv1);
        sv1.themDiem(diem2sv1);
        SinhVien sv2 = new SinhVien("2", "Dat");
        Diem diem1sv2 = new Diem (new MonHoc("Toan",1,1),4);
        Diem diem2sv2 = new Diem (new MonHoc("Ly",4,4),3);
        sv2.themDiem(diem1sv2);
        sv2.themDiem(diem2sv2);
        lh.them(sv1);
        lh.them(sv2);

        // action + assertion
        for (SinhVien sv: lh.getDsLop()) {
            System.out.println(sv.tinhDiemTrungBinh());
        }
    }

    @Test
    void sinhVienYeu() {
        //arrangement
        LopHoc lh = new LopHoc("OOP","Ha");
        SinhVien sv1 = new SinhVien("1", "Ha");
        Diem diem1sv1 = new Diem (new MonHoc("Toan",1,1),10);
        Diem diem2sv1 = new Diem (new MonHoc("Ly",4,4),5);
        sv1.themDiem(diem1sv1);
        sv1.themDiem(diem2sv1);
        SinhVien sv2 = new SinhVien("2", "Dat");
        Diem diem1sv2 = new Diem (new MonHoc("Toan",1,1),4);
        Diem diem2sv2 = new Diem (new MonHoc("Ly",4,4),3);
        sv2.themDiem(diem1sv2);
        sv2.themDiem(diem2sv2);
        lh.them(sv1);
        lh.them(sv2);

        // action + assertion
        for (SinhVien sv:lh.sinhVienYeu()){
            System.out.println(sv);
        }
    }
}