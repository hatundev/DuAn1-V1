/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hatundev
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VoucherBanHangResponse {
    
    private Integer stt;

    private String maVoucher;

    private String tenVoucher;

    private Integer soLuong;

    private Float giaTriToiThieuApDung;

    private Integer phanTramGiam;

    private Float soTienDuocGiamToiDa;

    private String ngayBatDau;

    private String ngayKetThuc;
}
