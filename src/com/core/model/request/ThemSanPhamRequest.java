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
public class ThemSanPhamRequest {
    
    private String tenSanPham;
    
    private String tenSanPhamChiTiet;
    
    private Float giaBan;
    
    private Integer idChip;
    
    private Integer idHeDieuHanh;
    
    private Integer idCameraSau;
    
    private Integer idCameraTruoc;
    
    private Integer idPin;
    
    private Integer idHang;
    
    private Integer idManHinh;
    
    private Integer idRam;
    
    private Integer idBoNho;
    
    private Integer idMauSac;
    
}
