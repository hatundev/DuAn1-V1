/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.KetQua;
import com.core.entity.NhanVien;
import com.core.model.response.NhanVienResponse;
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
public class NhanVienRepository {

    public List<NhanVienResponse> findAll() {
        String sql = """
                     SELECT 
                        ROW_NUMBER() OVER (ORDER BY nv.hoat_dong DESC, nv.chuc_vu DESC, nv.id DESC) AS 'stt',
                        nv.id,
                        nv.ma_nhan_vien,
                        nv.ten_nhan_vien,
                        nv.dia_chi,
                        nv.so_dien_thoai,
                        nv.cccd,
                        nv.chuc_vu,
                        nv.ngay_tao,
                        nv.hoat_dong,
                        COALESCE(SUM(hd.tong_tien_sau_giam), 0) AS DoanhSo
                     FROM
                        NhanVien nv
                     LEFT JOIN
                        HoaDon hd ON nv.id = hd.id_nhan_vien 
                        AND hd.hoat_dong = 1
                        AND hd.ngay_tao >= DATEADD(month, DATEDIFF(month, 0, GETDATE()), 0)
                        AND hd.ngay_tao < DATEADD(month, DATEDIFF(month, -1, GETDATE()), 0)
                     GROUP BY
                        nv.id, nv.ma_nhan_vien, nv.ten_nhan_vien, nv.dia_chi, nv.so_dien_thoai, nv.cccd, nv.chuc_vu, nv.ngay_tao, nv.hoat_dong
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<NhanVienResponse> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienResponse nv = NhanVienResponse.builder()
                        .stt(rs.getInt("stt"))
                        .id(rs.getInt("id"))
                        .maNhanVien(rs.getString("ma_nhan_vien"))
                        .tenNhanVien(rs.getString("ten_nhan_vien"))
                        .diaChi(rs.getString("dia_chi"))
                        .soDienThoai(rs.getString("so_dien_thoai"))
                        .cccd(rs.getString("cccd"))
                        .chucVu(rs.getInt("chuc_vu"))
                        .ngayTao(rs.getString("ngay_tao"))
                        .hoatDong(rs.getInt("hoat_dong"))
                        .doanhSo(rs.getFloat("DoanhSo"))
                        .build();
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean create(NhanVien data, String username) {
        String sql = """
                INSERT INTO NhanVien (ma_nhan_vien, ten_nhan_vien, so_dien_thoai, dia_chi, cccd, mat_khau, chuc_vu, ngay_tao, nguoi_tao, hoat_dong)
                VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, 1)
                """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, data.getMaNhanVien());
            ps.setString(2, data.getTenNhanVien());
            ps.setString(3, data.getSoDienThoai());
            ps.setString(4, data.getDiaChi());
            ps.setString(5, data.getCccd());
            ps.setString(6, data.getSoDienThoai());
            ps.setInt(7, data.getChucVu());
            ps.setString(8, username);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }
    
    public boolean updatePass(String pass, int id) {
        String sql = """
                UPDATE NhanVien
                SET mat_khau = ?
                WHERE id = ?
                """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pass);
            ps.setInt(2, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    
    public boolean resetPass(String pass,int id) {
        String sql = """
                UPDATE NhanVien
                SET mat_khau = 
                WHERE id = ?
                """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pass);
            ps.setInt(2, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public boolean update(NhanVien data, int id) {
        String sql = """
                UPDATE NhanVien
                SET ma_nhan_vien = ?, 
                    ten_nhan_vien = ?, 
                    so_dien_thoai = ?, 
                    dia_chi = ?, 
                    cccd = ?, 
                    chuc_vu = ?
                WHERE id = ?
                """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, data.getMaNhanVien());
            ps.setString(2, data.getTenNhanVien());
            ps.setString(3, data.getSoDienThoai());
            ps.setString(4, data.getDiaChi());
            ps.setString(5, data.getCccd());
            ps.setInt(6, data.getChucVu());
            ps.setInt(7, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public KetQua delete(Integer id) {
        String sql = """
                     UPDATE NhanVien
                     SET hoat_dong = 0
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
        NhanVienRepository repo = new NhanVienRepository();
        List<NhanVienResponse> list = repo.findAll();
        for (NhanVienResponse nhanVienResponse : list) {
            System.out.println(nhanVienResponse.toString());
        }
    }

}
