package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class LopHoc {
    private String ten;
    private String giaoVien;
    private List<SinhVien> dsLop = new ArrayList<SinhVien>();

    public LopHoc(String ten, String giaoVien) {
        this.ten = ten;
        this.giaoVien = giaoVien;
    }

    public boolean them(SinhVien svMoi) {
        return dsLop.add(svMoi);
    }

    public List<SinhVien> getDsLop() {
        return dsLop;
    }

    public void setDsLop(List<SinhVien> dsLop) {
        this.dsLop = dsLop;
    }

    //TODO Cau 4
    public String inDiem() {
    /*
    Danh Sach Diem Lop : ten
    Giao Vien Chu Nhiem : giaoVien
    STT      MSSV        Ten              Diem TB   XepLoai
    1        123456      Nguyen Van A     8.4       GIOI
    2        678919      Nguyen Van B     6         TB-KHA
    3        112456      Nguyen Van C     7         KHA
    */
        //...
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        sb.append("Danh Sach Diem Lop :"+this.ten+"\n");
        sb.append("Giao Vien Chu Nhiem :"+this.giaoVien+"\n");
        formatter.format("%s%17s%17s%17s%17s\n","STT","MSSV","Ten","Diem TB","Xep Loai");
        int stt = 0;
        for (SinhVien sv: this.dsLop) {
            stt+=1;
            formatter.format("%s%17s%17s%17s%17s\n",stt,sv.getMssv(),sv.getTen(),sv.tinhDiemTrungBinh(),sv.xepLoai());
        }
        return sb.toString();
    }

    //TODO Cau 5
    public List<SinhVien> top10() {
        Collections.sort(dsLop,(o1, o2) ->
        {
            if (o1.tinhDiemTrungBinh() > o2.tinhDiemTrungBinh()) {
                return 1;
            }
            if (o1.tinhDiemTrungBinh() < o2.tinhDiemTrungBinh()) {
                return -1;
            } else return 0;
        });
        List<SinhVien> listTop10 = null;
        if (this.dsLop.size() > 10) {
            for (int i = 0; i < 10; i++) {
                listTop10.add(this.dsLop.get(i));
            }
        }
        else {
            listTop10 = this.dsLop;
        }
        return listTop10;
    }

    //TODO Cau 6
    public List<SinhVien> sinhVienYeu() {
        //Tra ve danh sach vien vien xep loai YEU
        //...
        List<SinhVien> listYeu = new ArrayList<>();
        listYeu = (List<SinhVien>) this.dsLop.stream().filter((sinhVien -> {
            return sinhVien.xepLoai().equals("YEU");
        })).collect(Collectors.toList());
        return listYeu;
    }
}
