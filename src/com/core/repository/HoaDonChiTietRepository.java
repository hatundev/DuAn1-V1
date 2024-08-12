/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.KetQua;
import com.core.model.request.HoaDonChiTietRequest;
import com.core.model.response.HoaDonChiTietResponse;
import com.core.model.response.HoaDonChiTietResponse2;
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
public class HoaDonChiTietRepository {

    public List<HoaDonChiTietResponse> getGioHang(int idHoaDon) {
        String sql = """
                     SELECT
                     	ROW_NUMBER() OVER (
                     	ORDER BY hdct.id DESC) AS 'stt',
                     	hdct.id,
                     	hdct.id_hoa_don ,
                     	hdct.id_chi_tiet_san_pham ,
                        ctsp.ten_san_pham_chi_tiet,
                     	hdct.mo_ta ,
                     	ctsp.gia_ban
                     FROM
                     	HoaDonChiTiet hdct
                     JOIN ChiTietSanPham ctsp ON
                     	ctsp.id = hdct.id_chi_tiet_san_pham
                     WHERE
                     	hdct.id_hoa_don = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<HoaDonChiTietResponse> list = new ArrayList<>();
            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietResponse hdct = HoaDonChiTietResponse.builder()
                        .stt(rs.getInt(1))
                        .idHoaDonChiTiet(rs.getInt(2))
                        .idHoaDon(rs.getInt(3))
                        .idChiTietSanPham(rs.getInt(4))
                        .tenChiTietSanPham(rs.getString(5))
                        .moTa(rs.getString(6))
                        .giaBan(rs.getFloat(7))
                        .build();
                list.add(hdct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KetQua create(HoaDonChiTietRequest data) {
        String sql = """
                     INSERT INTO HoaDonChiTiet (
                         id_hoa_don,
                         id_chi_tiet_san_pham,
                         gia_ban,
                         mo_ta
                     ) VALUES (?, ?, ?, ?)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, data.getIdHoaDon());
            ps.setInt(2, data.getIdChiTietSanPham());
            ps.setFloat(3, data.getGiaBan());
            ps.setString(4, data.getMoTa());
            int result = ps.executeUpdate();
            if (result != 0) {
                
                return new KetQua(1, "Thanh cong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new KetQua(0, "That bai");
    }
    
    public KetQua delete(int id) {
        String sql = """
                     DELETE FROM HoaDonChiTiet
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
    
    public List<HoaDonChiTietResponse> getHoaDonChiTiet1() {
        List<HoaDonChiTietResponse> list = new ArrayList<>();
        String sql = """
                 SELECT
                     ROW_NUMBER() OVER (
                     ORDER BY hct.id DESC) AS 'stt',
                     hct.id,
                     ctsp.ten_san_pham_chi_tiet,
                     i.ma_imei,
                     hct.gia_ban
                 FROM 
                     HoaDonChiTiet hct
                 JOIN 
                     ChiTietSanPham ctsp ON hct.id_chi_tiet_san_pham = ctsp.id
                 JOIN 
                     Imei i ON i.id_chi_tiet_san_pham = ctsp.id;
                 """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonChiTietResponse response = HoaDonChiTietResponse.builder()
                        .idHoaDonChiTiet(rs.getInt("id"))
                        .tenChiTietSanPham(rs.getString("ten_san_pham_chi_tiet"))
                        .maImei(rs.getString("ma_imei"))
                        .giaBan(rs.getFloat("gia_ban"))
                        .build();
                list.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public List<HoaDonChiTietResponse2> showHDCT(int idHoaDon) {
        String sql = """
                     SELECT
                     	ROW_NUMBER() OVER (
                     	ORDER BY hdct.id DESC) AS 'stt',
                        ctsp.ten_san_pham_chi_tiet AS 'TenSP',
                     	hdct.mo_ta AS 'Imei',
                        hdct.gia_ban AS 'gia_ban'
                     FROM
                     	HoaDonChiTiet hdct
                     JOIN ChiTietSanPham ctsp ON
                     	ctsp.id = hdct.id_chi_tiet_san_pham
                     WHERE
                     	hdct.id_hoa_don = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<HoaDonChiTietResponse2> list = new ArrayList<>();
            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietResponse2 hdct = HoaDonChiTietResponse2.builder()
                        .stt(rs.getInt(1))
                        .tenSPCT(rs.getString(2))
                        .imei(rs.getString(3))
                        .giaBan(rs.getFloat(4))
                        .build();
                list.add(hdct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
