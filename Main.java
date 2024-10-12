import model.DichVu;
import model.KhachHang;
import model.Phong;

import service.QuanLyKhachHang;
import service.QuanLyPhong;
import service.QuanLyDichVu;
import service.ThanhToan;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        QuanLyKhachHang QuanLyKhachHang = new QuanLyKhachHang();
        QuanLyPhong QuanLyPhong = new QuanLyPhong();
        QuanLyDichVu QuanLyDichVu = new QuanLyDichVu();
        ThanhToan ThanhToan = new ThanhToan();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("|---------------------|");
            System.out.println("| Danh sach chuc nang |");
            System.out.println("|---------------------|");
            System.out.println("|1. Quan ly khach hang|");
            System.out.println("|2. Quan ly phong     |");
            System.out.println("|3. Dat phong         |");
            System.out.println("|4. Quan ly dich vu   |");
            System.out.println("|5. Thanh toan        |");
            System.out.println("|6.      Thoat        |");
            System.out.println("|---------------------|");
            System.out.print("Nhap vao chuc nang: ");
            int ChucNang = sc.nextInt();
            sc.nextLine();

            switch (ChucNang) {
                case 1:
                    System.out.println("1. Them khach hang");
                    System.out.println("2. Tim kiem khach hang");
                    System.out.println("3. Sua khach hang");
                    System.out.println("4. Xoa khach hang");
                    System.out.println("5. Hien thi danh sach khach hang");
                    System.out.print("Xin chon chuc nang: ");

                    int QLKH = sc.nextInt();
                    sc.nextLine();

                    if (QLKH == 1) {
                        System.out.print("Nhap ten khach hang:");
                        String ten = sc.nextLine();
                        System.out.print("Nhap so dien thoai:");
                        String sdt = sc.nextLine();
                        System.out.print("Nhap so CCCD:");
                        String cccd = sc.nextLine();

                        KhachHang KhachMoi = new KhachHang(ten, sdt, cccd);
                        QuanLyKhachHang.ThemKhachHang(KhachMoi);
                        System.out.println("Them khach hang thanh cong!");

                    } else if (QLKH == 2) {
                        System.out.println("Nhap ten, so dien thoai, hoac CCCD de tim khach hang:");
                        String keyword = sc.nextLine();

                        KhachHang KhachHang = QuanLyKhachHang.TimKiemKhachHang(keyword);
                        if (KhachHang != null) {
                            System.out.println("Khach hang tim thay:");
                            System.out.println("Ten: " + KhachHang.getTen());
                            System.out.println("So dien thoai: " + KhachHang.getSDT());
                            System.out.println("CCCD: " + KhachHang.getCCCD());
                            System.out.println("Phong da/thue: " + KhachHang.getLichSuDatPhong());
                        } else {
                            System.out.println("Khong tim thay khach hang.");
                        }
                    } else if (QLKH == 3) {
                        System.out.print("Tim kiem khach hang can sua: ");
                        String key = sc.nextLine();
                        KhachHang KhachHang = QuanLyKhachHang.TimKiemKhachHang(key);

                        if (KhachHang != null) {
                            System.out.print("Nhap ten moi: ");
                            String TenMoi = sc.nextLine();
                            System.out.print("Nhap SDT moi: ");
                            String SdtMoi = sc.nextLine();
                            System.out.print("Nhap so CCCD moi: ");
                            String CccdMoi = sc.nextLine();

                            QuanLyKhachHang.SuaKhachHang(key, TenMoi, SdtMoi, CccdMoi);

                        } else {
                            System.out.println("Khong tim thay khach hang");
                        }
                    } else if (QLKH == 4) {
                        System.out.print("Nhap tu khoa (ten/sdt/cccd) de tim khach hang can xoa: ");
                        String key = sc.nextLine();
                        KhachHang KhachHang = QuanLyKhachHang.TimKiemKhachHang(key);

                        if (KhachHang != null) {
                            QuanLyKhachHang.XoaKhachHang(key);
                        } else {
                            System.out.println("Khong tim thay khach hang.");
                        }
                    } else if (QLKH == 5) {
                        QuanLyKhachHang.HienThiDanhSachKH();
                    }
                    break;

                // Quan Ly Phong
                case 2:
                    System.out.println("1. Hien thi danh sach phong");
                    System.out.println("2. Sua loai phong");
                    int Room = sc.nextInt();
                    sc.nextLine(); // Don bo dem
                    if (Room == 1) {
                        QuanLyPhong.TrangThaiPhong();
                    } else if (Room == 2) {
                        System.out.println("Nhap so phong:");
                        String SoPhong = sc.nextLine();
                        System.out.println("Nhap loai phong moi:");
                        String loaiPhongMoi = sc.nextLine();
                        QuanLyPhong.SuaPhong(SoPhong, loaiPhongMoi);
                    }
                    break;

                // Dat Phong
                case 3:
                    System.out.print("Nhap ten khach hang:");
                    String TenKhach = sc.nextLine();
                    System.out.print("Nhap so dien thoai khach hang:");
                    String sdt = sc.nextLine();
                    System.out.print("Nhap so CCCD khach hang:");
                    String cccd = sc.nextLine();

                    System.out.print("Nhap so phong muon dat:");
                    String SoPhongDat = sc.nextLine();
                    Phong PhongDat = QuanLyPhong.TimPhong(SoPhongDat);

                    if (PhongDat != null && PhongDat.getTrangThai().equals("Trong")) {
                        PhongDat.setTrangThai("Full");

                        KhachHang khachDatPhong = new KhachHang(TenKhach, sdt, cccd);
                        QuanLyKhachHang.ThemKhachHang(khachDatPhong);

                        khachDatPhong.ThemLichSuDatPhong("Phong " + SoPhongDat);
                        System.out.println("Dat phong thanh cong cho khach hang: " + TenKhach);
                    } else {
                        System.out.println("Phong khong kha dung!");
                    }
                    break;

                // Hien thi danh sach dich vu
                case 4:
                    System.out.println("-------Danh sach dich vu------");
                    QuanLyDichVu.HienThiDichVu();
                    break;
                case 5:
                    // Thanh toán
                    System.out.println("--------Thanh toan-------");
                    System.out.print("Nhap ten khach hang: ");
                    String TenKhachHang = sc.nextLine();
                    KhachHang KhachThanhToan = QuanLyKhachHang.TimKiemKhachHang(TenKhachHang);

                    if (KhachThanhToan != null) {
                        System.out.print("Nhap so phong: ");
                        String SoPhongThanhToan = sc.nextLine();
                        Phong PhongThanhToan = QuanLyPhong.TimPhong(SoPhongThanhToan);

                        if (PhongThanhToan != null && PhongThanhToan.getTrangThai().equals("Full")) {
                            System.out.print("Nhap so ngay khach o: ");
                            int Ngay = sc.nextInt();
                            sc.nextLine();
                            double TienPhong = ThanhToan.TienPhong(PhongThanhToan, Ngay);

                            System.out.println("Tong tien phong: " + TienPhong);
                            System.out.println("---------------------------");

                            System.out.println("Danh sach dich vu:");
                            QuanLyDichVu.HienThiDichVu();

                            System.out.print("Nhap so loai dich vu khach su dung: ");
                            int SoDichVu = sc.nextInt();
                            sc.nextLine();

                            ArrayList<DichVu> DVSD = new ArrayList<>();
                            double TongTienDichVu = 0;

                            // Nhap dich vu su dung
                            for (int i = 0; i < SoDichVu; i++) {
                                System.out.print("Nhap ten dich vu: ");
                                String TenDichVu = sc.nextLine();

                                DichVu DichVu = QuanLyDichVu.TimKiemDichVu(TenDichVu);
                                if (DichVu != null) {
                                    System.out.print("Nhap so luong dich vu '" + TenDichVu + "' su dung: ");
                                    int SoLuong = sc.nextInt();
                                    sc.nextLine();

                                    double TienDichVu = DichVu.getGia() * SoLuong;
                                    TongTienDichVu += TienDichVu;

                                    DVSD.add(DichVu);
                                    System.out.println(
                                            "Khach da chon dich vu: " + DichVu.getTenDichVu() + "\n"
                                                    + " - So luong: " + SoLuong + "\n" +
                                                    " - Thanh tien: " + TienDichVu);
                                } else {
                                    System.out.println("Dich vu khong ton tai.");
                                }
                            }

                            System.out.println("Tong tien dich vu: " + TongTienDichVu);

                            double TongTien = TienPhong + TongTienDichVu;
                            System.out.println("Tong tien thanh toan: " + TongTien);

                            PhongThanhToan.setTrangThai("Trong");

                            System.out.println("Thanh toan thanh cong!");
                        } else {
                            System.out.println("Phong khong kha dung hoac chua duoc dat.");
                        }
                    } else {
                        System.out.println("Khong tim thay khach hang.");
                    }
                    break;

                case 6:
                    System.out.println("Thoat chuong trinh.");
                    System.exit(0);
                default:
                    System.out.println("Chuc nang khong le, vui long thu lai!.");
            }
        }
    }
}
