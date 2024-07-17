/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.KetQua;
import com.core.entity.SanPham;
import com.core.model.response.ChiTietSanPhamResponse;
import com.core.repository.ChiTietSanPhamRepository;

/**
 *
 * @author hatun
 */
public class ChiTietSanPhamService {

    private ChiTietSanPhamRepository repo = new ChiTietSanPhamRepository();

    public KetQua delete(int id) {
        if (repo.delete(id)) {
            return new KetQua(1, "Xóa thành công");
        } else {
            return new KetQua(0, "Xóa thất bại");
        }
    }
    
    public KetQua update(ChiTietSanPhamResponse data) {
        SanPham sp = SanPham.builder()
                .tenSanPham(data.getTenSanPham())
                .idChip(data.getIdChip())
                .idCameraSau(data.getIdCameraSau())
                .idCameraTruoc(data.getIdCameraTruoc())
                .idHang(data.getIdHang())
                .idHeDieuHanh(data.getIdHeDieuHanh())
                .idManHinh(data.getIdManHinh())
                .idPin(data.getIdPin())
                .ngayTao("")
                .nguoiTao("")
                .ngaySua("")
                .nguoiSua("")
                .hoatDong(1)
                .build();
    }

}
