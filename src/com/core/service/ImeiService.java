/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.Imei;
import com.core.entity.KetQua;
import com.core.model.response.ImeiResponse;
import com.core.repository.ImeiRepository;
import java.util.List;

/**
 *
 * @author hatun
 */
public class ImeiService {

    private ImeiRepository imeiRepository = new ImeiRepository();

    public KetQua checkImei(String ma, int id){
        return imeiRepository.checkImei(ma, id);
    }
    public List<Imei> findByIdCTSP(int id) {
        return imeiRepository.findByIdCTSP(id);
    }
    
    public ImeiResponse findByImei(String imei) {
        return imeiRepository.findByImei(imei);
    }

    public KetQua create(int id, String ma){
        return imeiRepository.create(id, ma);
    }
    
    public KetQua update(int id, String ma){
        return imeiRepository.update(id, ma);
    }
    
    public KetQua delete(String id){
        return imeiRepository.lock(id);
    }
    
    public KetQua unLock(String id){
        return imeiRepository.unLock(id);
    }
}
