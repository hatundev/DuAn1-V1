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
public class ThuocTinhResponse {
    
    private Integer stt;
    
    private Integer id;
    
    private String name;
    
    private Integer hoatDong;
    
    public Object[] toRowTable() {
        return new Object[]{
             stt, name, hoatDong==1 ?"Đang hoạt động": "Không hoạt động"
        };
    }
}
