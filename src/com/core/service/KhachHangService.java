/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.KetQua;
import com.core.entity.KhachHang;
import com.core.repository.KhachHangRepository;

/**
 *
 * @author hatundev
 */
public class KhachHangService {
    
    private KhachHangRepository repo = new KhachHangRepository();
    
    public KhachHang findBySDT(String sdt){
        return repo.findBySDT(sdt);
    }
    
    public KetQua create(String sdt, String name){
        return repo.create(sdt, name);
    } 
}
