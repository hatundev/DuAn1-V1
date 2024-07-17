/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.model.response;

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
public class SanPhamResponse {

    private Integer stt;
    
    private Integer id;

    private String tenSanPham;

    private String hang;

    private String ram;

    private String boNho;

    private String mauSac;

    private Integer soLuong;

    private Float gia;

    public Object[] toRowTable() {
        return new Object[]{
            stt, tenSanPham, hang, ram, boNho, mauSac, soLuong, gia
        };
    }

}
