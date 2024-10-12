package model;

public class DichVu {
    private String TenDichVu;
    private double Gia;

    public DichVu(String TenDichVu, double Gia) {
        this.TenDichVu = TenDichVu;
        this.Gia = Gia;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String TenDichVu) {
        this.TenDichVu = TenDichVu;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        this.Gia = gia;
    }
}
