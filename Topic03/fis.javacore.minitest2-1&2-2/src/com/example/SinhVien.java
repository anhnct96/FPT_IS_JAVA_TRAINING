package com.example;

import java.util.Formatter;
import java.util.*;

public class SinhVien {
    private String mssv;
    private String ten;

    private Set<Diem> monDaHoc = new HashSet<Diem>();

    public SinhVien(String mssv, String ten) {
        this.mssv = mssv;
        this.ten = ten;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Set<Diem> getMonDaHoc() {
        return monDaHoc;
    }

    public void setMonDaHoc(Set<Diem> monDaHoc) {
        this.monDaHoc = monDaHoc;
    }

    public boolean themDiem(Diem diemMoi) {
        return this.monDaHoc.add(diemMoi);
    }

    //TODO Cau 1
    public double tinhDiemTrungBinh() {
        double tongDiem = 0.0;
        double tongTinChi = 0.0;
        double diemTB = 0.0;
        double tong = monDaHoc.stream().mapToDouble(Diem::getDiem).sum();
        for (Diem diem : monDaHoc) {
            tongTinChi += diem.getMon().getTcLT() + diem.getMon().getTcTH();
            tongDiem += diem.getDiem() * (diem.getMon().getTcLT() + diem.getMon().getTcTH());
        }
        diemTB = tongDiem / tongTinChi;
        return diemTB;

    }


    //TODO Cau 2
    public String bangDiem() {
    /*
     MSSV : 0203044
     Ten  : Nguyen Van A
     Danh Sach Diem
     STT  Ten Mon             Diem       So Tin Chi
     1    Cau Truc Du Lieu 1  8          3
     2    Cau Truc Du Lieu 2  8          3
     Diem Trung Binh   8.0
    */
        //...
        //StringBuilder
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        sb.append("MSSV :"+this.mssv+"\n");
        sb.append("Ten  :"+this.ten+"\n");
        sb.append("Danh Sach Diem"+"\n");
        formatter.format("%s%5s%17s%17s\n","STT","Ten Mon","Diem","So Tin Chi");
        int stt = 0;
        for (Diem diem: monDaHoc) {
            stt += 1;
            formatter.format("%d%5s%17d%17d\n", stt,diem.getMon().getTen(),diem.getDiem(),(diem.getMon().getTcLT()+diem.getMon().getTcTH()));
        }
        sb.append("Diem Trung Binh  "+this.tinhDiemTrungBinh()+"\n");
        return sb.toString();
    }


    //TODO Cau 3
    public String xepLoai() {
    /*
    Quy tac xep loai nhu sau
        DiemTB < 5 -> YEU
        DiemTB >= 5 va DiemTB < 6 -> TB
        DiemTB >= 6 va DiemTB < 7 -> TB-KHA
        DiemTB >= 7 va DiemTB < 8 -> KHA
        DiemTB >= 8 -> GIOI
    */

        //...
        double diemTB = this.tinhDiemTrungBinh();
        String xepLoai = "";
        if (diemTB < 5) {
            xepLoai = "YEU";
        }
        if (diemTB >= 5 && diemTB < 6) {
            xepLoai = "TB";
        }
        if (diemTB >= 6 && diemTB < 7) {
            xepLoai = "TB-KHA";
        }
        if (diemTB >= 7 && diemTB < 8) {
            xepLoai = "KHA";
        }
        if (diemTB >= 8) {
            xepLoai = "GIOI";
        }
        return xepLoai;
    }


}
