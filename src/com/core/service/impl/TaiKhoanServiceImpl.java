/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service.impl;

import com.core.model.request.LoginRequest;
import com.core.model.response.TaiKhoanResponse;
import com.core.repository.TaiKhoanRepository;
import com.core.service.TaiKhoanService;
import java.util.List;

/**
 *
 * @author hatun
 */
public class TaiKhoanServiceImpl implements TaiKhoanService {

    private TaiKhoanRepository repo = new TaiKhoanRepository();

    @Override
    public List<TaiKhoanResponse> findAll() {
        return repo.findAll();
    }

    @Override
    public TaiKhoanResponse findByMaNhanVien(String maNhanVien) {
        return repo.findByMaNhanVien(maNhanVien);
    }

    public boolean isQuanLy(TaiKhoanResponse tk) {
        if (tk.getTenChucVu().equals("Quản lý")) {
            return true;
        }
        return false;
    }

    public int login(LoginRequest login) {
        TaiKhoanResponse tk = repo.findByMaNhanVien(login.getUsername());
        System.out.println(tk.toString());
        if (tk == null) {
            return 0;
        } else {
            if (tk.getPassword().equals(login.getPassword())) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    
    public static void main(String[] args) {
        TaiKhoanServiceImpl tk = new TaiKhoanServiceImpl();
        int x = tk.login(new LoginRequest("NV001","password1"));
        System.out.println(x);
    }

}
