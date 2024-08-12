/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.KetQua;
import com.core.model.request.HoaDonChiTietRequest;
import com.core.model.response.HoaDonChiTietResponse;
import com.core.repository.HoaDonChiTietRepository;
import java.util.List;

/**
 *
 * @author hatundev
 */
public class HoaDonChiTietService {
    
    private HoaDonChiTietRepository repo = new HoaDonChiTietRepository();
    
    public List<HoaDonChiTietResponse> getGioHang(int idHoaDon){
        return repo.getGioHang(idHoaDon);
    }
    
    public KetQua create(HoaDonChiTietRequest data){
        return repo.create(data);
    }
    
    public KetQua delete(int id){
        return repo.delete(id);
    }
}
