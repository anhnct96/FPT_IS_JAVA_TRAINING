package com.example;

import java.util.Objects;

public class MonHoc implements Cloneable, Comparable{
    private String ten;
    private int tcLT;
    private int tcTH;

    public MonHoc(String ten, int tcLT, int tcTH) {
        this.ten = ten;
        this.tcLT = tcLT;
        this.tcTH = tcTH;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTcLT() {
        return tcLT;
    }

    public void setTcLT(int tcLT) {
        this.tcLT = tcLT;
    }

    public int getTcTH() {
        return tcTH;
    }

    public void setTcTH(int tcTH) {
        this.tcTH = tcTH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonHoc monHoc = (MonHoc) o;
        return ten.equals(monHoc.ten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ten, tcLT, tcTH);
    }

    @Override
    protected MonHoc clone() throws CloneNotSupportedException {
        return (MonHoc)super.clone();
    }

    @Override
    public int compareTo(Object o) {
        MonHoc monHoc = (MonHoc) o;
        if (this.ten.compareTo(monHoc.ten) > 0) {
            return 1;
        }
        if (this.ten.compareTo(monHoc.ten) < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "ten='" + ten + '\'' +
                ", tcLT=" + tcLT +
                ", tcTH=" + tcTH +
                '}';
    }
}
