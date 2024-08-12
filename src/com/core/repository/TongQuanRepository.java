/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.core.model.response.TongQuanResponse;
import com.core.model.response.Top1Sale;
import com.core.tool.DBConnect;

/**
 *
 * @author hatun
 */
public class TongQuanRepository {

    public TongQuanResponse getDay() {
        String sql = """
                     SELECT
                         SUM(hd.tong_tien_sau_giam) AS TongTien,
                         (
                             SELECT COUNT(*)
                             FROM HoaDon hd2
                             WHERE hd2.hoat_dong = 1
                             AND CONVERT(DATE, hd2.ngay_tao) = CONVERT(DATE, GETDATE())
                         ) AS SoDonHoanThanh,
                         (
                             SELECT COUNT(*)
                             FROM HoaDon hd3
                             WHERE hd3.hoat_dong = 0
                             AND CONVERT(DATE, hd3.ngay_tao) = CONVERT(DATE, GETDATE())
                         ) AS SoDonChuaHoanThanh,
                         (
                             SELECT COUNT(*)
                             FROM HoaDon hd4
                             JOIN HoaDonChiTiet hdct ON hd4.id = hdct.id_hoa_don
                             WHERE CONVERT(DATE, hd4.ngay_tao) = CONVERT(DATE, GETDATE())
                             AND hd4.hoat_dong = 1
                         ) AS SoSanPhamDaBan
                     FROM HoaDon hd
                     WHERE CONVERT(DATE, hd.ngay_tao) = CONVERT(DATE, GETDATE())
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TongQuanResponse data = TongQuanResponse.builder()
                        .tongTien(rs.getFloat("TongTien"))
                        .soDonHoanThanh(rs.getInt("SoDonHoanThanh"))
                        .soDonChuaHoanThanh(rs.getInt("SoDonChuaHoanThanh"))
                        .soSanPhamDaBan(rs.getInt("SoSanPhamDaBan"))
                        .build();
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TongQuanResponse getLastDay() {
        String sql = """
                 SELECT
                     SUM(hd.tong_tien_sau_giam) AS TongTien,
                     (
                         SELECT COUNT(*)
                         FROM HoaDon hd2
                         WHERE hd2.hoat_dong = 1
                         AND CONVERT(DATE, hd2.ngay_tao) = CONVERT(DATE, DATEADD(DAY, -1, GETDATE()))
                     ) AS SoDonHoanThanh,
                     (
                         SELECT COUNT(*)
                         FROM HoaDon hd3
                         WHERE hd3.hoat_dong = 0
                         AND CONVERT(DATE, hd3.ngay_tao) = CONVERT(DATE, DATEADD(DAY, -1, GETDATE()))
                     ) AS SoDonChuaHoanThanh,
                     (
                         SELECT COUNT(*)
                         FROM HoaDon hd4
                         JOIN HoaDonChiTiet hdct ON hd4.id = hdct.id_hoa_don
                         WHERE CONVERT(DATE, hd4.ngay_tao) = CONVERT(DATE, DATEADD(DAY, -1, GETDATE()))
                         AND hd4.hoat_dong = 1
                     ) AS SoSanPhamDaBan
                 FROM HoaDon hd
                 WHERE CONVERT(DATE, hd.ngay_tao) = CONVERT(DATE, DATEADD(DAY, -1, GETDATE()))
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TongQuanResponse data = TongQuanResponse.builder()
                        .tongTien(rs.getFloat("TongTien"))
                        .soDonHoanThanh(rs.getInt("SoDonHoanThanh"))
                        .soDonChuaHoanThanh(rs.getInt("SoDonChuaHoanThanh"))
                        .soSanPhamDaBan(rs.getInt("SoSanPhamDaBan"))
                        .build();
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TongQuanResponse getWeek() {
        String sql = """
                 SELECT
                     SUM(hd.tong_tien_sau_giam) AS TongTien,
                     (
                         SELECT COUNT(*)
                         FROM HoaDon hd2
                         WHERE hd2.hoat_dong = 1
                         AND CONVERT(DATE, hd2.ngay_tao) >= CONVERT(DATE, DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), GETDATE()))
                         AND CONVERT(DATE, hd2.ngay_tao) <= CONVERT(DATE, GETDATE())
                     ) AS SoDonHoanThanh
                 FROM HoaDon hd
                 WHERE CONVERT(DATE, hd.ngay_tao) >= CONVERT(DATE, DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), GETDATE()))
                 AND CONVERT(DATE, hd.ngay_tao) <= CONVERT(DATE, GETDATE())
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TongQuanResponse data = TongQuanResponse.builder()
                        .tongTien(rs.getFloat("TongTien"))
                        .soDonHoanThanh(rs.getInt("SoDonHoanThanh"))
                        .build();
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TongQuanResponse getLastWeek() {
        String sql = """
                 SELECT
                     SUM(hd.tong_tien_sau_giam) AS TongTien,
                     (
                         SELECT COUNT(*)
                         FROM HoaDon hd2
                         WHERE hd2.hoat_dong = 1
                         AND CONVERT(DATE, hd2.ngay_tao) >= CONVERT(DATE, DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), GETDATE()) - 7)
                         AND CONVERT(DATE, hd2.ngay_tao) < CONVERT(DATE, DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), GETDATE()))
                     ) AS SoDonHoanThanh
                 FROM HoaDon hd
                 WHERE CONVERT(DATE, hd.ngay_tao) >= CONVERT(DATE, DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), GETDATE()) - 7)
                 AND CONVERT(DATE, hd.ngay_tao) < CONVERT(DATE, DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), GETDATE()))
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TongQuanResponse data = TongQuanResponse.builder()
                        .tongTien(rs.getFloat("TongTien"))
                        .soDonHoanThanh(rs.getInt("SoDonHoanThanh"))
                        .build();
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TongQuanResponse getMonth() {
        String sql = """
                 SELECT
                     SUM(hd.tong_tien_sau_giam) AS TongTien,
                     (
                         SELECT COUNT(*)
                         FROM HoaDon hd2
                         WHERE hd2.hoat_dong = 1
                         AND MONTH(hd2.ngay_tao) = MONTH(GETDATE())
                         AND YEAR(hd2.ngay_tao) = YEAR(GETDATE())
                     ) AS SoDonHoanThanh
                 FROM HoaDon hd
                 WHERE MONTH(hd.ngay_tao) = MONTH(GETDATE())
                 AND YEAR(hd.ngay_tao) = YEAR(GETDATE())
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TongQuanResponse data = TongQuanResponse.builder()
                        .tongTien(rs.getFloat("TongTien"))
                        .soDonHoanThanh(rs.getInt("SoDonHoanThanh"))
                        .build();
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TongQuanResponse getLastMonth() {
        String sql = """
                 SELECT
                     SUM(hd.tong_tien_sau_giam) AS TongTien,
                     (
                         SELECT COUNT(*)
                         FROM HoaDon hd2
                         WHERE hd2.hoat_dong = 1
                         AND MONTH(hd2.ngay_tao) = MONTH(DATEADD(MONTH, -1, GETDATE()))
                         AND YEAR(hd2.ngay_tao) = YEAR(DATEADD(MONTH, -1, GETDATE()))
                     ) AS SoDonHoanThanh
                 FROM HoaDon hd
                 WHERE MONTH(hd.ngay_tao) = MONTH(DATEADD(MONTH, -1, GETDATE()))
                 AND YEAR(hd.ngay_tao) = YEAR(DATEADD(MONTH, -1, GETDATE()))
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TongQuanResponse data = TongQuanResponse.builder()
                        .tongTien(rs.getFloat("TongTien"))
                        .soDonHoanThanh(rs.getInt("SoDonHoanThanh"))
                        .build();
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    WITH DoanhThuTheoNhanVien AS (
//                         SELECT 
//                             nv.ma_nhan_vien,
//                             nv.ten_nhan_vien,
//                             SUM(hd.tong_tien_sau_giam) AS doanh_so,
//                             COUNT(hd.id) AS so_hoa_don,
//                             COUNT(hdct.id) AS so_san_pham_ban
//                         FROM 
//                             NhanVien nv
//                         LEFT JOIN 
//                             HoaDon hd ON nv.id = hd.id_nhan_vien
//                         LEFT JOIN 
//                             HoaDonChiTiet hdct ON hd.id = hdct.id_hoa_don
//                         WHERE 
//                             MONTH(hd.ngay_tao) = MONTH(GETDATE()) 
//                             AND YEAR(hd.ngay_tao) = YEAR(GETDATE())
//                             AND hd.hoat_dong = 1
//                         GROUP BY 
//                             nv.ma_nhan_vien,
//                             nv.ten_nhan_vien
//                     )
//                     SELECT 
//                         ma_nhan_vien,
//                         ten_nhan_vien,
//                         doanh_so,
//                         so_hoa_don,
//                         so_san_pham_ban
//                     FROM 
//                         DoanhThuTheoNhanVien
//                     WHERE 
//                         doanh_so = (SELECT MAX(doanh_so) FROM DoanhThuTheoNhanVien);
    public Top1Sale getTop1Sale() {
        String sql = """
                     WITH DoanhThuTheoNhanVien AS (
                         SELECT
                             TOP 1 nv.ma_nhan_vien,
                             nv.ten_nhan_vien,
                             SUM( hd.tong_tien_sau_giam) AS doanh_so, 
                             COUNT(DISTINCT hd.id) AS so_hoa_don,
                              COUNT(DISTINCT hdct.id) AS so_san_pham_ban
                         FROM
                             NhanVien nv
                         JOIN 
                             HoaDon hd ON nv.id = hd.id_nhan_vien
                         JOIN 
                             HoaDonChiTiet hdct ON hd.id = hdct.id_hoa_don
                         WHERE
                             hd.hoat_dong = 1
                             AND MONTH(hd.ngay_tao) = MONTH(GETDATE())
                             AND YEAR(hd.ngay_tao) = YEAR(GETDATE())
                         GROUP BY
                             nv.ma_nhan_vien,
                             nv.ten_nhan_vien
                     )
                     SELECT 
                         ma_nhan_vien,
                         ten_nhan_vien,
                         doanh_so,
                         so_hoa_don,
                         so_san_pham_ban
                     FROM 
                         DoanhThuTheoNhanVien
                     WHERE 
                         doanh_so = (SELECT MAX(doanh_so) FROM DoanhThuTheoNhanVien);
                     """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Top1Sale nvDoanhThu = new Top1Sale();
                nvDoanhThu.setMaNhanVien(rs.getString("ma_nhan_vien"));
                nvDoanhThu.setTenNhanVien(rs.getString("ten_nhan_vien"));
                nvDoanhThu.setDoanhSo(rs.getFloat("doanh_so"));
                nvDoanhThu.setSoHoaDon(rs.getInt("so_hoa_don"));
                nvDoanhThu.setSoSanPhamBan(rs.getInt("so_san_pham_ban"));
                return nvDoanhThu;
            } else {
                return null;
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        TongQuanRepository repo = new TongQuanRepository();
//        System.out.println(repo.getDay().toString());
//        System.out.println(repo.getLastDay().toString());
//        System.out.println(repo.getWeek().toString());
//        System.out.println(repo.getLastWeek().toString());
//        System.out.println(repo.getMonth().toString());
//        System.out.println(repo.getLastMonth().toString());
        System.out.println(repo.getTop1Sale().toString());
    }

}

