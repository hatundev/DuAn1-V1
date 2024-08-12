/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.KetQua;
import com.core.model.request.HoaDonRequest;
import com.core.model.response.HoaDonResponse;
import com.core.repository.HoaDonRepository;
import java.util.List;

/**
 *
 * @author hatundev
 */
public class HoaDonService {

    private HoaDonRepository repo = new HoaDonRepository();

    public List<HoaDonResponse> getAllHDCho() {
        return repo.findAllByHD();
    }

    public KetQua create(String username) {
        return repo.create(username);
    }

    public Integer findByHoaDonRequest(HoaDonRequest data) {
        return repo.findByHoaDonRequest(data);
    }

    public KetQua updateMoney(int id, float money) {
        return repo.updateMoney(id, money);
    }
    
    public KetQua updateKhachHang(int id, String name, String sdt, int idHD) {
        return repo.updateKhachHang(id, name, sdt, idHD);
    }
    
    public KetQua updateHoanThanh(int id, float tongTien, float tongTienSauGiam, Integer idVoucher) {
        return repo.updateHoanThanh(tongTien, id, tongTienSauGiam, idVoucher);
    }
    
    public KetQua delete(int id){
        return repo.delete(id);
    }
}
