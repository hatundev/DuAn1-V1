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

/**
 *
 * @author hatundev
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HoaDonChiTietResponse {
    
    private int stt;
    private int idHoaDonChiTiet;
    private int idHoaDon;
    private int idChiTietSanPham;
    private String tenChiTietSanPham;
    private String moTa;
    private float giaBan; 
    private String maImei;
}
