/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.entity;

import com.core.model.response.HoaDonChiTietResponse;
import com.core.repository.HoaDonChiTietRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hatun
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BoNho {
    
    private Integer id;
    
    private String ten;
    
    private Integer hoatDong;
    
    public static void main(String[] args) {
        HoaDonChiTietRepository repo = new HoaDonChiTietRepository();
        List<HoaDonChiTietResponse> data = repo.getGioHang(8);
        for (HoaDonChiTietResponse hoaDonChiTietResponse : data) {
            System.out.println(hoaDonChiTietResponse.toString());
        }
    }
    
}
