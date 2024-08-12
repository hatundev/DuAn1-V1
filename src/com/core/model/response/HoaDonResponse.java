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
 * @author hatundev
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class HoaDonResponse {
    
    private Integer stt;
    private Integer id;
    private String tenKhachHang;
    private String sdt;
    private Float giaTien;
    private int hoatDong;
//new
    private String maNhanVien;
    private String ngayTao;
    private String maVouver;
    private float tongTienSauGiam;

}
