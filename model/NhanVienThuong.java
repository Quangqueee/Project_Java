package model;

public class NhanVienThuong extends NhanVien {
    private String boPhanLamViec;

    public NhanVienThuong(String ten, int tuoi, String queQuan, String ngaySinh,
            String cccd, String sdt, double heSoLuong, String boPhanLamViec) {
        super(ten, tuoi, queQuan, ngaySinh, cccd, sdt, heSoLuong);
        this.boPhanLamViec = boPhanLamViec;
    }

    public String getBoPhanLamViec() {
        return boPhanLamViec;
    }

    @Override
    public double tinhLuong() {
        return heSoLuong * 5000000; // Hệ số lương * 5 triệu
    }

    @Override
    public String toString() {
        return super.toString() + ", Bo phan: " + boPhanLamViec;
    }
}
