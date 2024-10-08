/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service.impl;

import com.core.entity.KetQua;
import com.core.model.request.ThuocTinhRequest;
import com.core.model.response.ThuocTinhResponse;
import com.core.repository.ThuocTinhRepository;
import com.core.service.ThuocTinhService;
import java.util.List;

/**
 *
 * @author hatun
 */
public class ThuocTinhServiceImpl implements ThuocTinhService {

    private ThuocTinhRepository repo = new ThuocTinhRepository();

    @Override
    public List<ThuocTinhResponse> findAll(String tenBang) {
        return repo.findAll(tenBang);
    }
    
    @Override
    public List<ThuocTinhResponse> findAllHD(String tenBang) {
        return repo.findAllHD(tenBang);
    }

    @Override
    public ThuocTinhResponse findById(String tenBang, Integer id) {
        return repo.findById(tenBang, id);
    }

    @Override
    public KetQua create(String tenBang, String data) {
        return repo.create(tenBang, data);
    }

    @Override
    public Boolean update(String tenBang, Integer id, ThuocTinhRequest data) {
        return repo.update(tenBang, id, data);
    }

    @Override
    public Boolean delete(String tenBang, Integer id) {
        return repo.delete(tenBang, id);
    }
    
    @Override
    public KetQua create1(String tenBang, String data,Integer hoatDong) {
         System.out.println("Tạo mới " + tenBang + ": " + data + ", Hoạt động: " + hoatDong);
        return repo.create1(tenBang, data,hoatDong);
    }
    
    @Override
    public Boolean update1(String tenBang, Integer id, ThuocTinhResponse data, Integer hoatDong) {
        return repo.update1(tenBang, id, data,hoatDong);
    }

    @Override
    public List<ThuocTinhResponse> cboHoatDong(String tenBang) {
        return repo.cboHoatDong(tenBang);
    }
}
