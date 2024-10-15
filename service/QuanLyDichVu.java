package service;

import model.DichVu;
import java.util.ArrayList;
import java.util.Scanner;

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
        if (DanhSachDichVu.isEmpty()) {
            System.out.println("Danh sach dich vu trong.");
            return;
        }
        System.out.println("Danh sach dich vu:");
        int i = 1;
        for (DichVu dv : DanhSachDichVu) {
            System.out.println(i++ + ": " + dv.getTenDichVu() + " - Gia tien: " + dv.getGia());
        }
    }

    // Tìm kiếm dịch vụ theo tên
    public DichVu TimKiemDichVu(String TenDichVu) {
        for (DichVu dv : DanhSachDichVu) {
            if (dv.getTenDichVu().equalsIgnoreCase(TenDichVu)) {
                return dv;
            }
        }
        return null;
    }

    // Thêm dịch vụ mới
    public void ThemDichVu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten dich vu: ");
        String tenDichVu = sc.nextLine();
        System.out.print("Nhap gia dich vu: ");
        int gia = sc.nextInt();
        sc.nextLine();

        DanhSachDichVu.add(new DichVu(tenDichVu, gia));
        System.out.println("Them dich vu thanh cong.");
    }

    public void XoaDichVu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten dich vu can xoa: ");
        String tenDichVu = sc.nextLine();

        DichVu dv = TimKiemDichVu(tenDichVu);
        if (dv != null) {
            DanhSachDichVu.remove(dv);
            System.out.println("Xoa dich vu thanh cong.");
        } else {
            System.out.println("Khong tim thay dich vu.");
        }
    }

    public void MenuQuanLyDichVu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n|----------------------|");
            System.out.println("| Quan ly dich vu      |");
            System.out.println("|----------------------|");
            System.out.println("| 1. Hien thi dich vu  |");
            System.out.println("| 2. Them dich vu      |");
            System.out.println("| 3. Xoa dich vu       |");
            System.out.println("| 4. Quay lai          |");
            System.out.println("|----------------------|");
            System.out.print("Chon chuc nang: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    HienThiDichVu();
                    break;
                case 2:
                    ThemDichVu();
                    break;
                case 3:
                    XoaDichVu();
                    break;
                case 4:
                    System.out.println("Quay lai menu chinh.");
                    return;
                default:
                    System.out.println("Chuc nang khong hop le.");
            }
        }
    }
}
