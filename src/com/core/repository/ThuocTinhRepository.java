/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.KetQua;
import com.core.model.request.ThuocTinhRequest;
import com.core.model.response.ThuocTinhResponse;
import com.core.tool.DBConnect;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hatun
 */
public class ThuocTinhRepository {

    public List<ThuocTinhResponse> findAll(String tenBang) {
        String sql = String.format("""
                                    SELECT 
                                         id,
                                         ten,
                                         hoat_dong
                                     FROM
                                         %s
                                     ORDER BY id DESC
                                    """, tenBang);
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<ThuocTinhResponse> data = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new ThuocTinhResponse(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public ThuocTinhResponse findById(String tenBang, int id) {
        String sql = String.format("""
                                    SELECT 
                                         id,
                                         ten,
                                         hoat_dong
                                     FROM
                                         %s
                                     WHERE 
                                         id = ?
                                    """, tenBang);
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ThuocTinhResponse(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public KetQua create(String tenBang, String data) {
        String sql = String.format("""
                                    INSERT INTO %s (ten,hoat_dong)
                                    VALUES (?,?)
                                    """, tenBang);
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, data);

            ps.setInt(2, 1);
            int result = ps.executeUpdate();
            if (result != 0) {
                return new KetQua(1, "Thanh cong");
            }
        } catch (Exception e) {

        }
        return new KetQua(0, "That bai");
    }

    public Boolean update(String tenBang, Integer id, ThuocTinhRequest data) {
//        String sql = String.format("""
//                                   UPDATE %s
//                                   SET ten = ? and hoat_dong = ?
//                                   WHERE id = ?
//                                   """, tenBang);
//        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, data.getName());
//            ps.setInt(2, data.getHoatDong());
//            ps.setInt(3, id);
//            int result = ps.executeUpdate();
//            if (result == 0) {
//                return false;
//            } else {
//                return true;
//            }
//        } catch (Exception e) {
//            return false;
//        }
        return false;
    }

    public Boolean delete(String tenBang, Integer id) {
        String sql = String.format("""
                                   DELETE FROM %s
                                   WHERE id = ?
                                   """, tenBang);
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
