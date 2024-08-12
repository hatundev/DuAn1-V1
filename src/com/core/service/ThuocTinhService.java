/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.KetQua;
import com.core.model.request.ThuocTinhRequest;
import com.core.model.response.ThuocTinhResponse;
import java.util.List;

/**
 *
 * @author hatun
 */
public interface ThuocTinhService {

    //Lấy danh sách đối tượng
    List<ThuocTinhResponse> findAll(String tenBang);

    List<ThuocTinhResponse> findAllHD(String tenBang);
    //Tìm đối tượng theo id
    ThuocTinhResponse findById(String tenBang, Integer id);

    //Thêm mới đối tượng
    KetQua create(String tenBang, String data);

    //Cập nhập đối tượng theo id
    Boolean update(String tenBang, Integer id, ThuocTinhRequest data);

    //Xóa đối tượng theo id
    Boolean delete(String tenBang, Integer id);

    List<ThuocTinhResponse> cboHoatDong(String tenBang);

    KetQua create1(String tenBang, String data, Integer hoatDong);

    Boolean update1(String tenBang, Integer id, ThuocTinhResponse data, Integer hoatDong);

}
