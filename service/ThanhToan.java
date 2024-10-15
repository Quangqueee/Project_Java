package service;

import model.KhachHang;
import model.Phong;
import model.DichVu;

import java.util.ArrayList;
import java.util.Scanner;

public class ThanhToan {
    private Scanner sc = new Scanner(System.in);

    // Hiển thị menu thanh toán
    public void MenuThanhToan(QuanLyKhachHang quanLyKhachHang, QuanLyPhong quanLyPhong, QuanLyDichVu quanLyDichVu) {
        System.out.println("--------Thanh Toan--------");

        System.out.print("Nhap Ten Khach Hang: ");
        String TenKhachHang;
        while (true) {
            System.out.print("Nhap ten khach hang: ");
            TenKhachHang = sc.nextLine();
            if (TenKhachHang.matches("[a-zA-Z\\s]{1,50}")) {
                break;
            } else {
                System.out.println("Ten khong hop le. Vui long nhap lai.");
            }
        }


        // Tìm kiếm danh sách khách hàng khớp với tên
        ArrayList<KhachHang> KetQua = quanLyKhachHang.TimKiemKhachHang(TenKhachHang);

        if (KetQua.isEmpty()) {
            System.out.println("Khong tim thay khach hang.");
            return;
        }

        // Nếu có nhiều khách hàng, yêu cầu người dùng chọn
        KhachHang KhachThanhToan;
        if (KetQua.size() > 1) {
            System.out.println("Ket qua tra ve voi ten: ");
            for (int i = 0; i < KetQua.size(); i++) {
                KhachHang kh = KetQua.get(i);
                System.out.println((i + 1) + ". " + kh.getTen() +
                        " - SĐT: " + kh.getSDT() +
                        " - CCCD: " + kh.getCCCD());
            }
            System.out.print("Chon khach hang can thanh toan (so thu tu): ");
            int chon = sc.nextInt();
            sc.nextLine(); // Đọc bỏ ký tự xuống dòng

            if (chon < 1 || chon > KetQua.size()) {
                System.out.println("Lua chon khong hop le.");
                return;
            }

            KhachThanhToan = KetQua.get(chon - 1);
        } else {
            KhachThanhToan = KetQua.get(0);
        }

        // Nhập số phòng cần thanh toán và kiểm tra
        System.out.print("Nhap So Phong: ");
        String SoPhong = sc.nextLine();
        Phong PhongThanhToan = quanLyPhong.TimPhong(SoPhong);

        if (PhongThanhToan == null || !PhongThanhToan.getTrangThai().equals("Full")) {
            System.out.println("Phong khong kha dung hoac chua duoc dat.");
            return;
        }

        // Nhập số ngày khách ở
        System.out.print("Nhap So Ngay Khach O: ");
        int SoNgay = sc.nextInt();
        sc.nextLine();

        // Nhập dịch vụ đã sử dụng
        System.out.print("Nhap So Loai Dich Vu Khach Da Su Dung: ");
        int SoDichVu = sc.nextInt();
        sc.nextLine();

        ArrayList<DichVu> DVSD = NhapDichVuSuDung(SoDichVu, quanLyDichVu);

        // Thực hiện thanh toán
        ThucHienThanhToan(KhachThanhToan, PhongThanhToan, SoNgay, DVSD);
    }

    public int TienPhong(Phong phong, int SoNgay) {
        int GiaPhong = LayGiaPhong(phong.getLoaiPhong());
        return GiaPhong * SoNgay;
    }

    private int LayGiaPhong(String LoaiPhong) {
        switch (LoaiPhong) {
            case "Phong don":
                return 300000;
            case "Phong doi":
                return 500000;
            case "Phong Vip":
                return 1000000;
            default:
                return 70000; 
        }
    }

    // Tính tiền dịch vụ từ danh sách dịch vụ đã sử dụng (int)
    public int TienDichVu(ArrayList<DichVu> DanhSachDichVu) {
        int TongTien = 0;
        if (DanhSachDichVu != null && !DanhSachDichVu.isEmpty()) {
            for (DichVu dv : DanhSachDichVu) {
                TongTien += dv.getGia();
            }
        }
        return TongTien;
    }

    // Thực hiện thanh toán và tính tổng tiền
    public void ThucHienThanhToan(KhachHang KhachThanhToan, Phong PhongThanhToan, int SoNgay, ArrayList<DichVu> DVSD) {
        int TienPhong = TienPhong(PhongThanhToan, SoNgay);
        System.out.println("Tong tien phong: " + TienPhong + " VND");

        int TongTienDichVu = TienDichVu(DVSD);
        System.out.println("Tong tien dich vu: " + TongTienDichVu + " VND");

        int TongTien = TienPhong + TongTienDichVu;
        System.out.printf("Tong tien thanh toan: %d VND\n", TongTien);

        PhongThanhToan.setTrangThai("Trong");
        System.out.println("Cam on quy khach!");
    }

    // Nhập các dịch vụ khách đã sử dụng và tính số lượng dịch vụ (int)
    public ArrayList<DichVu> NhapDichVuSuDung(int SoDichVu, QuanLyDichVu QuanLyDichVu) {
        ArrayList<DichVu> DVSD = new ArrayList<>();
        for (int i = 0; i < SoDichVu; i++) {
            System.out.print("Nhap ten dich vu: ");
            String TenDichVu = sc.nextLine();

            DichVu dichVu = QuanLyDichVu.TimKiemDichVu(TenDichVu);
            if (dichVu != null) {
                System.out.print("Nhap so luong dich vu '" + TenDichVu + "' su dung: ");
                int SoLuong = sc.nextInt();
                sc.nextLine(); 

                int TienDichVu = (int) dichVu.getGia() * SoLuong;

                System.out.printf("Khach da chon dich vu: %s - So luong: %d - Thanh tien: %d VND\n",
                        dichVu.getTenDichVu(), SoLuong, TienDichVu);

                for (int j = 0; j < SoLuong; j++) {
                    DVSD.add(dichVu);
                }
            } else {
                System.out.println("Dich vu khong ton tai.");
            }
        }
        return DVSD;
    }
}
