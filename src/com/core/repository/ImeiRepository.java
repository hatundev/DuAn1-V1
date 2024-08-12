/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.Imei;
import com.core.entity.KetQua;
import com.core.model.response.ImeiResponse;
import com.core.model.response.SanPhamResponse;
import com.core.service.ImeiService;
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
public class ImeiRepository {

    public List<Imei> findByIdCTSP(int id) {
        String sql = """
                     SELECT 
                        ROW_NUMBER() OVER (ORDER BY i.id DESC) AS 'stt',
                     	id,
                     	id_chi_tiet_san_pham ,
                     	ma_imei ,
                     	hoat_dong
                     FROM
                     	Imei i
                     WHERE
                     	i.id_chi_tiet_san_pham = ? and hoat_dong =1
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<Imei> list = new ArrayList<>();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Imei i = Imei.builder()
                        .stt(rs.getInt("stt"))
                        .id(rs.getInt("id"))
                        .idChiTietSanPham(rs.getInt("id_chi_tiet_san_pham"))
                        .maImei(rs.getString("ma_imei"))
                        .hoatDong(rs.getInt("hoat_dong"))
                        .build();
                list.add(i);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public KetQua create(int id, String ma) {
        String sql = """
                     INSERT INTO
                     	Imei (
                     		id_chi_tiet_san_pham,
                     		ma_imei,
                     		hoat_dong
                            )
                     VALUES (?, ?, ?)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, ma);
            ps.setInt(3, 1);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Thêm imei thành công!");
            }
        } catch (Exception e) {

        }
        return new KetQua(0, "Mã Imei đã tồn tại!");
    }

    public KetQua update(int id, String ma) {
        String sql = """
                     UPDATE Imei
                     SET ma_imei = ?
                     WHERE id = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(2, id);
            ps.setString(1, ma);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Sửa imei thành công!");
            }
        } catch (Exception e) {

        }
        return new KetQua(0, "Sửa thất bại!");
    }

    public KetQua lock(String imei) {
        String sql = """
                    UPDATE Imei
                    SET hoat_dong = 0 
                    WHERE ma_imei = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, imei);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Xóa imei thành công!");
            }
        } catch (Exception e) {

        }
        return new KetQua(0, "Xóa thất bại!");
    }
    
    public KetQua unLock(String imei) {
        String sql = """
                    UPDATE Imei
                    SET hoat_dong = 1 
                    WHERE ma_imei = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, imei);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Xóa imei thành công!");
            }
        } catch (Exception e) {

        }
        return new KetQua(0, "Xóa thất bại!");
    }
    
    public KetQua checkImei(String imei, int id){
        String sql = """
                     SELECT *
                     FROM Imei i
                     WHERE
                        ma_imei = ? and id_chi_tiet_san_pham = ? and hoat_dong = 1
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, imei);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new KetQua(1, "Thêm thành công");
            }
        } catch (Exception e) {
        }
        return new KetQua(0, "Loi");
    }

    public ImeiResponse findByImei(String imei) {
        String sql = """
                     SELECT
                        ctsp.id,
                     	ctsp.ten_san_pham_chi_tiet ,
                     	i.ma_imei ,
                     	ctsp.gia_ban 
                     FROM
                     	Imei i
                     JOIN ChiTietSanPham ctsp on
                     	i.id_chi_tiet_san_pham = ctsp.id
                     WHERE
                     	i.ma_imei = ? and i.hoat_dong = 1
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, imei);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ImeiResponse data = new ImeiResponse(rs.getInt("id"),rs.getString("ten_san_pham_chi_tiet"), rs.getString("ma_imei"), rs.getFloat("gia_ban"));
                return data;
            }
        } catch (Exception e) {

        }
        return null;
    }
    
    public boolean updateImei(int idctspcu,int idctspmoi){
        String sql = """
                     UPDATE Imei
                     SET id_chi_tiet_san_pham = ?
                     WHERE id_chi_tiet_san_pham = ? and hoat_dong = 1
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idctspmoi);
            ps.setInt(2, idctspcu);
            if (ps.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
    
    
    
    public static void main(String[] args) {
        ImeiService imeiService = new ImeiService();
        System.out.println(imeiService.delete("123458").toString());
    }
}
