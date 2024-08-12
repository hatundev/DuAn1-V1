/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.model.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter


/**
 *
 * @author admin
 */
public class HoaDonChiTietResponse1 {
    private int id;
    private String tenNhanVien;
    private String tenKhachHang;
    private String soDienThoai;
    private String maVoucher;
    private float chuyenKhoan;
    private float tienMat;
    private LocalDate ngayTao;
    private float tongTien;
    private float tongTienSauGiam;
}
