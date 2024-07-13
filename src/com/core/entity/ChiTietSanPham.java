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
public class ChiTietSanPham {
    
    private Integer id;

    private Integer idSanPham;

    private Integer idRam;

    private Integer idBoNho;

    private Integer idMauSac;

    private String tenSanPhamChiTiet;

    private Float giaBan;

    private Integer yeuThich;

    private String ngayTao;

    private String ngaySua;

    private String nguoiTao;

    private String nguoiSua;
    
}
