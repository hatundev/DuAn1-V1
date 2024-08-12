/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.model.request;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class TimKiemRequest {
    
    private String timKiem;
    
    private Float giaBatDau;
    
    private Float giaKetThuc;
    
    private Integer idHang;
    
    private Integer idRam;
    
    private Integer idBoNho;
    
    private Integer idMauSac;
    
}
