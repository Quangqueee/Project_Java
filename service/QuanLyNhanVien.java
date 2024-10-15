package service;

import model.NhanVien;
import model.NhanVienThuong;
import model.QuanLy;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyNhanVien {
    private ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Menu quản lý nhân viên
    public void MenuQuanLyNhanVien() {
        while (true) {
            System.out.println("\n|------------------------|");
            System.out.println("| Quan ly nhan vien      |");
            System.out.println("|------------------------|");
            System.out.println("| 1. Them nhan vien      |");
            System.out.println("| 2. Hien thi danh sach  |");
            System.out.println("| 3. Tinh luong nhan vien|");
            System.out.println("| 4. Xoa nhan vien       |");
            System.out.println("| 5. Quay lai            |");
            System.out.println("|------------------------|");
            System.out.print("Chon chuc nang: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    themNhanVien();
                    break;
                case 2:
                    hienThiDanhSachNhanVien();
                    break;
                case 3:
                    tinhLuongNhanVien();
                    break;
                case 4:
                    xoaNhanVien();
                    break;
                case 5:
                    System.out.println("Quay lai menu chinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    // Thêm nhân viên mới
    public void themNhanVien() {
        System.out.println("Chon loai nhan vien:");
        System.out.println("1. Nhan vien thuong");
        System.out.println("2. Quan ly");
        System.out.print("Lua chon: ");
        int chon = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ten: ");
        String ten = sc.nextLine();
        System.out.print("Nhap tuoi: ");
        int tuoi = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap que quan: ");
        String queQuan = sc.nextLine();
        System.out.print("Nhap ngay sinh: ");
        String ngaySinh = sc.nextLine();
        System.out.print("Nhap CCCD: ");
        String cccd = sc.nextLine();
        System.out.print("Nhap SDT: ");
        String sdt = sc.nextLine();
        System.out.print("Nhap he so luong: ");
        double heSoLuong = sc.nextDouble();
        sc.nextLine();

        if (chon == 1) {
            System.out.print("Nhap bo phan lam viec: ");
            String boPhanLamViec = sc.nextLine();
            danhSachNhanVien.add(new NhanVienThuong(ten, tuoi, queQuan, ngaySinh, cccd, sdt, heSoLuong, boPhanLamViec));
        } else if (chon == 2) {
            danhSachNhanVien.add(new QuanLy(ten, tuoi, queQuan, ngaySinh, cccd, sdt, heSoLuong));
        }

        System.out.println("Them nhan vien thanh cong.");
    }

    // Hiển thị danh sách nhân viên
    public void hienThiDanhSachNhanVien() {
        if (danhSachNhanVien.isEmpty()) {
            System.out.println("Danh sach nhan vien trong.");
            return;
        }

        // Hiển thị tiêu đề cột
        System.out.printf("%-5s %-20s %-5s %-15s %-12s %-11s %-15s %-10s %-20s\n",
                "STT", "Ten", "Tuoi", "Que Quan", "CCCD", "SDT", "Ngay Sinh", "HSL", "Bo Phan");

        System.out.println(
                "------------------------------------------------------------------------------------------------------");

        // Hiển thị từng nhân viên theo định dạng bảng
        for (int i = 0; i < danhSachNhanVien.size(); i++) {
            NhanVien nv = danhSachNhanVien.get(i);
            System.out.printf("%-5d %-20s %-5d %-15s %-12s %-11s %-15s %-10.1f %-20s\n",
                    i + 1,
                    nv.getTen(),
                    nv.getTuoi(),
                    nv.getQueQuan(),
                    nv.getCCCD(),
                    nv.getSDT(),
                    nv.getNgaySinh(),
                    nv.getHeSoLuong(),
                    (nv instanceof NhanVienThuong) ? ((NhanVienThuong) nv).getBoPhanLamViec() : "Quan ly");
        }
    }

    // Tính lương nhân viên
    public void tinhLuongNhanVien() {
        System.out.print("Nhap ten hoac CCCD nhan vien: ");
        String key = sc.nextLine();
        NhanVien nv = timNhanVien(key);

        if (nv != null) {
            System.out.println("Luong cua " + nv.getTen() + ": " + nv.tinhLuong() + " VND");
        } else {
            System.out.println("Khong tim thay nhan vien.");
        }
    }

    // Tìm kiếm nhân viên theo tên hoặc CCCD
    private NhanVien timNhanVien(String key) {
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getTen().equalsIgnoreCase(key) || nv.getCCCD().equals(key)) {
                return nv;
            }
        }
        return null;
    }

    // Xóa nhân viên
    public void xoaNhanVien() {
        System.out.print("Nhap ten hoac CCCD nhan vien can xoa: ");
        String key = sc.nextLine();
        NhanVien nv = timNhanVien(key);
        if (nv != null) {
            danhSachNhanVien.remove(nv);
            System.out.println("Xoa nhan vien thanh cong.");
        } else {
            System.out.println("Khong tim thay nhan vien.");
        }
    }
}
