/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.model.response;

import lombok.ToString;

/**
 *
 * @author Admin
 */
@ToString
public class VouCherResponse {
    
    private int id;
    
    private String maVouCher;
    
    private String tenVouCher;
    
    private int soLuong;
    
    private Float giaTriToiThieuApDung;
    
    private int phanTramGiam;
    
    private Float soTienDuocGiamToiDa;
    
    private String ngayBatDau;
    
    private String ngayKetThuc;
    
    private String moTa;
    
    private String ngayTao;
    
    private String ngaySua;
    
    private String nguoiTao;
    
    private String nguoiSua;
    
    private int hoatDong;
    
    private int STT;

    public VouCherResponse() {
    }

    public VouCherResponse(int id, String maVouCher, String tenVouCher, int soLuong, Float giaTriToiThieuApDung, int phanTramGiam, Float soTienDuocGiamToiDa, String ngayBatDau, String ngayKetThuc, String moTa, String ngayTao, String ngaySua, String nguoiTao, String nguoiSua, int hoatDong, int STT) {
        this.id = id;
        this.maVouCher = maVouCher;
        this.tenVouCher = tenVouCher;
        this.soLuong = soLuong;
        this.giaTriToiThieuApDung = giaTriToiThieuApDung;
        this.phanTramGiam = phanTramGiam;
        this.soTienDuocGiamToiDa = soTienDuocGiamToiDa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.hoatDong = hoatDong;
        this.STT = STT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaVouCher() {
        return maVouCher;
    }

    public void setMaVouCher(String maVouCher) {
        this.maVouCher = maVouCher;
    }

    public String getTenVouCher() {
        return tenVouCher;
    }

    public void setTenVouCher(String tenVouCher) {
        this.tenVouCher = tenVouCher;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaTriToiThieuApDung() {
        return giaTriToiThieuApDung;
    }

    public void setGiaTriToiThieuApDung(Float giaTriToiThieuApDung) {
        this.giaTriToiThieuApDung = giaTriToiThieuApDung;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Float getSoTienDuocGiamToiDa() {
        return soTienDuocGiamToiDa;
    }

    public void setSoTienDuocGiamToiDa(Float soTienDuocGiamToiDa) {
        this.soTienDuocGiamToiDa = soTienDuocGiamToiDa;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public int getHoatDong() {
        return hoatDong;
    }

    public void setHoatDong(int hoatDong) {
        this.hoatDong = hoatDong;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }
    
    
}
