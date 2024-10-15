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
    }

    // Hiển thị trạng thái tất cả các phòng
    public void TrangThaiPhong() {
        System.out.println("Danh Sach Phong:");
        for (Phong Phong : DanhSachPhong) {
            System.out.println("Phong: " + Phong.getSoPhong() +
                    " - Loai phong: " + Phong.getLoaiPhong() +
                    " - Trang Thai: " + Phong.getTrangThai());
        }
    }

    public void DatPhong(QuanLyKhachHang quanLyKhachHang) {
        System.out.println("--------Dat Phong--------");

        // Nhập tên khách hàng và kiểm tra tính hợp lệ
        String tenKhachHang;
        while (true) {
            System.out.print("Nhap ten khach hang: ");
            tenKhachHang = sc.nextLine();
            if (tenKhachHang.matches("[a-zA-Z\\s]{1,50}")) {
                break;
            } else {
                System.out.println("Ten khong hop le! Vui long nhap lai.");
            }
        }

        // Tìm kiếm khách hàng trong hệ thống
        ArrayList<KhachHang> ketQua = quanLyKhachHang.TimKiemKhachHang(tenKhachHang);

        KhachHang khachHang;
        if (ketQua.isEmpty()) {
            System.out.println("Khach Hang Chua Co Trong He Thong. Tao Moi Khach Hang.");

            // Nhập và kiểm tra số điện thoại
            String sdt;
            while (true) {
                System.out.print("Nhap so dien thoai: ");
                sdt = sc.nextLine();
                if (sdt.matches("0\\d{9}")) {
                    break;
                } else {
                    System.out.println("So dien thoai khong hop le! Vui long nhap lai.");
                }
            }

            // Nhập và kiểm tra CCCD
            String cccd;
            while (true) {
                System.out.print("Nhap so CCCD: ");
                cccd = sc.nextLine();
                if (cccd.matches("\\d{12}")) {
                    break;
                } else {
                    System.out.println("So CCCD khong hop le! Vui long nhap lai.");
                }
            }

            // Tạo đối tượng khách hàng mới và thêm vào hệ thống
            khachHang = new KhachHang(tenKhachHang, sdt, cccd);
            quanLyKhachHang.ThemKhachHang(khachHang);
            System.out.println("Them Khach Hang Moi Thanh Cong.");
        } else {
            // Nếu có nhiều kết quả, yêu cầu chọn khách hàng cụ thể
            if (ketQua.size() > 1) {
                System.out.println("Co nhieu khach hang khop voi ten: ");
                for (int i = 0; i < ketQua.size(); i++) {
                    KhachHang kh = ketQua.get(i);
                    System.out.println((i + 1) + ". " + kh.getTen() +
                            " - SĐT: " + kh.getSDT() +
                            " - CCCD: " + kh.getCCCD());
                }
                System.out.print("Chon khach hang (so thu tu): ");
                int chon = sc.nextInt();
                sc.nextLine(); // Đọc bỏ ký tự xuống dòng

                if (chon < 1 || chon > ketQua.size()) {
                    System.out.println("Lua chon khong hop le.");
                    return;
                }
                khachHang = ketQua.get(chon - 1);
            } else {
                khachHang = ketQua.get(0); // Nếu chỉ có một kết quả, chọn luôn
            }
        }

        // Nhập số phòng và kiểm tra tính hợp lệ
        System.out.print("Nhap So Phong: ");
        String soPhong = sc.nextLine();
        Phong phongDat = TimPhong(soPhong);

        if (phongDat != null && phongDat.getTrangThai().equals("Trong")) {
            phongDat.setTrangThai("Full");
            System.out.println("Dat Phong Thanh Cong. Phong: " + phongDat.getSoPhong());
            khachHang.themPhongDaThue(soPhong); // Cập nhật lịch sử phòng đã thuê
        } else {
            System.out.println("Phong Khong Kha Dung Hoac Da Duoc Dat.");
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
        System.out.print("Nhap So Phong Can Sua: ");
        String SoPhong = sc.nextLine();
        Phong PhongCanSua = TimPhong(SoPhong);

        if (PhongCanSua != null) {
            System.out.print("Nhap Loai Phong Moi: ");
            String LoaiPhongMoi = sc.nextLine();
            PhongCanSua.setLoaiPhong(LoaiPhongMoi);
            System.out.println("Cap Nhat Loai Phong Thanh Cong.");
        } else {
            System.out.println("Khong Tim Thay Phong.");
        }
    }

    public void CapNhatTrangThaiPhong() {
        System.out.print("Nhap So Phong Can Cap Nhat Trang Thai: ");
        String SoPhong = sc.nextLine();
        Phong PhongCanCapNhat = TimPhong(SoPhong);

        if (PhongCanCapNhat != null) {
            System.out.print("Nhap Trang Thai Moi (Trong/Full): ");
            String TrangThaiMoi = sc.nextLine();
            PhongCanCapNhat.setTrangThai(TrangThaiMoi);
            System.out.println("Cap Nhat Trang Thai Thanh Cong.");
        } else {
            System.out.println("Khong Tim Thay Phong.");
        }
    }

    public void MenuQuanLyPhong() {
        while (true) {
            System.out.println("\n|------------------------|");
            System.out.println("| Quan Ly Phong          |");
            System.out.println("|------------------------|");
            System.out.println("| 1. Xem Trang Thai Phong|");
            System.out.println("| 2. Sua Loai Phong      |");
            System.out.println("| 3. Cap Nhat Trang Thai |");
            System.out.println("| 4. Quay Lai            |");
            System.out.println("|------------------------|");
            System.out.print("Chon Chuc Nang: ");
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
                    System.out.println("Quay Lai Menu Chinh.");
                    return;
                default:
                    System.out.println("Chuc Nang Khong Hop Le.");
            }
        }
    }
}
