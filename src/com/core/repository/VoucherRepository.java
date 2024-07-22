/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.model.request.VoucherBanHangResponse;
import com.core.model.response.SanPhamResponse;
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
public class VoucherRepository {

    public List<VoucherBanHangResponse> getComBo(Float total) {
        String sql = """
                    SELECT 
                            ROW_NUMBER() OVER (ORDER BY v.id DESC) AS 'stt',
                            v.id AS 'id',
                            v.ma_voucher AS 'ma',
                            v.ten_voucher AS 'ten',
                            v.so_luong AS 'soLuong',
                            v.gia_tri_toi_thieu_ap_dung AS 'giaTriToiThieuApDung',
                            v.phan_tram_giam AS 'phanTramGiam',
                            v.so_tien_duoc_giam_toi_da AS 'soTienDuocGiamToiDa'
                    FROM 
                        PhieuGiamGia v 
                        LEFT JOIN (
                            SELECT 
                                id_phieu_giam_gia, 
                                COUNT(*) AS so_luong_da_su_dung
                            FROM 
                                HoaDon
                            GROUP BY 
                                id_phieu_giam_gia
                        ) hd ON v.id = hd.id_phieu_giam_gia
                    WHERE 
                            v.hoat_dong = 1 and 
                            v.ngay_bat_dau <= GETDATE() AND 
                            v.ngay_ket_thuc >= GETDATE() AND 
                            v.gia_tri_toi_thieu_ap_dung <= ? AND
                            (hd.so_luong_da_su_dung IS NULL OR hd.so_luong_da_su_dung < v.so_luong)
                    """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<VoucherBanHangResponse> list = new ArrayList<>();
            ps.setFloat(1, total);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VoucherBanHangResponse voucher = new VoucherBanHangResponse();
                voucher.setStt(rs.getInt("stt"));
                voucher.setMaVoucher(rs.getString("ma"));
                voucher.setTenVoucher(rs.getString("ten"));
                voucher.setSoLuong(rs.getInt("soLuong"));
                voucher.setGiaTriToiThieuApDung(rs.getFloat("giaTriToiThieuApDung"));
                voucher.setPhanTramGiam(rs.getInt("phanTramGiam"));
                voucher.setSoTienDuocGiamToiDa(rs.getFloat("soTienDuocGiamToiDa"));
                list.add(voucher);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        VoucherRepository repository = new VoucherRepository();
        //System.out.println(repository.getComBo().size());
    }
}
