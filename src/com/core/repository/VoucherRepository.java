/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.model.request.VoucherBanHangResponse;
import com.core.model.response.SanPhamResponse;
import com.core.model.response.VouCherResponse;
import com.core.tool.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hatundev
 */
public class VoucherRepository {

    public List<VoucherBanHangResponse> getComBo(Float total) {
        String sql = """
                    SELECT 
                            ROW_NUMBER() OVER (ORDER BY v.id DESC) AS 'stt',
                            v.id AS 'id',
                            v.ma_voucher AS 'ma',
                            v.ten_voucher AS 'ten',
                            v.so_luong AS 'soLuong',
                            v.gia_tri_toi_thieu_ap_dung AS 'giaTriToiThieuApDung',
                            v.phan_tram_giam AS 'phanTramGiam',
                            v.so_tien_duoc_giam_toi_da AS 'soTienDuocGiamToiDa'
                    FROM 
                        PhieuGiamGia v 
                        LEFT JOIN (
                            SELECT 
                                id_phieu_giam_gia, 
                                COUNT(*) AS so_luong_da_su_dung
                            FROM 
                                HoaDon
                            GROUP BY 
                                id_phieu_giam_gia
                        ) hd ON v.id = hd.id_phieu_giam_gia
                    WHERE 
                            v.hoat_dong = 1 and 
                            v.ngay_bat_dau <= GETDATE() AND 
                            v.ngay_ket_thuc >= GETDATE() AND 
                            v.gia_tri_toi_thieu_ap_dung <= ? AND
                            (hd.so_luong_da_su_dung IS NULL OR hd.so_luong_da_su_dung < v.so_luong)
                    """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<VoucherBanHangResponse> list = new ArrayList<>();
            ps.setFloat(1, total);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VoucherBanHangResponse voucher = new VoucherBanHangResponse();
                voucher.setStt(rs.getInt("stt"));
                voucher.setId(rs.getInt("id"));
                voucher.setMaVoucher(rs.getString("ma"));
                voucher.setTenVoucher(rs.getString("ten"));
                voucher.setSoLuong(rs.getInt("soLuong"));
                voucher.setGiaTriToiThieuApDung(rs.getFloat("giaTriToiThieuApDung"));
                voucher.setPhanTramGiam(rs.getInt("phanTramGiam"));
                voucher.setSoTienDuocGiamToiDa(rs.getFloat("soTienDuocGiamToiDa"));
                list.add(voucher);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<VouCherResponse> getShowVC() {
        List<VouCherResponse> list = new ArrayList<>();
        String sql = """
                    SELECT 
                        p.id,
                        ROW_NUMBER() OVER(ORDER BY p.id DESC) AS 'STT', 
                        p.ma_voucher AS 'Mã Phiếu Giảm Giá', 
                        p.ten_voucher AS 'Tên Phiếu Giảm Giá', 
                        p.gia_tri_toi_thieu_ap_dung AS 'Giá Trị Tối Thiểu Áp Dụng',
                        p.phan_tram_giam AS 'Phần Trăm Giảm',
                        p.so_tien_duoc_giam_toi_da AS 'Số Tiền Được Giảm Tối Đa',
                        p.ngay_bat_dau AS 'Ngày Bắt Đầu',
                        p.ngay_ket_thuc AS 'Ngày Kết Thúc',
                        p.ngay_tao AS 'Ngày Tạo',
                        p.nguoi_tao AS 'Người Tạo',
                        (p.so_luong - (SELECT Count(*) from HoaDon where id_phieu_giam_gia = p.id)) as 'Số Lượng'
                    FROM PhieuGiamGia p
                    """;

        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();

            while (rs.next()) {
                VouCherResponse vc = new VouCherResponse();
                vc.setId(rs.getInt("id"));
                vc.setSTT(rs.getInt("STT"));
                vc.setMaVouCher(rs.getString("Mã Phiếu Giảm Giá"));
                vc.setTenVouCher(rs.getString("Tên Phiếu Giảm Giá"));
                vc.setGiaTriToiThieuApDung(rs.getFloat("Giá Trị Tối Thiểu Áp Dụng"));
                vc.setPhanTramGiam(rs.getInt("Phần Trăm Giảm"));
                vc.setSoTienDuocGiamToiDa(rs.getFloat("Số Tiền Được Giảm Tối Đa"));
                vc.setNgayBatDau(rs.getString("Ngày Bắt Đầu"));
                vc.setNgayKetThuc(rs.getString("Ngày Kết Thúc"));
                vc.setNgayTao(rs.getString("Ngày Tạo"));
                vc.setNguoiTao(rs.getString("Người Tạo"));
                vc.setSoLuong(rs.getInt("Số Lượng"));
                list.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<VouCherResponse> searchByName(String ma) {
        List<VouCherResponse> list = new ArrayList<>();
        String sql = """
                    SELECT 
                        p.id, 
                        ROW_NUMBER() OVER(ORDER BY p.id DESC) AS 'STT', 
                        p.ma_voucher as 'Mã Phiếu Giảm Giá', 
                        p.ten_voucher as 'Tên Phiếu Giảm Giá', 
                        p.gia_tri_toi_thieu_ap_dung as 'Giá Trị Tối Thiểu Áp Dụng', 
                        p.phan_tram_giam as 'Phần Trăm Giảm', 
                        p.so_tien_duoc_giam_toi_da as 'Số Tiền Được Giảm Tối Đa', 
                        p.ngay_bat_dau as 'Ngày Bắt Đầu', 
                        p.ngay_ket_thuc as 'Ngày Kết Thúc', 
                        p.ngay_tao as 'Ngày Tạo', 
                        p.nguoi_tao as 'Người Tạo', 
                        (p.so_luong - (SELECT Count(*) from HoaDon where id_phieu_giam_gia = p.id)) as 'Số Lượng'
                    FROM PhieuGiamGia p 
                    WHERE p.ma_voucher LIKE ?
                    """;

        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, "%" + ma + "%");
            ResultSet rs = sttm.executeQuery();

            while (rs.next()) {
                VouCherResponse vc = new VouCherResponse();
                vc.setSTT(rs.getInt("STT"));
                vc.setMaVouCher(rs.getString("Mã Phiếu Giảm Giá"));
                vc.setTenVouCher(rs.getString("Tên Phiếu Giảm Giá"));
                vc.setGiaTriToiThieuApDung(rs.getFloat("Giá Trị Tối Thiểu Áp Dụng"));
                vc.setPhanTramGiam(rs.getInt("Phần Trăm Giảm"));
                vc.setSoTienDuocGiamToiDa(rs.getFloat("Số Tiền Được Giảm Tối Đa"));
                vc.setNgayBatDau(rs.getString("Ngày Bắt Đầu"));
                vc.setNgayKetThuc(rs.getString("Ngày Kết Thúc"));
                vc.setNgayTao(rs.getString("Ngày Tạo"));
                vc.setNguoiTao(rs.getString("Người Tạo"));
                vc.setSoLuong(rs.getInt("Số Lượng"));
                list.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean getADD(VouCherResponse vc) {
        String sql = """
                 INSERT INTO PhieuGiamGia(ma_voucher,ten_voucher,gia_tri_toi_thieu_ap_dung,phan_tram_giam,so_tien_duoc_giam_toi_da,ngay_bat_dau,ngay_ket_thuc,ngay_tao,nguoi_tao, so_luong,hoat_dong)
                 VALUES (?,?,?,?,?,?,?,GETDATE(),'admin',?,1);
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement sttm = con.prepareStatement(sql)) {

            sttm.setString(1, vc.getMaVouCher());
            sttm.setString(2, vc.getTenVouCher());
            sttm.setFloat(3, vc.getGiaTriToiThieuApDung());
            sttm.setInt(4, vc.getPhanTramGiam());
            sttm.setFloat(5, vc.getSoTienDuocGiamToiDa());
            sttm.setString(6, vc.getNgayBatDau());
            sttm.setString(7, vc.getNgayKetThuc());
            sttm.setInt(8, vc.getSoLuong());

            int rowsAffected = sttm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No rows affected. Insert may have failed.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteVouCher(int id) {
        String sql = "delete PhieuGiamGia where id = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, id);
            return sttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateVouCher(VouCherResponse vc) {
        String sql = """
                     UPDATE PhieuGiamGia 
                     	set
                     	ma_voucher = ?,
                     	ten_voucher = ?,
                     	so_luong = ?,
                     	gia_tri_toi_thieu_ap_dung = ?,
                     	phan_tram_giam = ?,
                     	so_tien_duoc_giam_toi_da = ?,
                     	ngay_bat_dau = ?,
                     	ngay_ket_thuc = ?,
                     	mo_ta = ?,
                     	ngay_sua = GETDATE(),
                     	nguoi_tao = ?,
                     	nguoi_sua = ?,
                     	hoat_dong = ?
                     	where id = ?
                     """;
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, vc.getMaVouCher());
            sttm.setString(2, vc.getTenVouCher());
            sttm.setInt(3, vc.getSoLuong());
            sttm.setFloat(4, vc.getGiaTriToiThieuApDung());
            sttm.setInt(5, vc.getPhanTramGiam());
            sttm.setFloat(6, vc.getSoTienDuocGiamToiDa());
            sttm.setString(7, vc.getNgayBatDau());
            sttm.setString(8, vc.getNgayKetThuc());
            sttm.setString(9, vc.getMoTa());
            sttm.setString(10, "admin");
            sttm.setString(11, "admin");
            sttm.setInt(12, vc.getHoatDong());
            sttm.setInt(13, vc.getId());
            if (sttm.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public VouCherResponse getVoucherByMa(String ma) throws SQLException {
        VouCherResponse voucher = null;
        String sql = """
                 SELECT P.id, ROW_NUMBER() OVER(ORDER BY p.id DESC) AS STT,
                     P.ma_voucher,
                     P.ten_voucher,
                     (P.so_luong - (SELECT Count(*) from HoaDon where id_phieu_giam_gia = P.id)) as 'so_luong',
                     P.gia_tri_toi_thieu_ap_dung,
                     P.phan_tram_giam,
                     P.so_tien_duoc_giam_toi_da,
                     P.ngay_bat_dau,
                     P.ngay_ket_thuc,
                     P.mo_ta,
                     P.ngay_tao,
                     P.ngay_sua,
                     p.nguoi_tao,
                     p.nguoi_sua,
                     p.hoat_dong
                 FROM PhieuGiamGia P
                 WHERE ma_voucher = ?
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ma);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    voucher = new VouCherResponse();
                    voucher.setId(rs.getInt("id"));
                    voucher.setSTT(rs.getInt("STT"));
                    voucher.setMaVouCher(rs.getString("ma_voucher"));
                    voucher.setTenVouCher(rs.getString("ten_voucher"));
                    voucher.setSoLuong(rs.getInt("so_luong"));
                    voucher.setGiaTriToiThieuApDung(rs.getFloat("gia_tri_toi_thieu_ap_dung"));
                    voucher.setPhanTramGiam(rs.getInt("phan_tram_giam"));
                    voucher.setSoTienDuocGiamToiDa(rs.getFloat("so_tien_duoc_giam_toi_da"));
                    voucher.setNgayBatDau(rs.getString("ngay_bat_dau"));
                    voucher.setNgayKetThuc(rs.getString("ngay_ket_thuc"));
                    voucher.setMoTa(rs.getString("mo_ta"));
                    voucher.setNgayTao(rs.getString("ngay_tao"));
                    voucher.setNgaySua(rs.getString("ngay_sua"));
                    voucher.setNguoiTao(rs.getString("nguoi_tao"));
                    voucher.setNguoiSua(rs.getString("nguoi_sua"));
                    voucher.setHoatDong(rs.getInt("hoat_dong"));
                }
            }
        }
        return voucher;
    }

    public static void main(String[] args) {
        VoucherRepository repository = new VoucherRepository();
        System.out.println(repository.getComBo(Float.valueOf(500)).size());
    }
}
