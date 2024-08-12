/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.KetQua;
import com.core.model.request.HoaDonRequest;
import com.core.model.response.HoaDonChiTietResponse1;
import com.core.model.response.HoaDonResponse;
import com.core.tool.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hatundev
 */
public class HoaDonRepository {

    public List<HoaDonResponse> findAllByHD() {
        String sql = """
                     SELECT
                     	ROW_NUMBER() OVER (
                     	ORDER BY hd.id DESC) AS 'stt',
                     	hd.id,
                     	kh.ten_khach_hang,
                        kh.so_dien_thoai,
                     	hd.tong_tien
                     FROM
                     	HoaDon hd
                     LEFT JOIN KhachHang kh ON
                     	hd.id_khach_hang = kh.id
                     WHERE
                     	hd.hoat_dong = 0
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<HoaDonResponse> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(HoaDonResponse.builder()
                        .stt(rs.getInt(1))
                        .id(rs.getInt(2))
                        .tenKhachHang(rs.getString(3))
                        .sdt(rs.getString(4))
                        .giaTien(rs.getFloat(5))
                        .build()
                );
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KetQua create(String username) {

        String checkSql = """
                          SELECT
                          	COUNT(*) AS total
                          FROM
                          	HoaDon
                          WHERE
                          	hoat_dong = 0
                          """;
        String insertSql = """
                     INSERT
                     	INTO
                     	HoaDon (
                     	    ngay_tao,
                            id_nhan_vien,
                            nguoi_tao,
                            hoat_dong)
                     VALUES (GETDATE(),?, ?, 0)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(checkSql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt("total") >= 5) {
                return new KetQua(0, "Chỉ được tạo 5 hóa đơn chờ");
            }
            try (PreparedStatement pss = conn.prepareStatement(insertSql)) {
                pss.setString(1, username);
                pss.setString(2, username);
                int result = pss.executeUpdate();
                if (result != 0) {
                    return new KetQua(1, "Tạo hóa đơn chờ thành công");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new KetQua(0, "Tạo hóa đơn chờ thất bại");
    }

    public KetQua updateMoney(int id, float tongTien) {
        String sql = """
                     UPDATE HoaDon
                     SET tong_tien = ?
                     WHERE id = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(2, id);
            ps.setFloat(1, tongTien);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new KetQua(0, "Erros");
    }

    public KetQua updateKhachHang(int id, String name, String sdt, int idHoaDon) {
        String sql = """
                     UPDATE HoaDon
                     SET id_khach_hang = ?, ten_khach_hang = ?, so_dien_thoai = ?
                     WHERE id = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, sdt);
            ps.setInt(4, idHoaDon);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new KetQua(0, "Erros");
    }

    public KetQua updateHoanThanh(float tongTien, int idHoaDon, float tongTienSauGiam, Integer idVoucher) {
        String sql = """
                     UPDATE HoaDon
                     SET tong_tien = ?, hoat_dong = 1, tong_tien_sau_giam = ?, id_phieu_giam_gia = ?
                     WHERE id = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, tongTien);
            ps.setFloat(2, tongTienSauGiam);
            ps.setInt(3, idVoucher);
            ps.setInt(4, idHoaDon);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new KetQua(0, "Erros");
    }

    public KetQua delete(int id) {
        String sql = """
                     DELETE FROM HoaDon
                     WHERE id = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result != 0) {

                return new KetQua(1, "Thanh cong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new KetQua(0, "That bai");
    }

    public static void main(String[] args) {
        HoaDonRepository hoaDonRepository = new HoaDonRepository();
        System.out.println(hoaDonRepository.updateMoney(6, 100000000).getThongBao());
    }

    public Integer findByHoaDonRequest(HoaDonRequest data) {
        String sql = """
                     SELECT id
                     FROM HoaDon
                     WHERE id_khach_hang = ? AND
                           id_nhan_vien = ? AND
                           id_phieu_giam_gia = ? AND
                           tong_tien = ? AND
                           tong_tien_sau_giam = ? AND
                           ten_khach_hang = ? AND
                           so_dien_thoai = ? AND
                           nguoi_tao = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, data.getIdKhachHang());
            ps.setInt(2, data.getIdNhanVien());
            ps.setInt(3, data.getIdPhieuGiamGia());
            ps.setFloat(4, data.getTongTien());
            ps.setFloat(5, data.getTongTienSauGiam());
            ps.setString(6, data.getTenKhachHang());
            ps.setString(7, data.getSdt());
            ps.setString(8, data.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //New
    public List<HoaDonResponse> showHoaDon() {
        List<HoaDonResponse> list = new ArrayList<>();
        String sql = """
                     SELECT
                         ROW_NUMBER() OVER (
                            ORDER BY hd.id DESC) AS 'stt',
                         hd.id,
                         kh.ten_khach_hang,
                         kh.so_dien_thoai,
                         nv.ma_nhan_vien,
                         hd.ngay_tao,
                         hd.tong_tien,
                         pgg.ma_voucher,
                         hd.tong_tien_sau_giam,
                         hd.hoat_dong
                     FROM
                         HoaDon hd
                     LEFT JOIN
                         KhachHang kh ON kh.id = hd.id_khach_hang
                     LEFT JOIN
                         NhanVien nv ON nv.id = hd.id_nhan_vien
                     LEFT JOIN
                         PhieuGiamGia pgg ON pgg.id = hd.id_phieu_giam_gia;
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(HoaDonResponse.builder()
                        .stt(rs.getInt("stt"))
                        .id(rs.getInt("id"))
                        .tenKhachHang(rs.getString("ten_khach_hang"))
                        .sdt(rs.getString("so_dien_thoai"))
                        .maNhanVien(rs.getString("ma_nhan_vien"))
                        .ngayTao(String.valueOf(rs.getDate("ngay_tao")))
                        .giaTien(rs.getFloat("tong_tien"))
                        .maVouver(rs.getString("ma_voucher"))
                        .tongTienSauGiam(rs.getFloat("tong_tien_sau_giam"))
                        .hoatDong(rs.getInt("hoat_dong"))
                        .build()
                );
            }

        } catch (Exception e) {
            return null;
        }
        return list.isEmpty() ? null : list;
    }

    public List<HoaDonResponse> searchHoaDon(String searchTerm) {
        List<HoaDonResponse> list = new ArrayList<>();
        String sql = """
                 SELECT HoaDon.id, KhachHang.ten_khach_hang, KhachHang.so_dien_thoai,
                        NhanVien.ma_nhan_vien, HoaDon.ngay_tao, HoaDon.tong_tien,
                        PhieuGiamGia.ma_voucher, HoaDon.tong_tien_sau_giam
                 FROM HoaDon
                 JOIN KhachHang ON KhachHang.id = HoaDon.id_khach_hang
                 JOIN NhanVien ON NhanVien.id = HoaDon.id_nhan_vien
                 LEFT JOIN PhieuGiamGia ON PhieuGiamGia.id = HoaDon.id_phieu_giam_gia
                 WHERE HoaDon.hoat_dong = 1
                 AND (HoaDon.id = ? OR KhachHang.so_dien_thoai LIKE ?)
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int id = -1;
            try {
                id = Integer.parseInt(searchTerm);
            } catch (NumberFormatException e) {
            }

            ps.setInt(1, id);
            ps.setString(2, "%" + searchTerm + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonResponse hd = new HoaDonResponse();
                    hd.setId(rs.getInt("id"));
                    hd.setTenKhachHang(rs.getString("ten_khach_hang"));
                    hd.setSdt(rs.getString("so_dien_thoai"));
                    hd.setMaNhanVien(rs.getString("ma_nhan_vien"));
                    hd.setNgayTao(String.valueOf(rs.getDate("ngay_tao")));
                    hd.setGiaTien(rs.getFloat("tong_tien"));
                    hd.setMaVouver(rs.getString("ma_voucher"));
                    hd.setTongTienSauGiam(rs.getFloat("tong_tien_sau_giam"));
                    list.add(hd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
     public List<HoaDonResponse> showHoaDon1() {
        List<HoaDonResponse> list = new ArrayList<>();
        String sql = """
                     SELECT
                                              ROW_NUMBER() OVER (
                                                 ORDER BY hd.id DESC) AS 'stt',
                                              hd.id,
                                              kh.ten_khach_hang,
                                              kh.so_dien_thoai,
                                              nv.ma_nhan_vien,
                                              hd.ngay_tao,
                                              hd.tong_tien,
                                              pgg.ma_voucher,
                                              hd.tong_tien_sau_giam,
                                              hd.hoat_dong
                                          FROM
                                              HoaDon hd
                                          LEFT JOIN
                                              KhachHang kh ON kh.id = hd.id_khach_hang
                                          LEFT JOIN
                                              NhanVien nv ON nv.id = hd.id_nhan_vien
                                          LEFT JOIN
                                              PhieuGiamGia pgg ON pgg.id = hd.id_phieu_giam_gia
                                          where hd.hoat_dong = 1;
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(HoaDonResponse.builder()
                        .stt(rs.getInt("stt"))
                        .id(rs.getInt("id"))
                        .tenKhachHang(rs.getString("ten_khach_hang"))
                        .sdt(rs.getString("so_dien_thoai"))
                        .maNhanVien(rs.getString("ma_nhan_vien"))
                        .ngayTao(String.valueOf(rs.getDate("ngay_tao")))
                        .giaTien(rs.getFloat("tong_tien"))
                        .maVouver(rs.getString("ma_voucher"))
                        .tongTienSauGiam(rs.getFloat("tong_tien_sau_giam"))
                        .hoatDong(rs.getInt("hoat_dong"))
                        .build()
                );
            }

        } catch (Exception e) {
            return null;
        }
        return list.isEmpty() ? null : list;
    }

    public List<HoaDonResponse> showHoaDon2() {
        List<HoaDonResponse> list = new ArrayList<>();
        String sql = """
                     SELECT
                                              ROW_NUMBER() OVER (
                                                 ORDER BY hd.id DESC) AS 'stt',
                                              hd.id,
                                              kh.ten_khach_hang,
                                              kh.so_dien_thoai,
                                              nv.ma_nhan_vien,
                                              hd.ngay_tao,
                                              hd.tong_tien,
                                              pgg.ma_voucher,
                                              hd.tong_tien_sau_giam,
                                              hd.hoat_dong
                                          FROM
                                              HoaDon hd
                                          LEFT JOIN
                                              KhachHang kh ON kh.id = hd.id_khach_hang
                                          LEFT JOIN
                                              NhanVien nv ON nv.id = hd.id_nhan_vien
                                          LEFT JOIN
                                              PhieuGiamGia pgg ON pgg.id = hd.id_phieu_giam_gia
                                          where hd.hoat_dong = 0;
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(HoaDonResponse.builder()
                        .stt(rs.getInt("stt"))
                        .id(rs.getInt("id"))
                        .tenKhachHang(rs.getString("ten_khach_hang"))
                        .sdt(rs.getString("so_dien_thoai"))
                        .maNhanVien(rs.getString("ma_nhan_vien"))
                        .ngayTao(String.valueOf(rs.getDate("ngay_tao")))
                        .giaTien(rs.getFloat("tong_tien"))
                        .maVouver(rs.getString("ma_voucher"))
                        .tongTienSauGiam(rs.getFloat("tong_tien_sau_giam"))
                        .hoatDong(rs.getInt("hoat_dong"))
                        .build()
                );
            }

        } catch (Exception e) {
            return null;
        }
        return list.isEmpty() ? null : list;
    }

    public HoaDonChiTietResponse1 getHoaDonChiTiet(int hoaDonId) {
        String sql = """
                 SELECT hd.id, hd.ngay_tao, hd.tong_tien, hd.tong_tien_sau_giam,
                        nv.ten_nhan_vien, kh.ten_khach_hang, pgg.ma_voucher,
                        chttt.tong_tien as tien_mat,
                        (SELECT tong_tien FROM ChiTietHinhThucThanhToan 
                         WHERE id_hoa_don = hd.id AND id_hinh_thuc_thanh_toan = 2) as chuyen_khoan
                 FROM HoaDon hd
                 LEFT JOIN NhanVien nv ON hd.id_nhan_vien = nv.id
                 LEFT JOIN KhachHang kh ON hd.id_khach_hang = kh.id
                 LEFT JOIN PhieuGiamGia pgg ON hd.id_phieu_giam_gia = pgg.id
                 LEFT JOIN ChiTietHinhThucThanhToan chttt ON hd.id = chttt.id_hoa_don AND chttt.id_hinh_thuc_thanh_toan = 1
                 WHERE hd.id = ?
                 """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, hoaDonId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return HoaDonChiTietResponse1.builder()
                            .id(rs.getInt("id"))
                            .ngayTao(rs.getDate("ngay_tao").toLocalDate())
                            .tongTien(rs.getFloat("tong_tien"))
                            .tongTienSauGiam(rs.getFloat("tong_tien_sau_giam"))
                            .tenNhanVien(rs.getString("ten_nhan_vien"))
                            .tenKhachHang(rs.getString("ten_khach_hang"))
                            .maVoucher(rs.getString("ma_voucher"))
                            .tienMat(rs.getFloat("tien_mat"))
                            .chuyenKhoan(rs.getFloat("chuyen_khoan"))
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
