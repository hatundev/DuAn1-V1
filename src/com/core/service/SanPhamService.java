/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.core.service;

import com.core.entity.ChiTietSanPham;
import com.core.entity.KetQua;
import com.core.entity.SanPham;
import com.core.model.request.ChiTietSanPhamRequest;
import com.core.model.request.ThemSanPhamRequest;
import com.core.model.request.TimKiemRequest;
import com.core.model.response.ChiTietSanPhamResponse;
import com.core.model.response.SanPhamResponse;
import com.core.repository.ChiTietSanPhamRepository;
import com.core.repository.SanPhamRepository;
import java.util.List;

/**
 *
 * @author hatun
 */
public class SanPhamService {

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    
    public List<SanPhamResponse> getDataToTable(){
        return sanPhamRepository.findByPage();
    }
    
    public List<SanPhamResponse> getDataToTable(TimKiemRequest request){
        return sanPhamRepository.findByPage(request);
    }
    
    
    
    public Integer getSize(){
        return sanPhamRepository.getSize();
    }

    private SanPham convertRequestToSanPham(ThemSanPhamRequest data) {
        SanPham sp = SanPham.builder()
                .tenSanPham(data.getTenSanPham())
                .idChip(data.getIdChip())
                .idCameraSau(data.getIdCameraSau())
                .idCameraTruoc(data.getIdCameraTruoc())
                .idHang(data.getIdHang())
                .idHeDieuHanh(data.getIdHeDieuHanh())
                .idManHinh(data.getIdManHinh())
                .idPin(data.getIdPin())
                .ngayTao("")
                .nguoiTao("")
                .ngaySua("")
                .nguoiSua("")
                .hoatDong(1)
                .build();
        return sp;
    }

    private ChiTietSanPham convertRequestToCTSP(ThemSanPhamRequest data, Integer idSanPham) {
        ChiTietSanPham ctsp = ChiTietSanPham.builder()
                .idSanPham(idSanPham)
                .tenSanPhamChiTiet(data.getTenSanPhamChiTiet())
                .idRam(data.getIdRam())
                .idBoNho(data.getIdBoNho())
                .idMauSac(data.getIdMauSac())
                .giaBan(data.getGiaBan())
                .ngayTao("")
                .nguoiTao("")
                .ngaySua("")
                .nguoiSua("")
                .hoatDong(1)
                .yeuThich(1)
                .build();
        return ctsp;
    }
    
//    private SanPham convertRequestToSanPham(ChiTietSanPhamRequest data) {
//        SanPham sp = SanPham.builder()
//                .tenSanPham(data.getTenSanPham())
//                .idChip(data.getIdChip())
//                .idCameraSau(data.getIdCameraSau())
//                .idCameraTruoc(data.getIdCameraTruoc())
//                .idHang(data.getIdHang())
//                .idHeDieuHanh(data.getIdHeDieuHanh())
//                .idManHinh(data.getIdManHinh())
//                .idPin(data.getIdPin())
//                .ngayTao(data.getNgayTao())
//                .nguoiTao(data.getNguoiTao())
//                .ngaySua("")
//                .nguoiSua(data.getNguoiSua())
//                .hoatDong(1)
//                .build();
//        return sp;
//    }
//
//    private ChiTietSanPham convertRequestToCTSP(ChiTietSanPhamRequest data, Integer idSanPham) {
//        ChiTietSanPham ctsp = ChiTietSanPham.builder()
//                .idSanPham(idSanPham)
//                .tenSanPhamChiTiet(data.getTenSanPhamChiTiet())
//                .idRam(data.getIdRam())
//                .idBoNho(data.getIdBoNho())
//                .idMauSac(data.getIdMauSac())
//                .giaBan(data.getGiaBan())
//                .ngayTao("")
//                .nguoiTao("")
//                .ngaySua("")
//                .nguoiSua("")
//                .hoatDong(1)
//                .yeuThich(1)
//                .build();
//        return ctsp;
//    }
    

//    public KetQua create(ChiTietSanPhamRequest data) {
//        
//        SanPham sp = convertRequestToSanPham(data);
//        if (sanPhamRepository.findIdBySanPham(sp) == null) {
//            boolean resultAddSanPham = sanPhamRepository.create(sp);
//        }
//        
//        int idSanPham = sanPhamRepository.findIdBySanPham(sp);
//        
//        ChiTietSanPham ctsp = convertRequestToCTSP(data, idSanPham);
//        
//        if (chiTietSanPhamRepository.findIdBySanPham(ctsp) == null) {
//            boolean resultAddChiTietSanPham = chiTietSanPhamRepository.create(ctsp);
//            if (resultAddChiTietSanPham) {
//                return new KetQua(1 , "Thêm sản phẩm thành công");
//            } else {
//                return new KetQua(0,"Thêm sản phẩm thất bại");
//            }
//        } else {
//            return new KetQua(0,"Sản phẩm đã tồn tại");
//        }
//    }
    public KetQua create(ThemSanPhamRequest data, String username) {
        
        SanPham sp = convertRequestToSanPham(data);
        if (sanPhamRepository.findIdBySanPham(sp) == null) {
            boolean resultAddSanPham = sanPhamRepository.create(sp, username);
        }
        
        int idSanPham = sanPhamRepository.findIdBySanPham(sp);
        
        ChiTietSanPham ctsp = convertRequestToCTSP(data, idSanPham);
        
        if (chiTietSanPhamRepository.findIdBySanPham(ctsp) == null) {
            boolean resultAddChiTietSanPham = chiTietSanPhamRepository.create(ctsp, username);
            if (resultAddChiTietSanPham) {
                return new KetQua(1 , "Thêm sản phẩm thành công");
            } else {
                return new KetQua(0,"Thêm sản phẩm thất bại");
            }
        } else {
            return new KetQua(0,"Sản phẩm đã tồn tại");
        }
    }
    
//    public KetQua update(ChiTietSanPhamRequest data) {
//        
//        SanPham sp = convertRequestToSanPham(data);
//        if (sanPhamRepository.findIdBySanPham(sp) == null) {
//            boolean resultAddSanPham = sanPhamRepository.create(sp);
//        }
//        
//        int idSanPham = sanPhamRepository.findIdBySanPham(sp);
//        
//        ChiTietSanPham ctsp = convertRequestToCTSP(themSanPhamRequest, idSanPham);
//        
//        if (chiTietSanPhamRepository.findIdBySanPham(ctsp) == null) {
//            boolean resultAddChiTietSanPham = chiTietSanPhamRepository.create(ctsp);
//            if (resultAddChiTietSanPham) {
//                return new KetQua(1 , "Thêm sản phẩm thành công");
//            } else {
//                return new KetQua(0,"Thêm sản phẩm thất bại");
//            }
//        } else {
//            return new KetQua(0,"Sản phẩm đã tồn tại");
//        }
//    }

    public ChiTietSanPhamResponse findByID(int id){
        return chiTietSanPhamRepository.findByID(id);
    }
}
