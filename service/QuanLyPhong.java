package service;

import model.KhachHang;
import model.Phong;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyPhong {
    private ArrayList<Phong> DanhSachPhong;
    private Scanner sc = new Scanner(System.in);

    // Khởi tạo danh sách phòng
    public QuanLyPhong() {
        DanhSachPhong = new ArrayList<>();
        DanhSachPhong.add(new Phong("101", "Phong Don", "Trong"));
        DanhSachPhong.add(new Phong("102", "Phong Doi", "Trong"));
        DanhSachPhong.add(new Phong("103", "Phong Vip", "Trong"));
        DanhSachPhong.add(new Phong("201", "Phong Don", "Trong"));
        DanhSachPhong.add(new Phong("202", "Phong Doi", "Trong"));
        DanhSachPhong.add(new Phong("203", "Phong Vip", "Trong"));
        DanhSachPhong.add(new Phong("301", "Phong Don", "Trong"));
        DanhSachPhong.add(new Phong("302", "Phong Doi", "Trong"));
        DanhSachPhong.add(new Phong("303", "Phong Vip", "Trong"));
    }

    public void TrangThaiPhong() {
        System.out.println("Danh Sach Phong:");
        for (Phong Phong : DanhSachPhong) {
            System.out.println("Phong: " + Phong.getSoPhong() +
                    " - Loai phong: " + Phong.getLoaiPhong() +
                    " - Trang Thai: " + Phong.getTrangThai());
        }
    }

    public void DatPhong(QuanLyKhachHang QuanLyKhachHang) {
        System.out.println("--------Dat Phong--------");

        String tenkhach;
        while (true) {
            System.out.print("Nhap ten khach hang: ");
            tenkhach = sc.nextLine();
            if (tenkhach.matches("[a-zA-Z\\s]{1,50}")) {
                break;
            } else {
                System.out.println("\nTen khong hop le! Vui long nhap lai.\n");
            }
        }

        // Tìm kiếm khách hàng trong hệ thống
        ArrayList<KhachHang> KetQua = QuanLyKhachHang.TimKiemKhachHang(tenkhach);

        KhachHang KhachHang;
        if (KetQua.isEmpty()) {
            System.out.println("\nKhach Hang Chua Co Trong He Thong. Tao Moi Khach Hang.");

            String sdt;
            while (true) {
                System.out.print("\nNhap so dien thoai: ");
                sdt = sc.nextLine();
                if (sdt.matches("0\\d{9}")) {
                    break;
                } else {
                    System.out.println("\nSo dien thoai khong hop le! Vui long nhap lai.\n");
                }
            }

            String cccd;
            while (true) {
                System.out.print("Nhap so CCCD: ");
                cccd = sc.nextLine();
                if (cccd.matches("\\d{12}")) {
                    break;
                } else {
                    System.out.println("\nSo CCCD khong hop le! Vui long nhap lai.\n");
                }
            }

            KhachHang = new KhachHang(tenkhach, sdt, cccd);
            QuanLyKhachHang.ThemKhachHang(KhachHang);

        } else {
            // Nếu có nhiều kết quả, yêu cầu chọn khách hàng cụ thể
            if (KetQua.size() > 1) {
                System.out.println("\nCo nhieu khach hang khop voi ten:\n");
                for (int i = 0; i < KetQua.size(); i++) {
                    KhachHang kh = KetQua.get(i);
                    System.out.println((i + 1) + ". " + kh.getTen() +
                            " - SDT: " + kh.getSDT() +
                            " - CCCD: " + kh.getCCCD());
                }
                System.out.print("\nChon khach hang (so thu tu): ");
                int chon = sc.nextInt();
                sc.nextLine();

                if (chon < 1 || chon > KetQua.size()) {
                    System.out.println("\nLua chon khong hop le.");
                    return;
                }
                KhachHang = KetQua.get(chon - 1);
            } else {
                KhachHang = KetQua.get(0);
            }
        }

        // Nhập số phòng và kiểm tra
        System.out.print("Nhap So Phong: ");
        String SoPhong = sc.nextLine();
        Phong PhongDat = TimPhong(SoPhong);

        if (PhongDat != null && PhongDat.getTrangThai().equals("Trong")) {
            PhongDat.setTrangThai("Full");
            System.out.println("\nDat Phong Thanh Cong. Phong: " + PhongDat.getSoPhong());
            KhachHang.ThemPhongDaThue(SoPhong); // Cập nhật lịch sử phòng đã thuê
        } else {
            System.out.println("\nPhong Khong Kha Dung Hoac Da Duoc Dat.");
        }
    }

    public Phong TimPhong(String SoPhong) {
        for (Phong Phong : DanhSachPhong) {
            if (Phong.getSoPhong().equals(SoPhong)) {
                return Phong;
            }
        }
        return null;
    }

    public void SuaPhong() {
        System.out.print("\nNhap So Phong Can Sua: ");
        String SoPhong = sc.nextLine();
        Phong PhongCanSua = TimPhong(SoPhong);

        if (PhongCanSua != null) {
            System.out.print("Nhap Loai Phong Moi: ");
            String LoaiPhongMoi = sc.nextLine();
            PhongCanSua.setLoaiPhong(LoaiPhongMoi);
            System.out.println("\nCap Nhat Loai Phong Thanh Cong.");
        } else {
            System.out.println("\nKhong Tim Thay Phong.");
        }
    }

    public void CapNhatTrangThaiPhong() {
        System.out.print("\nNhap So Phong Can Cap Nhat Trang Thai: ");
        String SoPhong = sc.nextLine();
        Phong PhongCanCapNhat = TimPhong(SoPhong);

        if (PhongCanCapNhat != null) {
            System.out.print("\nNhap Trang Thai Moi (Trong/Full): ");
            String TrangThaiMoi = sc.nextLine();
            PhongCanCapNhat.setTrangThai(TrangThaiMoi);
            System.out.println("\nCap Nhat Trang Thai Thanh Cong.");
        } else {
            System.out.println("\nKhong Tim Thay Phong.");
        }
    }

    public void MenuQuanLyPhong() {
        while (true) {
            System.out.println("\n|------------------------|");
            System.out.println("|     Quan Ly Phong      |");
            System.out.println("|------------------------|");
            System.out.println("| 1. Xem Trang Thai Phong|");
            System.out.println("| 2. Sua Loai Phong      |");
            System.out.println("| 3. Cap Nhat Trang Thai |");
            System.out.println("| 4. Quay Lai            |");
            System.out.println("|------------------------|");
            System.out.print("\nChon Chuc Nang: ");
            int Chon = sc.nextInt();
            sc.nextLine();

            switch (Chon) {
                case 1:
                    TrangThaiPhong();
                    break;
                case 2:
                    SuaPhong();
                    break;
                case 3:
                    CapNhatTrangThaiPhong();
                    break;
                case 4:
                    System.out.println("\nQuay Lai Menu Chinh.");
                    return;
                default:
                    System.out.println("\nChuc Nang Khong Hop Le.");
            }
        }
    }
}
