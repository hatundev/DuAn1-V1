/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service.impl;

import com.core.entity.KetQua;
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

//    public boolean isQuanLy(String username) {
//        if (tk.getTenChucVu().equals("Quản lý")) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public KetQua login(LoginRequest login) {
        TaiKhoanResponse tk = repo.findByMaNhanVien(login.getUsername());
        if (tk == null) {
            return new KetQua(0, "Tai khoan khong ton tai!");
        } else {
            if (tk.getPassword().equals(login.getPassword())) {
                if (tk.getTenChucVu().equals("Quản lý")) {
                    return new KetQua(1, "Quanly");
                } else {
                    return new KetQua(1, "NhanVien");
                }
            } else {
                return new KetQua(0, "Sai mat khau!");
            }
        }
    }

    public static void main(String[] args) {
        TaiKhoanServiceImpl tk = new TaiKhoanServiceImpl();
        KetQua x = tk.login(new LoginRequest("NV001", "password1"));
        System.out.println(x.toString());
    }

}
