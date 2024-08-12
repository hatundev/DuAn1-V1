/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.model.response;


/**
 *
 * @author Admin
 */
public class KhachHangResponse {
    private Integer stt;
    
    private Integer id;
    
    private String tenKhachHang;
    
    private String SoDienThoai;
    
    private String ghiChu;
    
    private Integer hoatDong;

    public KhachHangResponse() {
    }

    public KhachHangResponse(Integer stt, Integer id, String tenKhachHang, String SoDienThoai,String ghiChu, Integer hoatDong) {
        this.stt = stt;
        this.id = id;
        this.tenKhachHang = tenKhachHang;
        this.SoDienThoai = SoDienThoai;
        this.hoatDong = hoatDong;
        this.ghiChu = ghiChu;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public Integer getHoatDong() {
        return hoatDong;
    }

    public void setHoatDong(Integer hoatDong) {
        this.hoatDong = hoatDong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
   
}
