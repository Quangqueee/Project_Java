package service;

import model.KhachHang;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyKhachHang {
    private ArrayList<KhachHang> DanhSachKhachHang;

    public QuanLyKhachHang() {
        DanhSachKhachHang = new ArrayList<>();
        DanhSachKhachHang.add(new KhachHang("Nguyen Duc A", "0812551230", "0000000001"));
        DanhSachKhachHang.add(new KhachHang("Mai Van B", "0912551230", "0000000002"));
        DanhSachKhachHang.add(new KhachHang("Nguyen Hong C", "0889999403", "0000000003"));
        DanhSachKhachHang.add(new KhachHang("Le Thi D", "0812442111", "0000000004"));
        DanhSachKhachHang.add(new KhachHang("Dang Duc E", "0912551230", "0000000005"));
    }

    public void ThemKhachHang(KhachHang KhachHang) {
        DanhSachKhachHang.add(KhachHang);
        System.out.println("\nThem Khach Hang Thanh Cong.");
    }

    public void ThemKhachHang() {
        Scanner sc = new Scanner(System.in);

        String ten;
        while (true) {
            System.out.print("Nhap ten khach hang: ");
            ten = sc.nextLine();
            if (ten.matches("[a-zA-Z\\s]{1,50}")) {
                break;
            } else {
                System.out.println("\nTen khong hop le. Vui long nhap lai.");
            }
        }

        String sdt;
        while (true) {
            System.out.print("\nNhap so dien thoai: ");
            sdt = sc.nextLine();
            if (sdt.matches("0\\d{9}")) {
                break;
            } else {
                System.out.println("\nSo dien thoai khong hop le. Vui long nhap lai.");
            }
        }

        String cccd;
        while (true) {
            System.out.print("Nhap so CCCD: ");
            cccd = sc.nextLine();
            if (cccd.matches("\\d{12}")) {
                break;
            } else {
                System.out.println("\nSo CCCD khong hop le. Vui long nhap lai.");
            }
        }

        KhachHang KhachHang = new KhachHang(ten, sdt, cccd);
        DanhSachKhachHang.add(KhachHang);
        System.out.println("\nThem khach hang thanh cong.");
    }

    public ArrayList<KhachHang> TimKiemKhachHang(String key) {
        ArrayList<KhachHang> KetQua = new ArrayList<>();
        for (KhachHang kh : DanhSachKhachHang) {
            if (kh.getTen().toLowerCase().contains(key.toLowerCase()) ||
                    kh.getSDT().contains(key) ||
                    kh.getCCCD().contains(key)) {
                KetQua.add(kh);
            }
        }
        return KetQua;
    }

    public void SuaKhachHang() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nNhap thong tin khach hang can sua (ten, SDT hoac CCCD): ");
        String key = sc.nextLine();

        ArrayList<KhachHang> KetQua = TimKiemKhachHang(key);

        if (KetQua.isEmpty()) {
            System.out.println("\nKhong tim thay khach hang.");
            return;
        }

        // Nếu có nhiều khách hàng khớp, yêu cầu chọn một khách hàng cụ thể
        if (KetQua.size() > 1) {
            System.out.println("\nCo nhieu khach hang khop voi thong tin: ");
            for (int i = 0; i < KetQua.size(); i++) {
                KhachHang kh = KetQua.get(i);
                System.out.println((i + 1) + ". " + kh.getTen() +
                        " - SDT: " + kh.getSDT() +
                        " - CCCD: " + kh.getCCCD());
            }
            System.out.print("\nChon khach hang can sua (Chon theo STT): ");
            int chon = sc.nextInt();
            sc.nextLine();

            if (chon < 1 || chon > KetQua.size()) {
                System.out.println("\nLua chon khong hop le.");
                return;
            }

            KhachHang kh = KetQua.get(chon - 1);
            CapNhatThongTinKhachHang(sc, kh);
        } else {
            // Nếu chỉ có một khách hàng, sửa trực tiếp
            KhachHang kh = KetQua.get(0);
            CapNhatThongTinKhachHang(sc, kh);
        }
    }

    // Hàm cập nhật thông tin khách hàng
    private void CapNhatThongTinKhachHang(Scanner sc, KhachHang kh) {
        System.out.print("\nNhap ten moi: ");
        String tenMoi = sc.nextLine();
        System.out.print("Nhap SDT moi: ");
        String sdtMoi = sc.nextLine();
        System.out.print("Nhap CCCD moi: ");
        String cccdMoi = sc.nextLine();

        kh.setTen(tenMoi);
        kh.setSDT(sdtMoi);
        kh.setCCCD(cccdMoi);

        System.out.println("\nCap nhat thong tin thanh cong.");
    }

    public void XoaKhachHang() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nNhap thong tin khach hang can xoa (ten, SDT hoac CCCD): ");
        String key = sc.nextLine();
    
        ArrayList<KhachHang> KetQua = TimKiemKhachHang(key); 
    
        if (KetQua.isEmpty()) {
            System.out.println("\nKhong tim thay khach hang nao.");
            return;
        }
    
        System.out.println("\nChon khach hang muon xoa:\n");
        for (int i = 0; i < KetQua.size(); i++) {
            System.out.println((i + 1) + ". " + KetQua.get(i));
        }
    
        System.out.print("\nNhap so thu tu khach hang can xoa: ");
        int chon = sc.nextInt();
        sc.nextLine(); 
    
        if (chon < 1 || chon > KetQua.size()) {
            System.out.println("\nLua chon khong hop le.");
            return;
        }
    
        KhachHang khachHang = KetQua.get(chon - 1);
        DanhSachKhachHang.remove(khachHang); 
        System.out.println("\nXoa khach hang thanh cong.");
    }
    

    public void HienThiDanhSachKH() {
        if (DanhSachKhachHang.isEmpty()) {
            System.out.println("\nDanh sach khach hang trong.");
            return;
        }

        for (KhachHang kh : DanhSachKhachHang) {
            System.out.println("Khach hang: " + kh.getTen()
                    + " - So dien thoai: " + kh.getSDT()
                    + " - CCCD: " + kh.getCCCD());

            ArrayList<String> LichSuPhong = kh.getLichSuDatPhong();
            if (LichSuPhong.isEmpty()) {
                System.out.println("- Khach hang chua thue phong nao.");
            } else {
                for (String phong : LichSuPhong) {
                    System.out.println("- Phong da thue: " + phong);
                }
            }
        }
    }

    // Hiển thị menu quản lý khách hàng
    public void MenuQuanLyKhachHang() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n|------------------------|");
            System.out.println("|   Quan ly khach hang   |");
            System.out.println("|------------------------|");
            System.out.println("| 1. Them khach hang     |");
            System.out.println("| 2. Tim khach hang      |");
            System.out.println("| 3. Sua khach hang      |");
            System.out.println("| 4. Xoa khach hang      |");
            System.out.println("| 5. Hien thi danh sach  |");
            System.out.println("| 6. Quay lai            |");
            System.out.println("|------------------------|");
            System.out.print("\nChon chuc nang: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    ThemKhachHang();
                    break;
                case 2:
                    System.out.print("\nNhap thong tin khach hang can tim: ");
                    String key = sc.nextLine();
                    ArrayList<KhachHang> ketQua = TimKiemKhachHang(key);

                    if (ketQua.isEmpty()) {
                        System.out.println("\nKhong tim thay khach hang.");
                    } else {
                        System.out.println("\nKet qua tim kiem:\n");
                        for (KhachHang kh : ketQua) {
                            System.out.println("Khach hang: " + kh.getTen() +
                                    " - So dien thoai: " + kh.getSDT() +
                                    " - CCCD: " + kh.getCCCD());
                        }
                    }
                    break;

                case 3:
                    SuaKhachHang();
                    break;
                case 4:
                    XoaKhachHang();
                    break;
                case 5:
                    HienThiDanhSachKH();
                    break;
                case 6:
                    System.out.println("\nQuay lai menu chinh.");
                    return;
                default:
                    System.out.println("\nChuc nang khong hop le.");
                    break;
            }
        }
    }
}
