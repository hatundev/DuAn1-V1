/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.SanPham;
import com.core.model.request.TimKiemRequest;
import com.core.model.response.ChiTietSanPhamResponse;
import com.core.model.response.SanPhamResponse;
import com.core.tool.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hatun
 */
public class SanPhamRepository {

    public List<SanPhamResponse> findByPage() {
        String sql = """
                     SELECT stt,id, ten_san_pham, ten_hang, ten_ram, ten_bo_nho, ten_mau_sac, so_luong, gia_ban
                     FROM (
                         SELECT 
                             ROW_NUMBER() OVER (ORDER BY ctsp.id DESC) AS 'stt',
                             ctsp.id as 'id',
                             sp.ten_san_pham AS 'ten_san_pham',
                             h.ten AS 'ten_hang',
                             r.ten AS 'ten_ram',
                             bn.ten AS 'ten_bo_nho',
                             ms.ten AS 'ten_mau_sac',
                             (SELECT COUNT(*) FROM Imei i WHERE i.id_chi_tiet_san_pham = ctsp.id and i.hoat_dong= 1) AS 'so_luong',
                             ctsp.gia_ban AS 'gia_ban'
                         FROM ChiTietSanPham ctsp 
                         JOIN SanPham sp ON ctsp.id_san_pham = sp.id 
                         JOIN Hang h ON sp.id_hang = h.id 
                         JOIN Ram r ON ctsp.id_ram = r.id 
                         JOIN BoNho bn ON ctsp.id_bo_nho = bn.id 
                         JOIN MauSac ms ON ctsp.id_mau_sac = ms.id 
                         WHERE ctsp.hoat_dong = 1
                     ) AS RankedProducts
                     
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<SanPhamResponse> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamResponse sp = new SanPhamResponse();
                sp.setStt(rs.getInt("stt"));
                sp.setId(rs.getInt("id"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setHang(rs.getString("ten_hang"));
                sp.setRam(rs.getString("ten_ram"));
                sp.setBoNho(rs.getString("ten_bo_nho"));
                sp.setMauSac(rs.getString("ten_mau_sac"));
                sp.setSoLuong(rs.getInt("so_luong"));
                sp.setGia(rs.getFloat("gia_ban"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<SanPhamResponse> findByPage(TimKiemRequest request) {
        StringBuilder sql = new StringBuilder("""
                        SELECT 
                            ROW_NUMBER() OVER (ORDER BY ctsp.id DESC) AS 'stt',
                            ctsp.id as 'id',
                            CONCAT_WS(' ', sp.ten_san_pham, hdh.ten, c.ten, p.ten, mh.ten, ct.ten, cs.ten) AS 'ten_san_pham',
                            h.ten AS 'ten_hang',
                            r.ten AS 'ten_ram',
                            bn.ten AS 'ten_bo_nho',
                            ms.ten AS 'ten_mau_sac',
                            (SELECT COUNT(*) FROM Imei i WHERE i.id_chi_tiet_san_pham = ctsp.id and i.hoat_dong= 1) AS 'so_luong',
                            ctsp.gia_ban AS 'gia_ban'
                        FROM ChiTietSanPham ctsp 
                        LEFT JOIN SanPham sp ON ctsp.id_san_pham = sp.id 
                        LEFT JOIN Hang h ON sp.id_hang = h.id
                        LEFT JOIN HeDieuHanh hdh ON sp.id_he_dieu_hanh = hdh.id
                        LEFT JOIN ManHinh mh ON sp.id_man_hinh = mh.id
                        LEFT JOIN CameraTruoc ct ON sp.id_camera_truoc = ct.id
                        LEFT JOIN CameraSau cs ON sp.id_camera_sau = cs.id
                        LEFT JOIN Chip c ON sp.id_chip= c.id
                        LEFT JOIN Pin p ON sp.id_pin= p.id
                        LEFT JOIN Ram r ON ctsp.id_ram = r.id 
                        LEFT JOIN BoNho bn ON ctsp.id_bo_nho = bn.id 
                        LEFT JOIN MauSac ms ON ctsp.id_mau_sac = ms.id 
                        WHERE ctsp.hoat_dong = 1 AND 
                              ctsp.gia_ban >= ? AND
                              ctsp.gia_ban <= ?
                        """);

        if (request.getTimKiem() != null && !request.getTimKiem().isEmpty()) {
            sql.append(" AND sp.ten_san_pham like ?");
        }
        if (request.getIdHang() != null) {
            sql.append(" AND h.id = ?");
        }
        if (request.getIdRam() != null) {
            sql.append(" AND r.id = ?");
        }
        if (request.getIdBoNho() != null) {
            sql.append(" AND bn.id = ?");
        }
        if (request.getIdMauSac() != null) {
            sql.append(" AND ms.id = ?");
        }

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            ps.setFloat(paramIndex++, request.getGiaBatDau());
            ps.setFloat(paramIndex++, request.getGiaKetThuc());
            if (request.getTimKiem() != null && !request.getTimKiem().isEmpty()) {
                ps.setString(paramIndex++, "%" + request.getTimKiem() + "%");
            }
            if (request.getIdHang() != null) {
                ps.setInt(paramIndex++, request.getIdHang());
            }
            if (request.getIdRam() != null) {
                ps.setInt(paramIndex++, request.getIdRam());
            }
            if (request.getIdBoNho() != null) {
                ps.setInt(paramIndex++, request.getIdBoNho());
            }
            if (request.getIdMauSac() != null) {
                ps.setInt(paramIndex++, request.getIdMauSac());
            }
            
            List<SanPhamResponse> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamResponse sp = new SanPhamResponse();
                sp.setStt(rs.getInt(1));
                sp.setId(rs.getInt(2));
                sp.setTenSanPham(rs.getString(3));
                sp.setHang(rs.getString(4));
                sp.setRam(rs.getString(5));
                sp.setBoNho(rs.getString(6));
                sp.setMauSac(rs.getString(7));
                sp.setSoLuong(rs.getInt(8));
                sp.setGia(rs.getFloat(9));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamResponse> findByName(String name) {
        String sql = """
                     SELECT 
                        ROW_NUMBER() OVER (ORDER BY ctsp.id DESC) AS 'stt',
                        ctsp.id as 'id',
                        sp.ten_san_pham AS 'ten_san_pham',
                        h.ten AS 'ten_hang',
                        r.ten AS 'ten_ram',
                        bn.ten AS 'ten_bo_nho',
                        ms.ten AS 'ten_mau_sac',
                        (SELECT COUNT(*) FROM Imei i WHERE i.id_chi_tiet_san_pham = ctsp.id and i.hoat_dong= 1) AS 'so_luong',
                        ctsp.gia_ban AS 'gia_ban'
                    FROM ChiTietSanPham ctsp 
                    JOIN SanPham sp ON ctsp.id_san_pham = sp.id 
                    JOIN Hang h ON sp.id_hang = h.id 
                    JOIN Ram r ON ctsp.id_ram = r.id 
                    JOIN BoNho bn ON ctsp.id_bo_nho = bn.id 
                    JOIN MauSac ms ON ctsp.id_mau_sac = ms.id 
                    WHERE ctsp.hoat_dong = 1 and ctsp.ten_san_pham_chi_tiet like ?
                     
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<SanPhamResponse> list = new ArrayList<>();
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamResponse sp = new SanPhamResponse();
                sp.setStt(rs.getInt("stt"));
                sp.setId(rs.getInt("id"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setHang(rs.getString("ten_hang"));
                sp.setRam(rs.getString("ten_ram"));
                sp.setBoNho(rs.getString("ten_bo_nho"));
                sp.setMauSac(rs.getString("ten_mau_sac"));
                sp.setSoLuong(rs.getInt("so_luong"));
                sp.setGia(rs.getFloat("gia_ban"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getSize() {
        String sql = """
                     SELECT COUNT(*) AS 'size'
                     FROM ChiTietSanPham
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int result = rs.getInt("size");
                return result;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Integer findIdBySanPham(SanPham sp) {
        String sql = """
                     SELECT id
                     FROM SanPham
                     WHERE ten_san_pham = ? and
                           id_he_dieu_hanh = ? and
                           id_man_hinh = ? and
                           id_camera_truoc = ? and
                           id_camera_sau = ? and
                           id_chip = ? and
                           id_pin = ? and
                           hoat_dong = 1
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getIdHeDieuHanh());
            ps.setInt(3, sp.getIdManHinh());
            ps.setInt(4, sp.getIdCameraTruoc());
            ps.setInt(5, sp.getIdCameraSau());
            ps.setInt(6, sp.getIdChip());
            ps.setInt(7, sp.getIdPin());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int result = rs.getInt("id");
                return result;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(SanPham sp, String username) {
        String sql = """
                     INSERT INTO
                     	SanPham (
                     		ten_san_pham,
                     		id_he_dieu_hanh,
                     		id_man_hinh,
                     		id_camera_truoc,
                     		id_camera_sau,
                     		id_chip,
                                id_hang,
                     		id_pin,
                     		ngay_tao,
                     		nguoi_tao,
                     		hoat_dong
                     	)
                     VALUES (?, ?, ?, ?, ?, ?,?, ?, GETDATE(), ?, ?)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getIdHeDieuHanh());
            ps.setInt(3, sp.getIdManHinh());
            ps.setInt(4, sp.getIdCameraTruoc());
            ps.setInt(5, sp.getIdCameraSau());
            ps.setInt(6, sp.getIdChip());
            ps.setInt(7, sp.getIdHang());
            ps.setInt(8, sp.getIdPin());
            ps.setString(9, username);
            ps.setInt(10, 1);
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(SanPham sp, int id) {
        String sql = """
                 UPDATE 
                 	SanPham 
                 SET 
                 		ten_san_pham = ?,
                 		id_he_dieu_hanh = ?,
                 		id_man_hinh = ?,
                 		id_camera_truoc = ?,
                 		id_camera_sau = ?,
                 		id_chip = ?,
                        id_hang = ?,
                 		id_pin = ?
                 WHERE id = ?
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getIdHeDieuHanh());
            ps.setInt(3, sp.getIdManHinh());
            ps.setInt(4, sp.getIdCameraTruoc());
            ps.setInt(5, sp.getIdCameraSau());
            ps.setInt(6, sp.getIdChip());
            ps.setInt(7, sp.getIdHang());
            ps.setInt(8, sp.getIdPin());
            ps.setInt(9, id);

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            // Log lỗi hoặc in ra thông báo lỗi
            e.printStackTrace();
            return false;
        }
    }

}
