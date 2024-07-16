/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phone.view.form.sanpham;

import com.core.entity.KetQua;
import com.core.model.request.ThemSanPhamRequest;
import com.core.model.response.ThuocTinhResponse;
import com.core.service.SanPhamService;
import com.core.service.impl.ThuocTinhServiceImpl;
import com.phone.custom.component.Notification;
import com.phone.swing.Combobox;
import java.awt.Panel;
import java.util.List;

/**
 *
 * @author hatun
 */
public class FormThemSanPham extends javax.swing.JFrame {

    private ThuocTinhServiceImpl service = new ThuocTinhServiceImpl();
    private SanPhamService sanPhamService = new SanPhamService();
    private Notification notiSuccess;
    private Notification notiWarring;
    private FormSanPham parentForm;

    /**
     * Creates new form FormThemSanPham
     */
    public FormThemSanPham() {
        initComponents();
    }

    public FormThemSanPham(FormSanPham parentForm) {
        this.parentForm = parentForm;
        initComponents();
        setLocationRelativeTo(null);
        init();
        reset();
    }

    private void init() {
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

    private void openThemNhanh(String name) {
        FormThemNhanh formThemNhanh = new FormThemNhanh();
        formThemNhanh.handleEvent(name);
        formThemNhanh.setVisible(true);
    }

    private void fillDataToCombobox(Combobox combo, List<ThuocTinhResponse> listData) {
        for (ThuocTinhResponse thuocTinhResponse : listData) {
            combo.addItem(thuocTinhResponse.getName());
        }
    }

    private void reset() {
        txtTenSanPham.setText("");
        txtGiaBan.setText("");
        cbChip.setSelectedIndex(-1);
        cbHeDieuHanh.setSelectedIndex(-1);
        cbCameraSau.setSelectedIndex(-1);
        cbCameraTruoc.setSelectedIndex(-1);
        cbPin.setSelectedIndex(-1);
        cbHang.setSelectedIndex(-1);
        cbManHinh.setSelectedIndex(-1);
        cbRam.setSelectedIndex(-1);
        cbBoNho.setSelectedIndex(-1);
        cbMauSac.setSelectedIndex(-1);
    }

    private Integer findIdByName(String name, List<ThuocTinhResponse> data) {
        for (ThuocTinhResponse thuocTinhResponse : data) {
            if (thuocTinhResponse.getName().equals(name)) {
                return thuocTinhResponse.getId();
            }
        }
        return 0;
    }

    private ThemSanPhamRequest readForm() {

        String name = txtTenSanPham.getText().trim();
        String stringPrice = txtGiaBan.getText();
        if (name.isEmpty()) {
            notiWarring = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Chưa nhập tên");
            notiWarring.showNotification();
            txtTenSanPham.requestFocus();
            return null;
        }

        if (stringPrice.isEmpty()) {
            notiWarring = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Chưa nhập giá");
            notiWarring.showNotification();
            txtGiaBan.requestFocus();
            return null;
        }

        Float price;
        try {
            price = Float.valueOf(stringPrice);
            if (price <= 0) {
                notiWarring = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Giá phải là số lớn hơn 0");
                notiWarring.showNotification();
                txtGiaBan.requestFocus();
                return null;
            }
        } catch (Exception e) {
            notiWarring = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Giá phải là số");
            notiWarring.showNotification();
            txtGiaBan.requestFocus();
            return null;
        }

        String fullName = name + " " + String.valueOf(cbRam.getSelectedItem())
                + " " + String.valueOf(cbBoNho.getSelectedItem())
                + " " + String.valueOf(cbMauSac.getSelectedItem());

        ThemSanPhamRequest themSanPhamRequest = new ThemSanPhamRequest(
                name,
                fullName,
                price,
                findIdByName(String.valueOf(cbChip.getSelectedItem()), service.findAll("Chip")),
                findIdByName(String.valueOf(cbHeDieuHanh.getSelectedItem()), service.findAll("HeDieuHanh")),
                findIdByName(String.valueOf(cbCameraSau.getSelectedItem()), service.findAll("CameraSau")),
                findIdByName(String.valueOf(cbCameraTruoc.getSelectedItem()), service.findAll("CameraTruoc")),
                findIdByName(String.valueOf(cbPin.getSelectedItem()), service.findAll("Pin")),
                findIdByName(String.valueOf(cbHang.getSelectedItem()), service.findAll("Hang")),
                findIdByName(String.valueOf(cbMauSac.getSelectedItem()), service.findAll("MauSac")),
                findIdByName(String.valueOf(cbRam.getSelectedItem()), service.findAll("Ram")),
                findIdByName(String.valueOf(cbBoNho.getSelectedItem()), service.findAll("BoNho")),
                findIdByName(String.valueOf(cbMauSac.getSelectedItem()), service.findAll("MauSac")));
        return themSanPhamRequest;
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
        txtTenSanPham = new com.phone.swing.TextField();
        txtGiaBan = new com.phone.swing.TextField();
        cbChip = new com.phone.swing.Combobox();
        cbHang = new com.phone.swing.Combobox();
        cbHeDieuHanh = new com.phone.swing.Combobox();
        cbManHinh = new com.phone.swing.Combobox();
        cbCameraSau = new com.phone.swing.Combobox();
        cbRam = new com.phone.swing.Combobox();
        cbCameraTruoc = new com.phone.swing.Combobox();
        cbBoNho = new com.phone.swing.Combobox();
        cbMauSac = new com.phone.swing.Combobox();
        cbPin = new com.phone.swing.Combobox();
        btnThemChip = new javax.swing.JLabel();
        btnThemCameraSau = new javax.swing.JLabel();
        btnThemHeDieuHanh = new javax.swing.JLabel();
        btnCameraTruoc = new javax.swing.JLabel();
        btnThemPin = new javax.swing.JLabel();
        btnThemHang = new javax.swing.JLabel();
        btnThemManHinh = new javax.swing.JLabel();
        btnThemRam = new javax.swing.JLabel();
        btnThemBoNho = new javax.swing.JLabel();
        btnThemMauSac = new javax.swing.JLabel();
        btnThem = new com.phone.swing.ButtonPrimary();
        btnExit = new com.phone.swing.ButtonInfo();
        btnReset = new com.phone.swing.ButtonWarring();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(246, 237, 237));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 3, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/social-media.png"))); // NOI18N
        jLabel1.setText("Thêm Sản Phẩm");

        txtTenSanPham.setLabelText("Tên sản phẩm");

        txtGiaBan.setLabelText("Giá bán");

        cbChip.setLabeText("Chip");

        cbHang.setLabeText("Hãng");

        cbHeDieuHanh.setLabeText("Hệ điều hành");

        cbManHinh.setLabeText("Màn hình");

        cbCameraSau.setLabeText("Camera Sau");

        cbRam.setLabeText("Ram");

        cbCameraTruoc.setLabeText("Camera Trước");

        cbBoNho.setLabeText("Bộ nhớ");

        cbMauSac.setLabeText("Màu sắc");

        cbPin.setLabeText("Pin");

        btnThemChip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemChip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemChip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemChipMouseClicked(evt);
            }
        });

        btnThemCameraSau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemCameraSau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemCameraSau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemCameraSauMouseClicked(evt);
            }
        });

        btnThemHeDieuHanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemHeDieuHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemHeDieuHanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemHeDieuHanhMouseClicked(evt);
            }
        });

        btnCameraTruoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCameraTruoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnCameraTruoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCameraTruocMouseClicked(evt);
            }
        });

        btnThemPin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemPin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemPinMouseClicked(evt);
            }
        });

        btnThemHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemHangMouseClicked(evt);
            }
        });

        btnThemManHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemManHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemManHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemManHinhMouseClicked(evt);
            }
        });

        btnThemRam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemRam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemRamMouseClicked(evt);
            }
        });

        btnThemBoNho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemBoNho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemBoNho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemBoNhoMouseClicked(evt);
            }
        });

        btnThemMauSac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/add-button.png"))); // NOI18N
        btnThemMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMauSacMouseClicked(evt);
            }
        });

        btnThem.setText("Add");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemChip)
                    .addComponent(btnThemCameraSau)
                    .addComponent(btnThemHeDieuHanh)
                    .addComponent(btnCameraTruoc)
                    .addComponent(btnThemPin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnThemHang))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnThemManHinh))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbRam, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnThemRam))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnThemBoNho))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(btnThemMauSac)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbChip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(cbHeDieuHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCameraSau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCameraTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbChip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemChip, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbCameraSau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemCameraSau, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemRam, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbCameraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCameraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemPin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemChipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemChipMouseClicked
        // TODO add your handling code here:
        openThemNhanh("chip");
    }//GEN-LAST:event_btnThemChipMouseClicked

    private void btnThemHeDieuHanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemHeDieuHanhMouseClicked
        // TODO add your handling code here:
        openThemNhanh("hệ điều hành");
    }//GEN-LAST:event_btnThemHeDieuHanhMouseClicked

    private void btnThemCameraSauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemCameraSauMouseClicked
        // TODO add your handling code here:
        openThemNhanh("camera sau");
    }//GEN-LAST:event_btnThemCameraSauMouseClicked

    private void btnCameraTruocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCameraTruocMouseClicked
        // TODO add your handling code here:
        openThemNhanh("camera trước");
    }//GEN-LAST:event_btnCameraTruocMouseClicked

    private void btnThemPinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemPinMouseClicked
        // TODO add your handling code here:
        openThemNhanh("pin");
    }//GEN-LAST:event_btnThemPinMouseClicked

    private void btnThemHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemHangMouseClicked
        // TODO add your handling code here:
        openThemNhanh("hãng");
    }//GEN-LAST:event_btnThemHangMouseClicked

    private void btnThemManHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemManHinhMouseClicked
        // TODO add your handling code here:
        openThemNhanh("màn hình");
    }//GEN-LAST:event_btnThemManHinhMouseClicked

    private void btnThemRamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemRamMouseClicked
        // TODO add your handling code here:
        openThemNhanh("ram");
    }//GEN-LAST:event_btnThemRamMouseClicked

    private void btnThemBoNhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemBoNhoMouseClicked
        // TODO add your handling code here:
        openThemNhanh("bộ nhớ");
    }//GEN-LAST:event_btnThemBoNhoMouseClicked

    private void btnThemMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMauSacMouseClicked
        // TODO add your handling code here:
        openThemNhanh("màu sắc");
    }//GEN-LAST:event_btnThemMauSacMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (readForm() != null) {
            KetQua ketQua = sanPhamService.create(readForm());
            if (ketQua.getIdKetQua() == 0) {
                notiWarring = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, ketQua.getThongBao());
                notiWarring.showNotification();
            } else {
                notiSuccess = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, ketQua.getThongBao());
                notiSuccess.showNotification();
                
                if (parentForm != null) {
                    parentForm.init();
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormThemSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormThemSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormThemSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormThemSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormThemSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCameraTruoc;
    private com.phone.swing.ButtonInfo btnExit;
    private com.phone.swing.ButtonWarring btnReset;
    private com.phone.swing.ButtonPrimary btnThem;
    private javax.swing.JLabel btnThemBoNho;
    private javax.swing.JLabel btnThemCameraSau;
    private javax.swing.JLabel btnThemChip;
    private javax.swing.JLabel btnThemHang;
    private javax.swing.JLabel btnThemHeDieuHanh;
    private javax.swing.JLabel btnThemManHinh;
    private javax.swing.JLabel btnThemMauSac;
    private javax.swing.JLabel btnThemPin;
    private javax.swing.JLabel btnThemRam;
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
    private com.phone.swing.TextField txtGiaBan;
    private com.phone.swing.TextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
