/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.KetQua;
import com.core.model.request.LoginRequest;
import com.core.model.response.TaiKhoanResponse;
import java.util.List;

/**
 *
 * @author hatun
 */
public interface TaiKhoanService {
    
    List<TaiKhoanResponse> findAll();
    
    TaiKhoanResponse findByMaNhanVien(String maNhanVien);
    
    KetQua login(LoginRequest data);
}
