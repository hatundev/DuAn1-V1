/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.entity;

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
public class HoaDon {
    
    private Integer id;

    private Integer idKhachHang;

    private Integer idNhanVien;

    private Integer idPhieuGiamGia;

    private String ngayTao;

    private Float tongTien;

    private Float tongTienSauGiam;

    private String tenKhachHang;

    private String soDienThoai;

    private String ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private Integer hoatDong;
    
}
