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

    private int idHeDieuHanh;

    private int idManHinh;

    private int idHang;

    private int idCameraTruoc;

    private int idCameraSau;

    private int idChip;

    private int idPin;

    private String tenSanPham;

    private int idRam;

    private int idBoNho;

    private int idMauSac;

    private String tenSanPhamChiTiet;

    private Float giaBan;

    private Integer yeuThich;

    private String ngayTao;

    private String ngaySua;

    private String nguoiTao;

    private String nguoiSua;
    
    private Integer hoatDong;
    
}
