package org.chinh.recyclerview;

public class Student {

    private int id;
    private String hoTen;
    private String diaChi;
    private String soDienThoai;

    public Student(int id, String hoTen, String diaChi, String soDienThoai) {
        this.id = id;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public int getId() {
        return id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }}
