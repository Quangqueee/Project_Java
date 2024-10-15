package model;

public abstract class NhanVien {
    protected String ten;
    protected int tuoi;
    protected String queQuan;
    protected String ngaySinh;
    protected String cccd;
    protected String sdt;
    protected double heSoLuong;

    public NhanVien(String ten, int tuoi, String queQuan, String ngaySinh,
            String cccd, String sdt, double heSoLuong) {
        this.ten = ten;
        this.tuoi = tuoi;
        this.queQuan = queQuan;
        this.ngaySinh = ngaySinh;
        this.cccd = cccd;
        this.sdt = sdt;
        this.heSoLuong = heSoLuong;
    }

    public String getTen() {
        return ten;
    }

    public String getCCCD() {
        return cccd;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public int getTuoi() {
        return tuoi;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getSDT() {
        return sdt;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    // Phương thức tính lương trừu tượng (sẽ được lớp con triển khai)
    public abstract double tinhLuong();

    @Override
    public String toString() {
        return "Nhan vien: " + ten + ", Tuoi: " + tuoi + ", Que quan: " + queQuan +
                ", Ngay sinh: " + ngaySinh + ", CCCD: " + cccd + ", SDT: " + sdt +
                ", He so luong: " + heSoLuong;
    }
}
