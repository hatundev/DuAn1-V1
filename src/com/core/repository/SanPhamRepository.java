/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.SanPham;
import com.core.tool.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hatun
 */
public class SanPhamRepository {

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
                     		id_pin,
                     		ngay_tao,
                     		nguoi_tao,
                     		hoat_dong
                     	)
                     VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getIdHeDieuHanh());
            ps.setInt(3, sp.getIdManHinh());
            ps.setInt(4, sp.getIdCameraTruoc());
            ps.setInt(5, sp.getIdCameraSau());
            ps.setInt(6, sp.getIdChip());
            ps.setInt(7, sp.getIdPin());
            ps.setString(8, "Null");
            ps.setInt(9, 1);
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
