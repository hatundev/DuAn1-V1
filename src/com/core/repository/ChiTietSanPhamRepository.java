/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.ChiTietSanPham;
import com.core.tool.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hatun
 */
public class ChiTietSanPhamRepository {

    public Integer findIdBySanPham(ChiTietSanPham ctsp) {
        String sql = """
                     SELECT id
                     FROM ChiTietSanPham
                     WHERE ten_san_pham_chi_tiet = ? and
                           id_san_pham = ? and
                           id_ram = ? and
                           id_bo_nho = ? and
                           id_mau_sac = ? and
                           gia_ban = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ctsp.getTenSanPhamChiTiet());
            ps.setInt(2, ctsp.getIdSanPham());
            ps.setInt(3, ctsp.getIdRam());
            ps.setInt(4, ctsp.getIdBoNho());
            ps.setInt(5, ctsp.getIdMauSac());
            ps.setFloat(6, ctsp.getGiaBan());
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
    
    public boolean create(ChiTietSanPham ctsp) {
        String sql = """
                     INSERT INTO
                     	ChiTietSanPham (
                     		ten_san_pham_chi_tiet,
                     		id_san_pham,
                     		id_ram,
                     		id_bo_nho,
                     		id_mau_sac,
                     		gia_ban,
                     		ngay_tao,
                     		nguoi_tao,
                     		yeu_thich,
                     		hoat_dong
                     	)
                     VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ctsp.getTenSanPhamChiTiet());
            ps.setInt(2, ctsp.getIdSanPham());
            ps.setInt(3, ctsp.getIdRam());
            ps.setInt(4, ctsp.getIdBoNho());
            ps.setInt(5, ctsp.getIdMauSac());
            ps.setFloat(6, ctsp.getGiaBan());
            ps.setString(7, "null");
            ps.setInt(8, 0);
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
