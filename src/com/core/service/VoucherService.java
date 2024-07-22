/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.model.request.VoucherBanHangResponse;
import com.core.repository.VoucherRepository;
import java.util.List;

/**
 *
 * @author hatundev
 */
public class VoucherService {
    
    private VoucherRepository voucherRepository = new VoucherRepository();
    
    public List<VoucherBanHangResponse> getCombo(Float total){
        return voucherRepository.getComBo(total);
    }
}
