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
public class NhanVien {
    
    private Integer id;

    private String maNhanVien;

    private String tenNhanVien;

    private String diaChi;

    private String soDienThoai;

    private String cccd;

    private String matKhau;

    private Integer chucVu;

    private String ngayTao;

    private String ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private Integer hoatDong;
    
}
