package com.example;

import java.io.Serializable;

public class Diem implements Comparable<Diem>, Serializable, Cloneable {
    private MonHoc mon;
    private int diem;

    public Diem(MonHoc mon, int diem) {
        this.mon = mon;
        this.diem = diem;
    }

    public MonHoc getMon() {
        return mon;
    }

    public void setMon(MonHoc mon) {
        this.mon = mon;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diem diem1 = (Diem) o;
        return diem == diem1.diem;
    }

    @Override
    public int compareTo(Diem o) {
        if (this.diem > o.diem) return 1;
        else if (this.diem < o.diem) return -1;
        else return 0;
    }

    @Override
    public int hashCode() {
        int result = mon.hashCode();
        result = 31 * result + diem;
        return result;
    }

    @Override
    public Diem clone() throws CloneNotSupportedException {
        return (Diem)super.clone();
    }
}
