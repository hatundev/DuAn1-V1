/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.model.response;

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
public class ChiTietSanPhamResponse {

    private Integer idChiTietSanPham;

    private Integer idSanPham;

    private String tenSanPham;

    private String tenSanPhamChiTiet;

    private String tenHeDieuHanh;

    private String tenHang;

    private String tenManHinh;

    private String tenCameraTruoc;

    private String tenCameraSau;

    private String tenChip;

    private String tenPin;

    private String tenRam;

    private String tenBoNho;

    private String tenMauSac;
    
    private Integer soLuong;

    private Float giaBan;

    private String ngayTao;

    private String ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private Integer yeuThich;

    private Integer hoatDong;

}
