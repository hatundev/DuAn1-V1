/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.Imei;
import com.core.entity.KetQua;
import com.core.model.response.SanPhamResponse;
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
    
    public KetQua create(int id, String ma){
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
            ps.setString(2,ma);
            ps.setInt(3, 1);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Thêm imei thành công!");
            }
        } catch (Exception e) {
           
        }
        return new KetQua(0 , "Mã Imei đã tồn tại!");
    }
    
    public KetQua update(int id, String ma){
        String sql = """
                     UPDATE Imei
                     SET ma_imei = ?
                     WHERE id = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(2, id);
            ps.setString(1,ma);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Sửa imei thành công!");
            }
        } catch (Exception e) {
           
        }
        return new KetQua(0 , "Sửa thất bại!");
    }
    
    public KetQua delete(int id){
        String sql = """
                    UPDATE Imei
                    SET hoat_dong = 0 
                    WHERE id = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            if (ps.executeUpdate() != 0) {
                return new KetQua(1, "Xóa imei thành công!");
            }
        } catch (Exception e) {
           
        }
        return new KetQua(0 , "Xóa thất bại!");
    }
}
