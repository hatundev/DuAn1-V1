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
public class PhieuGiamGia {
    
    private Integer id;

    private String maVoucher;

    private String tenVoucher;

    private Integer soLuong;

    private Float giaTriToiThieuApDung;

    private Integer phanTramGiam;

    private Float soTienDuocGiamToiDa;

    private String ngayBatDau;

    private String ngayKetThuc;

    private String moTa;

    private String ngayTao;

    private String ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private Integer hoatDong;
    
}
