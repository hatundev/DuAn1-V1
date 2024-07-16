/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.SanPham;
import com.core.model.response.SanPhamResponse;
import com.core.tool.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hatun
 */
public class SanPhamRepository {

    public List<SanPhamResponse> findByPage(int page) {
        String sql = """
                     SELECT stt, ten_san_pham, ten_hang, ten_ram, ten_bo_nho, ten_mau_sac, so_luong, gia_ban
                     FROM (
                         SELECT 
                             ROW_NUMBER() OVER (ORDER BY ctsp.id DESC) AS 'stt',
                             sp.ten_san_pham AS 'ten_san_pham',
                             h.ten AS 'ten_hang',
                             r.ten AS 'ten_ram',
                             bn.ten AS 'ten_bo_nho',
                             ms.ten AS 'ten_mau_sac',
                             (SELECT COUNT(*) FROM Imei i WHERE i.id_chi_tiet_san_pham = ctsp.id) AS 'so_luong',
                             ctsp.gia_ban AS 'gia_ban'
                         FROM ChiTietSanPham ctsp 
                         JOIN SanPham sp ON ctsp.id_san_pham = sp.id 
                         JOIN Hang h ON sp.id_hang = h.id 
                         JOIN Ram r ON ctsp.id_ram = r.id 
                         JOIN BoNho bn ON ctsp.id_bo_nho = bn.id 
                         JOIN MauSac ms ON ctsp.id_mau_sac = ms.id 
                     ) AS RankedProducts
                     WHERE stt >= ? AND  stt < ?;
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<SanPhamResponse> list = new ArrayList<>();
            ps.setInt(1, (page - 1) * 8 + 1);
            ps.setInt(2, page * 8 + 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamResponse sp = new SanPhamResponse();
                sp.setStt(rs.getInt("stt"));
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
                           id_pin = ?
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

    public boolean create(SanPham sp) {
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
            ps.setString(9, "Null");
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

}
