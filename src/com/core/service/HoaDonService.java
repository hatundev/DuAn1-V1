/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.KetQua;
import com.core.model.request.HoaDonRequest;
import com.core.repository.HoaDonRepository;

/**
 *
 * @author hatundev
 */
public class HoaDonService {
    
    private HoaDonRepository repo = new HoaDonRepository();
    
    public KetQua create(HoaDonRequest data){
        return repo.create(data);
    }
    
}
