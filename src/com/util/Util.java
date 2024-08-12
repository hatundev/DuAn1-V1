/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.util;

import java.text.NumberFormat;
import java.util.Locale;
/**
 *
 * @author hatundev
 */
public class Util {
    
    public String convertFloatToMoney(float money){
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return currencyFormatter.format(money) + " VND";
    }
    
    public static void main(String[] args) {
        float x = 50000000;
        Util convertMoney = new Util();
        System.out.println(convertMoney.convertFloatToMoney(x));
    }
}
