/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.repository;

import com.core.entity.ChiTietSanPham;
import com.core.model.response.ChiTietSanPhamResponse;
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
public class ChiTietSanPhamRepository {

    public List<ChiTietSanPhamResponse> findAll() {
        String sql = """
                    SELECT 
                        ROW_NUMBER() OVER (ORDER BY ctsp.id DESC) AS 'stt',
                        ctsp.id as 'id',
                        ctsp.ten_san_pham_chi_tiet AS 'ten_san_pham_chi_tiet',
                        (SELECT COUNT(*) FROM Imei i WHERE i.id_chi_tiet_san_pham = ctsp.id and i.hoat_dong= 1) AS 'so_luong',
                        ctsp.gia_ban AS 'gia_ban'
                    FROM ChiTietSanPham ctsp JOIN SanPham sp ON sp.id=ctsp.id_san_pham
                    WHERE ctsp.hoat_dong = 1 and sp.hoat_dong=1
                    """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            List<ChiTietSanPhamResponse> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPhamResponse sp = ChiTietSanPhamResponse.builder()
                        .stt(rs.getInt("stt"))
                        .idChiTietSanPham(rs.getInt("id"))
                        .tenSanPhamChiTiet(rs.getString("ten_san_pham_chi_tiet"))
                        .soLuong(rs.getInt("so_luong"))
                        .giaBan(rs.getFloat("gia_ban"))
                        .build();
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public Integer findIdBySanPham(ChiTietSanPham ctsp) {
        String sql = """
                     SELECT id
                     FROM ChiTietSanPham
                     WHERE ten_san_pham_chi_tiet = ? and
                           id_san_pham = ? and
                           id_ram = ? and
                           id_bo_nho = ? and
                           id_mau_sac = ? and
                           gia_ban = ?
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ctsp.getTenSanPhamChiTiet());
            ps.setInt(2, ctsp.getIdSanPham());
            ps.setInt(3, ctsp.getIdRam());
            ps.setInt(4, ctsp.getIdBoNho());
            ps.setInt(5, ctsp.getIdMauSac());
            ps.setFloat(6, ctsp.getGiaBan());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int result = rs.getInt("id");
                return result;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public ChiTietSanPhamResponse findByID(int id) {
        String sql = """
                 SELECT 
                    ctsp.id AS idChiTietSanPham,
                    ctsp.id_san_pham AS idSanPham,
                    r.ten AS tenRam,
                    bn.ten AS tenBoNho,
                    ms.ten AS tenMauSac,
                    ctsp.ten_san_pham_chi_tiet AS tenSanPhamChiTiet,
                    (SELECT COUNT(*) FROM Imei i WHERE i.id_chi_tiet_san_pham = ctsp.id and hoat_dong = 1) as soLuong,
                    ctsp.gia_ban AS giaBan,
                    he.ten AS tenHeDieuHanh,
                    mh.ten AS tenManHinh,
                    h.ten AS tenHang,
                    ct.ten AS tenCameraTruoc,
                    cs.ten AS tenCameraSau,
                    c.ten AS tenChip,
                    p.ten AS tenPin,
                    sp.ten_san_pham AS tenSanPham,
                    ctsp.ngay_tao AS ngayTao,
                    ctsp.ngay_sua AS ngaySua,
                    ctsp.nguoi_tao AS nguoiTao,
                    ctsp.nguoi_sua AS nguoiSua,
                    ctsp.yeu_thich AS yeuThich,
                    ctsp.hoat_dong AS hoatDong
                FROM 
                    ChiTietSanPham ctsp
                LEFT JOIN
                    Imei i on ctsp.id = i.id_chi_tiet_san_pham
                JOIN 
                    SanPham sp ON ctsp.id_san_pham = sp.id
                JOIN 
                    Ram r ON ctsp.id_ram = r.id
                JOIN 
                    BoNho bn ON ctsp.id_bo_nho = bn.id
                JOIN 
                    MauSac ms ON ctsp.id_mau_sac = ms.id
                JOIN 
                    HeDieuHanh he ON sp.id_he_dieu_hanh = he.id
                JOIN 
                    ManHinh mh ON sp.id_man_hinh = mh.id
                JOIN 
                    Hang h ON sp.id_hang = h.id
                JOIN 
                    CameraTruoc ct ON sp.id_camera_truoc = ct.id
                JOIN 
                    CameraSau cs ON sp.id_camera_sau = cs.id
                JOIN 
                    Chip c ON sp.id_chip = c.id
                JOIN 
                    Pin p ON sp.id_pin = p.id
                WHERE 
                    ctsp.id = ?
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return ChiTietSanPhamResponse.builder()
                        .idChiTietSanPham(rs.getInt("idChiTietSanPham"))
                        .idSanPham(rs.getInt("idSanPham"))
                        .tenRam(rs.getString("tenRam"))
                        .tenBoNho(rs.getString("tenBoNho"))
                        .tenMauSac(rs.getString("tenMauSac"))
                        .tenSanPhamChiTiet(rs.getString("tenSanPhamChiTiet"))
                        .soLuong(rs.getInt("soLuong"))
                        .giaBan(rs.getFloat("giaBan"))
                        .tenHeDieuHanh(rs.getString("tenHeDieuHanh"))
                        .tenManHinh(rs.getString("tenManHinh"))
                        .tenHang(rs.getString("tenHang"))
                        .tenCameraTruoc(rs.getString("tenCameraTruoc"))
                        .tenCameraSau(rs.getString("tenCameraSau"))
                        .tenChip(rs.getString("tenChip"))
                        .tenPin(rs.getString("tenPin"))
                        .tenSanPham(rs.getString("tenSanPham"))
                        .ngayTao(rs.getString("ngayTao"))
                        .ngaySua(rs.getString("ngaySua"))
                        .nguoiTao(rs.getString("nguoiTao"))
                        .nguoiSua(rs.getString("nguoiSua"))
                        .yeuThich(rs.getInt("yeuThich"))
                        .hoatDong(rs.getInt("hoatDong"))
                        .build();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        ChiTietSanPhamRepository ctspr = new ChiTietSanPhamRepository();
        System.out.println(ctspr.findByID(2).toString());
    }

    public boolean create(ChiTietSanPham ctsp, String username) {
        String sql = """
                     INSERT INTO
                     	ChiTietSanPham (
                     		ten_san_pham_chi_tiet,
                     		id_san_pham,
                     		id_ram,
                     		id_bo_nho,
                     		id_mau_sac,
                     		gia_ban,
                     		ngay_tao,
                     		nguoi_tao,
                     		yeu_thich,
                     		hoat_dong
                     	)
                     VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ctsp.getTenSanPhamChiTiet());
            ps.setInt(2, ctsp.getIdSanPham());
            ps.setInt(3, ctsp.getIdRam());
            ps.setInt(4, ctsp.getIdBoNho());
            ps.setInt(5, ctsp.getIdMauSac());
            ps.setFloat(6, ctsp.getGiaBan());
            ps.setString(7, username);
            ps.setInt(8, 1);
            ps.setInt(9, 1);
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(ChiTietSanPham ctsp, int id) {
        String sql = """
                 UPDATE ChiTietSanPham
                 SET ten_san_pham_chi_tiet = ?,
                     id_ram = ?,
                     id_bo_nho = ?,
                     id_mau_sac = ?,
                     gia_ban = ?
                 WHERE id = ?
                 """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ctsp.getTenSanPhamChiTiet());
            ps.setInt(2, ctsp.getIdRam());
            ps.setInt(3, ctsp.getIdBoNho());
            ps.setInt(4, ctsp.getIdMauSac());
            ps.setFloat(5, ctsp.getGiaBan());
            ps.setFloat(6, id);
            if (ps.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = """
                    UPDATE ChiTietSanPham
                         SET hoat_dong = 0
                         WHERE id = ?
                    """;
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
