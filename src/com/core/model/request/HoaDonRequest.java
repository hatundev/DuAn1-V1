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
 * @author hatundev
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HoaDonRequest {
    
    private Integer idKhachHang;
    
    private Integer idNhanVien;
    
    private Integer idPhieuGiamGia;
    
    private float tongTien;
    
    private float tongTienSauGiam;
    
    private String tenKhachHang;
    
    private String sdt;
    
    private String username;
}
