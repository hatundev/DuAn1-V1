/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.KetQua;
import com.core.entity.KhachHang;
import com.core.model.response.KhachHangResponse;
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

    public KhachHang findBySDT(String sdt) {
        String sql = """
                     SELECT
                     	kh.id,
                     	kh.ten_khach_hang,
                     	kh.so_dien_thoai,
                     	kh.hoat_dong,
                        kh.ghi_chu
                     FROM
                     	KhachHang kh
                     WHERE
                     	kh.so_dien_thoai LIKE ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + sdt + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt("id"));
                kh.setTenKhachHang(rs.getString("ten_khach_hang"));
                kh.setSoDienThoai(rs.getString("so_dien_thoai"));
                kh.setHoatDong(rs.getInt("hoat_dong"));
                kh.setGhiChu(rs.getString("ghi_chu"));
                return kh;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public boolean updateKhachHangtets(KhachHangResponse kh) {
        String sql = "UPDATE KhachHang SET ten_khach_hang = ?, so_dien_thoai = ?, ghi_chu = ? WHERE id = ? and hoat_dong = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, kh.getTenKhachHang());
            sttm.setString(2, kh.getSoDienThoai());
            sttm.setInt(5, kh.getHoatDong());
            sttm.setString(3, kh.getGhiChu());
            sttm.setInt(4, kh.getId());
            
            return sttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatusKhachHang(int id, String ghiChu, int hoatDong) {
        String sql = "UPDATE KhachHang SET hoat_dong = ?, ghi_chu = ? WHERE id = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, hoatDong);
            sttm.setString(2, ghiChu);
            sttm.setInt(3, id);
            int rowsAffected = sttm.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public KetQua create(String sdt, String name) {
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

    public List<KhachHangResponse> getShowKH(boolean active) {
        List<KhachHangResponse> list = new ArrayList<>();
        String sql = "SELECT "
                + "kh.id,"
                + "ROW_NUMBER() OVER(ORDER BY kh.id DESC) AS 'STT', "
                + "kh.ten_khach_hang AS 'Tên Khách Hàng', "
                + "kh.so_dien_thoai AS 'Số Điện Thoại', "
                + "kh.ghi_chu as 'GhiChu',"
                + "kh.hoat_dong AS 'Trang Thai' "
                + "FROM KhachHang kh "
                + "WHERE kh.hoat_dong = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, active ? 1 : 0);
            ResultSet rs = sttm.executeQuery();

            while (rs.next()) {
                KhachHangResponse kh = new KhachHangResponse();
                kh.setId(rs.getInt("id"));
                kh.setStt(rs.getInt("STT"));
                kh.setTenKhachHang(rs.getString("Tên Khách Hàng"));
                kh.setSoDienThoai(rs.getString("Số Điện Thoại"));
                kh.setGhiChu(rs.getString("GhiChu"));
                kh.setHoatDong(rs.getInt("Trang Thai"));
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<KhachHangResponse> getShowKHX() {
        List<KhachHangResponse> list = new ArrayList<>();
        String sql = "SELECT "
                + "kh.id,"
                + "ROW_NUMBER() OVER(ORDER BY kh.id DESC) AS 'STT', "
                + "kh.ten_khach_hang AS 'Tên Khách Hàng', "
                + "kh.so_dien_thoai AS 'Số Điện Thoại', "
                + "kh.ghi_chu as 'GhiChu',"
                + "kh.hoat_dong AS 'Trang Thai' "
                + "FROM KhachHang kh "
                + "WHERE kh.hoat_dong = 0";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();

            while (rs.next()) {
                KhachHangResponse kh = new KhachHangResponse();
                kh.setId(rs.getInt("id"));
                kh.setStt(rs.getInt("STT"));
                kh.setTenKhachHang(rs.getString("Tên Khách Hàng"));
                kh.setSoDienThoai(rs.getString("Số Điện Thoại"));
                kh.setGhiChu(rs.getString("GhiChu"));
                kh.setHoatDong(rs.getInt("Trang Thai"));
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHangResponse> getShowKH() {
        List<KhachHangResponse> list = new ArrayList<>();
        String sql = """
                    SELECT 
                        kh.id,
                        ROW_NUMBER() OVER(ORDER BY kh.id DESC) AS 'STT', 
                        kh.ten_khach_hang AS 'Tên Khách Hàng', 
                        kh.so_dien_thoai AS 'Số Điện Thoại', 
                        kh.ghi_chu as 'GhiChu',
                        kh.hoat_dong AS 'Trang Thai' 
                    FROM KhachHang kh
                    WHERE kh.hoat_dong = 1
                    """;

        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();

            while (rs.next()) {
                KhachHangResponse kh = new KhachHangResponse();
                kh.setId(rs.getInt("id"));
                kh.setStt(rs.getInt("STT"));
                kh.setTenKhachHang(rs.getString("Tên Khách Hàng"));
                kh.setSoDienThoai(rs.getString("Số Điện Thoại"));
                kh.setHoatDong(rs.getInt("Trang Thai"));
                kh.setGhiChu(rs.getString("GhiChu"));
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHangResponse> searchByName(String ten) {
        List<KhachHangResponse> list = new ArrayList<>();
        String sql = "SELECT "
                + "ROW_NUMBER() OVER(ORDER BY kh.id DESC) AS 'STT', "
                + "kh.id as 'ID',"
                + "kh.ten_khach_hang AS 'Tên Khách Hàng', "
                + "kh.so_dien_thoai AS 'Số Điện Thoại', "
                + "kh.hoat_dong AS 'Trang Thai', "
                + "kh.ghi_chu as 'GhiChu'"
                + "FROM KhachHang kh "
                + "WHERE kh.ten_khach_hang LIKE ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, "%" + ten + "%");
            ResultSet rs = sttm.executeQuery();

            while (rs.next()) {
                KhachHangResponse kh = new KhachHangResponse();
                kh.setStt(rs.getInt("STT"));
                kh.setId(rs.getInt("ID"));
                kh.setTenKhachHang(rs.getString("Tên Khách Hàng"));
                kh.setSoDienThoai(rs.getString("Số Điện Thoại"));
                kh.setHoatDong(rs.getInt("Trang Thai"));
                kh.setGhiChu(rs.getString("GhiChu"));
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteKhachHang(String ten) {
        String sql = "delete KhachHang where ten_khach_hang = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, ten);
            return sttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean acctiveKH(int id) {
        String sql = "UPDATE KhachHang SET hoat_dong = 1 WHERE id = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, id);
            return sttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateKhachHang(KhachHangResponse kh) {
        String sql = "UPDATE KhachHang SET ten_khach_hang = ?, so_dien_thoai = ?, hoat_dong = ?, ghi_chu = ? WHERE id = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, kh.getTenKhachHang());
            sttm.setString(2, kh.getSoDienThoai());
            sttm.setInt(3, kh.getHoatDong());
            sttm.setString(4, kh.getGhiChu());
            sttm.setInt(5, kh.getId());
            return sttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        KhachHangRepository repo = new KhachHangRepository();
        System.out.println(repo.findBySDT("0961"));
    }
}
