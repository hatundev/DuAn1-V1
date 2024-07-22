/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.KetQua;
import com.core.model.request.HoaDonRequest;
import com.core.tool.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author hatundev
 */
public class HoaDonRepository {

    public KetQua create(HoaDonRequest data) {
        String sql = """
                     INSERT
                     	INTO
                     	HoaDon (id_khach_hang,
                     	id_nhan_vien,
                     	id_phieu_giam_gia,
                     	tong_tien,
                        tong_tien_sau_giam,
                     	ten_khach_hang,
                     	so_dien_thoai,
                     	ngay_tao,
                     	nguoi_tao,
                     	hoat_dong)
                     VALUES ( ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, 1)
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
            int result = ps.executeUpdate();
            if (result != 0) {
                return new KetQua(1, "Thanh cong");
            }
        } catch (Exception e) {

        }
        return new KetQua(0, "That bai");
    }
}
