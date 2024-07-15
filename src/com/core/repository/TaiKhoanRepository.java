/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.model.response.TaiKhoanResponse;
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
public class TaiKhoanRepository {

    public List<TaiKhoanResponse> findAll() {
        List<TaiKhoanResponse> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "	ma_nhan_vien,\n"
                + "	mat_khau,\n"
                + "	ten_nhan_vien,\n"
                + "	chuc_vu\n"
                + "FROM\n"
                + "	NhanVien nv ";

        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoanResponse tk = new TaiKhoanResponse();
                tk.setUsername(rs.getString("ma_nhan_vien"));
                tk.setPassword(rs.getString("mat_khau"));
                tk.setTenNhanVien(rs.getString("ten_nhan_vien"));
                tk.setTenChucVu(rs.getInt("chuc_vu") == 1 ? "Quản lý" : "Nhân viên");
                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public TaiKhoanResponse findByMaNhanVien(String username) {
        String sql = "SELECT\n"
                + "	ma_nhan_vien,\n"
                + "	mat_khau,\n"
                + "	ten_nhan_vien,\n"
                + "	chuc_vu\n"
                + "FROM\n"
                + "	NhanVien nv\n"
                + "WHERE \n"
                + "	ma_nhan_vien = ?";

        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoanResponse tk = new TaiKhoanResponse();
                tk.setUsername(rs.getString("ma_nhan_vien"));
                tk.setPassword(rs.getString("mat_khau"));
                tk.setTenNhanVien(rs.getString("ten_nhan_vien"));
                tk.setTenChucVu(rs.getInt("chuc_vu") == 1 ? "Quản lý" : "Nhân viên");
                return tk;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) {
        TaiKhoanRepository repo = new TaiKhoanRepository();
        TaiKhoanResponse tk = repo.findByMaNhanVien("NV001");
        System.out.println(tk.toString());
    }
}
