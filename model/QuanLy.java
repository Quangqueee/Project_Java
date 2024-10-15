package model;

public class QuanLy extends NhanVien {
    public QuanLy(String ten, int tuoi, String queQuan, String ngaySinh,
            String cccd, String sdt, double heSoLuong) {
        super(ten, tuoi, queQuan, ngaySinh, cccd, sdt, heSoLuong);
    }

    @Override
    public double tinhLuong() {
        return heSoLuong * 10000000; // Hệ số lương * 10 triệu
    }
}
