package service;

import model.Phong;
import java.util.ArrayList;

public class QuanLyPhong {
    private ArrayList<Phong> DanhSachPhong;

    public QuanLyPhong() {
        DanhSachPhong = new ArrayList<>();
        DanhSachPhong.add(new Phong("101", "Phong don", "Trong"));
        DanhSachPhong.add(new Phong("102", "Phong doi", "Trong"));
        DanhSachPhong.add(new Phong("103", "Phong Vip", "Trong"));
        DanhSachPhong.add(new Phong("104", "Phong don", "Trong"));
        DanhSachPhong.add(new Phong("105", "Phong Vip", "Trong"));
    }

    public void TrangThaiPhong() {
        for (int i = 0; i < DanhSachPhong.size(); i++) {
            Phong phong = DanhSachPhong.get(i);
            System.out.println("Phong: " + phong.getSoPhong() + " - Loai phong: " + phong.getLoaiPhong()
                    + " - Trang thai: " + phong.getTrangThai());
        }
    }

    public Phong TimPhong(String SoPhong) {
        for (int i = 0; i < DanhSachPhong.size(); i++) {
            Phong phong = DanhSachPhong.get(i);
            if (phong.getSoPhong().equals(SoPhong)) {
                return phong;
            }
        }
        return null;
    }

    public void SuaPhong(String SoPhong, String LoaiPhongMoi) {
        Phong phong = TimPhong(SoPhong);
        if (phong != null) {
            phong.setLoaiPhong(LoaiPhongMoi);
            System.out.println("Cap nhat loai phong thanh cong.");
        } else {
            System.out.println("Phong khong ton tai.");
        }
    }
}
