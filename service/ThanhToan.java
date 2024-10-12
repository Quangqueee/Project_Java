package service;

import model.Phong;
import model.DichVu;
import java.util.ArrayList;

public class ThanhToan {
    public double TienPhong(Phong phong, int soNgay) {
        double GiaPhong = 100000;
        if (phong.getLoaiPhong().equals("Phong don")) {
            GiaPhong = 300000;
        } else if (phong.getLoaiPhong().equals("Phong doi")) {
            GiaPhong = 500000;
        } else if (phong.getLoaiPhong().equals("Phong Vip")) {
            GiaPhong = 1000000;
        }
        return GiaPhong * soNgay;
    }

    public double TienDichVu(ArrayList<DichVu> DichVu) {
        int TongTien = 0;
        if (DichVu != null && !DichVu.isEmpty()) {
            for (DichVu dv : DichVu) {
                TongTien += dv.getGia();
            }
        }
        return TongTien;
    }
}
