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
public class SanPham {
    
    private Integer id;

    private Integer idHeDieuHanh;

    private Integer idManHinh;

    private Integer idHang;

    private Integer idCameraTruoc;

    private Integer idCameraSau;

    private Integer idChip;

    private Integer idPin;

    private String tenSanPham;

    private String ngayTao;

    private String ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private Integer hoatDong;
    
}
