/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hatun
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ChiTietSanPhamRequest {
    
    private Integer idSanPham;

    private Integer idChiTietSanPham;

    private String tenHeDieuHanh;

    private String tenManHinh;

    private String tenHang;

    private String tenCameraTruoc;

    private String tenCameraSau;

    private String tenChip;

    private String tenPin;

    private String tenSanPham;

    private String tenRam;

    private String tenBoNho;

    private String tenMauSac;

    private String tenSanPhamChiTiet;

    private Float giaBan;

    private Integer yeuThich;

    private String ngayTao;

    private String ngaySua;

    private String nguoiTao;

    private String nguoiSua;
    
    private Integer hoatDong;
    
}
