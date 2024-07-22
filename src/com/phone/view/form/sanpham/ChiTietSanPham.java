/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.phone.view.form.sanpham;

import com.core.entity.Imei;
import com.core.entity.KetQua;
import com.core.model.request.ChiTietSanPhamRequest;
import com.core.model.response.ChiTietSanPhamResponse;
import com.core.model.response.ThuocTinhResponse;
import com.core.service.ChiTietSanPhamService;
import com.core.service.ImeiService;
import com.core.service.SanPhamService;
import com.core.service.impl.ThuocTinhServiceImpl;
import com.phone.swing.Combobox;
import com.phone.view.main.Main;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hatun
 */
public class ChiTietSanPham extends javax.swing.JPanel {

    private ChiTietSanPhamService chiTietSanPhamService = new ChiTietSanPhamService();
    private SanPhamService sanPhamService = new SanPhamService();
    private ThuocTinhServiceImpl service = new ThuocTinhServiceImpl();
    private ChiTietSanPhamResponse data = new ChiTietSanPhamResponse();
    private ImeiService imeiService = new ImeiService();
    private List<Imei> listImei = new ArrayList<>();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    /**
     * Creates new form ChiTietSanPham
     */
    public ChiTietSanPham() {
        initComponents();
        setSize(1500, 825);
    }

    public ChiTietSanPham(int id) {
        initComponents();
        setSize(1500, 825);
        defaultTableModel = (DefaultTableModel) tbImei.getModel();
        init(id);
    }

    public void init(int id) {
        fillDataCombobox();
        data = sanPhamService.findByID(id);
        fillForm(data);
        fillTableImei(id);

    }

    public void fillTableImei(int id) {
        listImei.clear();
        defaultTableModel.setRowCount(0);
        listImei = imeiService.findByIdCTSP(id);
        for (Imei imei : listImei) {
            defaultTableModel.addRow(imei.toRowTable());
        }
    }

    private void fillForm(ChiTietSanPhamResponse data) {
        txtId.setText(String.valueOf(data.getIdChiTietSanPham()));
        txtTenSP.setText(data.getTenSanPham());
        txtSoLuong.setText(String.valueOf(data.getSoLuong()));
        txtTenCTSP.setText(data.getTenSanPhamChiTiet());
        txtGiaBan.setText(String.valueOf(data.getGiaBan()));
        cbHang.setSelectedItem(data.getTenHang());
        cbHeDieuHanh.setSelectedItem(data.getTenHeDieuHanh());
        cbChip.setSelectedItem(data.getTenChip());
        cbManHinh.setSelectedItem(data.getTenManHinh());
        cbCameraTruoc.setSelectedItem(data.getTenCameraTruoc());
        cbCameraSau.setSelectedItem(data.getTenCameraSau());
        cbPin.setSelectedItem(data.getTenPin());
        cbRam.setSelectedItem(data.getTenRam());
        cbBoNho.setSelectedItem(data.getTenBoNho());
        cbMauSac.setSelectedItem(data.getTenMauSac());
        txtNgayTao.setText(data.getNgayTao());
        txtNguoiTao.setText(data.getNguoiTao());
        txtNgaySua.setText(data.getNgaySua() == null ? "" : data.getNguoiSua());
        txtNguoiSua.setText(data.getNguoiSua() == null ? "" : data.getNguoiSua());
        rdbYeuThich.setSelected(data.getYeuThich() == 1 ? true : false);
        //rdbHoatDong.setSelected(data.getHoatDong() == 1 ? true : false);
    }

    private void fillDataCombobox() {
        fillDataToCombobox(cbChip, service.findAll("Chip"));
        fillDataToCombobox(cbHeDieuHanh, service.findAll("HeDieuHanh"));
        fillDataToCombobox(cbCameraSau, service.findAll("CameraSau"));
        fillDataToCombobox(cbCameraTruoc, service.findAll("CameraTruoc"));
        fillDataToCombobox(cbPin, service.findAll("Pin"));
        fillDataToCombobox(cbHang, service.findAll("Hang"));
        fillDataToCombobox(cbManHinh, service.findAll("ManHinh"));
        fillDataToCombobox(cbRam, service.findAll("Ram"));
        fillDataToCombobox(cbBoNho, service.findAll("BoNho"));
        fillDataToCombobox(cbMauSac, service.findAll("MauSac"));
    }

    private void fillDataToCombobox(Combobox combo, List<ThuocTinhResponse> listData) {
        for (ThuocTinhResponse thuocTinhResponse : listData) {
            combo.addItem(thuocTinhResponse.getName());
        }
    }

    private int getidSelectedCombobox(Combobox combo, String tenBang) {
        return service.findAll(tenBang).get(combo.getSelectedIndex()).getId();
    }

    private ChiTietSanPhamRequest readForm() {
        ChiTietSanPhamRequest request = ChiTietSanPhamRequest.builder()
                .idSanPham(data.getIdSanPham())
                .idChiTietSanPham(data.getIdChiTietSanPham())
                .idHeDieuHanh(getidSelectedCombobox(cbHeDieuHanh,"HeDieuHanh"))
                .idManHinh(getidSelectedCombobox(cbManHinh, "ManHinh"))
                .idHang(getidSelectedCombobox(cbHang,"Hang"))
                .idCameraTruoc(getidSelectedCombobox(cbCameraTruoc, "CameraTruoc"))
                .idCameraSau(getidSelectedCombobox(cbCameraSau, "CameraSau"))
                .idChip(getidSelectedCombobox(cbChip, "Chip"))
                .idPin(getidSelectedCombobox(cbPin, "Pin"))
                .tenSanPham(txtTenSP.getText())
                .idRam(getidSelectedCombobox(cbRam,"Ram"))
                .idBoNho(getidSelectedCombobox(cbBoNho, "BoNho"))
                .idMauSac(getidSelectedCombobox(cbMauSac,"MauSac"))
                .tenSanPhamChiTiet(txtTenSP.getText()+String.valueOf(cbRam.getSelectedItem())+String.valueOf(cbBoNho.getSelectedItem())+ String.valueOf(cbMauSac.getSelectedItem()))
                .giaBan(Float.valueOf(txtGiaBan.getText()))
                .yeuThich(rdbYeuThich.isSelected() == true ? 1: 0)
                .ngayTao(data.getNgayTao())
                .nguoiTao(data.getNguoiTao())
                .ngaySua("")
                .nguoiSua("")
                .nguoiTao(data.getNguoiTao())
                .nguoiSua("")
                .yeuThich(rdbYeuThich.isSelected() == true ? 1 : 0)
                .hoatDong(1)
                .build();
        return request;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNgayTao = new com.phone.swing.TextField();
        txtNguoiTao = new com.phone.swing.TextField();
        cbChip = new com.phone.swing.Combobox();
        rdbYeuThich = new com.phone.swing.RadioButtonCustom();
        cbManHinh = new com.phone.swing.Combobox();
        txtNgaySua = new com.phone.swing.TextField();
        txtId = new com.phone.swing.TextField();
        cbCameraTruoc = new com.phone.swing.Combobox();
        txtNguoiSua = new com.phone.swing.TextField();
        txtTenSP = new com.phone.swing.TextField();
        txtSoLuong = new com.phone.swing.TextField();
        cbCameraSau = new com.phone.swing.Combobox();
        txtTenCTSP = new com.phone.swing.TextField();
        cbPin = new com.phone.swing.Combobox();
        txtGiaBan = new com.phone.swing.TextField();
        cbRam = new com.phone.swing.Combobox();
        cbHang = new com.phone.swing.Combobox();
        cbBoNho = new com.phone.swing.Combobox();
        cbHeDieuHanh = new com.phone.swing.Combobox();
        cbMauSac = new com.phone.swing.Combobox();
        btnXoa = new com.phone.swing.ButtonDanger();
        btnSua = new com.phone.swing.ButtonWarring();
        jPanel2 = new javax.swing.JPanel();
        txtImei = new com.phone.swing.TextField();
        btnThemImei = new com.phone.swing.ButtonPrimary();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbImei = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtIdImei = new com.phone.swing.TextField();
        txtDetailImei = new com.phone.swing.TextField();
        btnSuaImei = new com.phone.swing.ButtonWarring();
        btnXoaImei = new com.phone.swing.ButtonDanger();

        setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel1.setText("CHI TIẾT SẢN PHẨM");

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel2.setText("QUẢN LÝ IMEI");

        jPanel1.setOpaque(false);

        txtNgayTao.setEditable(false);
        txtNgayTao.setLabelText("Ngày tạo");

        txtNguoiTao.setEditable(false);
        txtNguoiTao.setLabelText("Người tạo");

        cbChip.setLabeText("Chip");

        rdbYeuThich.setText("Yêu thích");

        cbManHinh.setLabeText("Màn hình");

        txtNgaySua.setEditable(false);
        txtNgaySua.setLabelText("Ngày sửa");

        txtId.setEditable(false);
        txtId.setLabelText("ID");

        cbCameraTruoc.setLabeText("Camera trước");

        txtNguoiSua.setEditable(false);
        txtNguoiSua.setLabelText("Người sửa");

        txtTenSP.setLabelText("Tên sản phẩm");

        txtSoLuong.setEditable(false);
        txtSoLuong.setLabelText("Số lượng");

        cbCameraSau.setLabeText("Camera sau");

        txtTenCTSP.setLabelText("Tên chi tiết sản phẩm");
        txtTenCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCTSPActionPerformed(evt);
            }
        });

        cbPin.setLabeText("Pin");

        txtGiaBan.setLabelText("Giá bán");

        cbRam.setLabeText("Ram");

        cbHang.setLabeText("Hãng");

        cbBoNho.setLabeText("Bộ nhớ");

        cbHeDieuHanh.setLabeText("Hệ điều hành");

        cbMauSac.setLabeText("Màu sắc");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTenCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbChip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCameraTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbBoNho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbHeDieuHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbManHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCameraSau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(txtNgaySua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNguoiTao, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(txtNguoiSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addComponent(rdbYeuThich, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbChip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCameraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCameraSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(rdbYeuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );

        jPanel2.setOpaque(false);

        txtImei.setLabelText("Imei");

        btnThemImei.setText("Thêm");
        btnThemImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemImeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnThemImei, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel3.setOpaque(false);

        tbImei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "STT", "Imei"
            }
        ));
        tbImei.setRowHeight(50);
        tbImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbImeiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbImei);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel3.setText("Chi tiết imei");

        txtIdImei.setEditable(false);
        txtIdImei.setLabelText("ID");

        txtDetailImei.setLabelText("Mã Imei");

        btnSuaImei.setText("Sửa");
        btnSuaImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaImeiActionPerformed(evt);
            }
        });

        btnXoaImei.setText("Xóa");
        btnXoaImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaImeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(202, 202, 202))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtIdImei, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(txtDetailImei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(btnSuaImei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaImei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDetailImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel1)))
                .addGap(128, 128, 128)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(46, 46, 46)))
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Sửa sản phẩm",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

        // 0=yes, 1=no, 2=cancel
        if (input == 0) {
            //Thuc hien xoa mem
        } else if (input == 1) {
            fillForm(data);
        } else {

        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTenCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCTSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCTSPActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Xóa sản phẩm",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        // 0=yes, 1=no, 2=cancel
        if (input == 0) {
            //Thuc hien xoa mem
            KetQua ketQua = chiTietSanPhamService.delete(data.getIdChiTietSanPham());
            if (ketQua.getIdKetQua() == 1) {
                Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
                mainFrame.showNotiSuccess(ketQua.getThongBao());
                this.init(data.getIdChiTietSanPham());
            } else {
                Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
                mainFrame.showNotiWarring(ketQua.getThongBao());
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemImeiActionPerformed
        // TODO add your handling code here:
        if (txtImei.getText().isEmpty()) {
            Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
            mainFrame.showNotiWarring("Chưa nhập imei!");
            txtImei.requestFocus();
        } else {
            KetQua ketQua = imeiService.create(data.getIdChiTietSanPham(), txtImei.getText());
            if (ketQua.getIdKetQua() == 1) {
                Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
                mainFrame.showNotiSuccess(ketQua.getThongBao());
                this.init(data.getIdChiTietSanPham());
            } else {
                Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
                mainFrame.showNotiWarring(ketQua.getThongBao());
            }
        }

    }//GEN-LAST:event_btnThemImeiActionPerformed

    private void tbImeiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbImeiMouseClicked
        // TODO add your handling code here:
        int id = listImei.get(tbImei.getSelectedRow()).getId();
        txtIdImei.setText(String.valueOf(listImei.get(tbImei.getSelectedRow()).getId()));
        txtDetailImei.setText(listImei.get(tbImei.getSelectedRow()).getMaImei());
    }//GEN-LAST:event_tbImeiMouseClicked

    private void btnXoaImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaImeiActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Xóa imei",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        // 0=yes, 1=no, 2=cancel
        if (input == 0) {
            KetQua ketQua = imeiService.delete(txtDetailImei.getText());
            if (ketQua.getIdKetQua() == 1) {
                Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
                mainFrame.showNotiSuccess(ketQua.getThongBao());
                this.init(data.getIdChiTietSanPham());
                txtIdImei.setText("");
                txtDetailImei.setText("");
            } else {
                Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
                mainFrame.showNotiWarring(ketQua.getThongBao());
            }
        }
    }//GEN-LAST:event_btnXoaImeiActionPerformed

    private void btnSuaImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaImeiActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Sửa imei",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        // 0=yes, 1=no, 2=cancel
        if (input == 0) {

        }
    }//GEN-LAST:event_btnSuaImeiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.phone.swing.ButtonWarring btnSua;
    private com.phone.swing.ButtonWarring btnSuaImei;
    private com.phone.swing.ButtonPrimary btnThemImei;
    private com.phone.swing.ButtonDanger btnXoa;
    private com.phone.swing.ButtonDanger btnXoaImei;
    private com.phone.swing.Combobox cbBoNho;
    private com.phone.swing.Combobox cbCameraSau;
    private com.phone.swing.Combobox cbCameraTruoc;
    private com.phone.swing.Combobox cbChip;
    private com.phone.swing.Combobox cbHang;
    private com.phone.swing.Combobox cbHeDieuHanh;
    private com.phone.swing.Combobox cbManHinh;
    private com.phone.swing.Combobox cbMauSac;
    private com.phone.swing.Combobox cbPin;
    private com.phone.swing.Combobox cbRam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.phone.swing.RadioButtonCustom rdbYeuThich;
    private javax.swing.JTable tbImei;
    private com.phone.swing.TextField txtDetailImei;
    private com.phone.swing.TextField txtGiaBan;
    private com.phone.swing.TextField txtId;
    private com.phone.swing.TextField txtIdImei;
    private com.phone.swing.TextField txtImei;
    private com.phone.swing.TextField txtNgaySua;
    private com.phone.swing.TextField txtNgayTao;
    private com.phone.swing.TextField txtNguoiSua;
    private com.phone.swing.TextField txtNguoiTao;
    private com.phone.swing.TextField txtSoLuong;
    private com.phone.swing.TextField txtTenCTSP;
    private com.phone.swing.TextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
