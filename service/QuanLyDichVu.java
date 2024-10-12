package service;

import model.DichVu;
import java.util.ArrayList;

public class QuanLyDichVu {
    private ArrayList<DichVu> DanhSachDichVu;

    public QuanLyDichVu() {
        DanhSachDichVu = new ArrayList<>();
        DanhSachDichVu.add(new DichVu("An sang", 100000));
        DanhSachDichVu.add(new DichVu("Dua don", 200000));
        DanhSachDichVu.add(new DichVu("Giat ui", 50000));
        DanhSachDichVu.add(new DichVu("Spa", 300000));
    }

    public void HienThiDichVu() {
        for (int i = 0; i < DanhSachDichVu.size(); i++) {
            DichVu dv = DanhSachDichVu.get(i);
            System.out.println((i + 1) + ": " + dv.getTenDichVu() + " - Gia tien: " + dv.getGia());
        }
    }

    public DichVu TimKiemDichVu(String TenDichVu) {
        for (int i = 0; i < DanhSachDichVu.size(); i++) {
            DichVu dv = DanhSachDichVu.get(i);
            if (dv.getTenDichVu().equalsIgnoreCase(TenDichVu)) {
                return dv;
            }
        }
        return null;
    }


}
