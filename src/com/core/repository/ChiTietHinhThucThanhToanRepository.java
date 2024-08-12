/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.tool.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author hatun
 */
public class ChiTietHinhThucThanhToanRepository {
    
    public boolean createTienMat(int idhdct, float tienmat) {
        String sql = """
                INSERT INTO ChiTietHinhThucThanhToan(id_hoa_don, id_hinh_thuc_thanh_toan, tong_tien, hoat_dong)
                VALUES (?, 1, ?, 1)
                """;

        try (Connection conn = DBConnect.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, idhdct); // Cài đặt id_hoa_don
            ps.setFloat(2, tienmat); // Cài đặt tong_tien

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0; // Trả về true nếu có ít nhất 1 hàng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console nếu có
            return false;
        }
    }
    
    public boolean createChuyenKhoan(int idhdct, float tienmat) {
        String sql = """
                INSERT INTO ChiTietHinhThucThanhToan(id_hoa_don, id_hinh_thuc_thanh_toan, tong_tien, hoat_dong)
                VALUES (?, 2, ?, 1)
                """;

        try (Connection conn = DBConnect.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, idhdct); // Cài đặt id_hoa_don
            ps.setFloat(2, tienmat); // Cài đặt tong_tien

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0; // Trả về true nếu có ít nhất 1 hàng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console nếu có
            return false;
        }
    }
    
}
