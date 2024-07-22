/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.KetQua;
import com.core.entity.KhachHang;
import com.core.model.request.VoucherBanHangResponse;
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
public class KhachHangRepository {
    
    public KhachHang findBySDT(String sdt){
        String sql = """
                     SELECT
                     	kh.id,
                     	kh.ten_khach_hang,
                     	kh.so_dien_thoai,
                     	kh.hoat_dong
                     FROM
                     	KhachHang kh
                     WHERE
                     	kh.so_dien_thoai LIKE ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%"+sdt+"%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt("id"));
                kh.setTenKhachHang(rs.getString("ten_khach_hang"));
                kh.setSoDienThoai(rs.getString("so_dien_thoai"));
                kh.setHoatDong(rs.getInt("hoat_dong"));
                return kh;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    
    public KetQua create(String sdt, String name){
        String sql = """
                     INSERT INTO KhachHang(ten_khach_hang, so_dien_thoai, hoat_dong)
                     VALUES (?,?, 1)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, sdt);
            int result = ps.executeUpdate();
            if (result != 0) {
                return new KetQua(1, "Thanh cong");
            }
        } catch (Exception e) {

        }
        return new KetQua(0, "That bai");
    }
    
    public static void main(String[] args) {
        KhachHangRepository repo= new KhachHangRepository();
        System.out.println(repo.findBySDT("0961"));
    }
}
