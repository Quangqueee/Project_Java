import service.QuanLyKhachHang;
import service.QuanLyNhanVien;
import service.QuanLyPhong;
import service.QuanLyDichVu;
import service.ThanhToan;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Khởi tạo các đối tượng quản lý
        QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang();
        QuanLyPhong quanLyPhong = new QuanLyPhong();
        QuanLyDichVu quanLyDichVu = new QuanLyDichVu();
        QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien();
        ThanhToan thanhToan = new ThanhToan();

        while (true) {
            System.out.println("\n|----------------------------|");
            System.out.println("| He Thong Quan Ly Khach San |");
            System.out.println("|----------------------------|");
            System.out.println("| 1. Quan Ly Khach Hang      |");
            System.out.println("| 2. Quan Ly Phong           |");
            System.out.println("| 3. Quan Ly Dich Vu         |");
            System.out.println("| 4. Dat Phong               |");
            System.out.println("| 5. Thanh Toan              |");
            System.out.println("| 6. Thoat                   |");
            System.out.println("|----------------------------|");
            System.out.print("Chon Chuc Nang: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    quanLyKhachHang.MenuQuanLyKhachHang();
                    break;

                case 2:
                    quanLyPhong.MenuQuanLyPhong();
                    break;

                case 3:
                    quanLyDichVu.MenuQuanLyDichVu();
                    break;

                case 4:
                    quanLyPhong.DatPhong(quanLyKhachHang);
                    break;

                case 5:
                    thanhToan.MenuThanhToan(quanLyKhachHang, quanLyPhong, quanLyDichVu);
                    break;

                case 6:
                    System.out.println("Thoat Chuong Trinh.");
                    System.exit(0);
                    break;

                case 7:
                    quanLyNhanVien.MenuQuanLyNhanVien();
                    break;
                default:
                    System.out.println("Chuc Nang Khong Hop Le. Vui Long Chon Lai.");
            }
        }
    }
}
