package com.phone.view.form.thuoctinh;

import com.core.service.impl.ThuocTinhServiceImpl;
import com.phone.custom.component.Notification;
import com.phone.view.form.sanpham.FormThemNhanh;

public class FormThuocTinh extends javax.swing.JPanel {

    private ThuocTinhServiceImpl service = new ThuocTinhServiceImpl();
    private Notification notiSuccess;
    private Notification notiWarring;

    public FormThuocTinh() {
        initComponents();
    }

    private void openThemCTTT(String name, String tenBang) {
        FormCTTTHang formCTTT = new FormCTTTHang();
        formCTTT.handleEvent(name, tenBang);
        formCTTT.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHang = new javax.swing.JButton();
        btnChip = new javax.swing.JButton();
        btnHeDieuHanh = new javax.swing.JButton();
        btnMauSac = new javax.swing.JButton();
        btnBoNho = new javax.swing.JButton();
        btnPin = new javax.swing.JButton();
        btnCameraSau = new javax.swing.JButton();
        btnCameraTruoc = new javax.swing.JButton();
        btnRam = new javax.swing.JButton();
        btnManHinh = new javax.swing.JButton();

        setBackground(new java.awt.Color(147, 158, 168));

        btnHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-brand-64.png"))); // NOI18N
        btnHang.setText("   Hãng");
        btnHang.setActionCommand("Hãng");
        btnHang.setBorder(null);
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        btnChip.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-processor-64.png"))); // NOI18N
        btnChip.setText("    Chip");
        btnChip.setToolTipText("");
        btnChip.setBorder(null);
        btnChip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChipActionPerformed(evt);
            }
        });

        btnHeDieuHanh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHeDieuHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-android-64.png"))); // NOI18N
        btnHeDieuHanh.setText("Hệ điều hành");
        btnHeDieuHanh.setBorder(null);
        btnHeDieuHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeDieuHanhActionPerformed(evt);
            }
        });

        btnMauSac.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-color-64.png"))); // NOI18N
        btnMauSac.setText("Màu sắc");
        btnMauSac.setBorder(null);
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        btnBoNho.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBoNho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-rom-64.png"))); // NOI18N
        btnBoNho.setText("  Bộ nhớ");
        btnBoNho.setBorder(null);
        btnBoNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoNhoActionPerformed(evt);
            }
        });

        btnPin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-battery-64.png"))); // NOI18N
        btnPin.setText("     Pin");
        btnPin.setToolTipText("");
        btnPin.setBorder(null);
        btnPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPinActionPerformed(evt);
            }
        });

        btnCameraSau.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCameraSau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-dual-camera-64.png"))); // NOI18N
        btnCameraSau.setText("Camera sau");
        btnCameraSau.setBorder(null);
        btnCameraSau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCameraSauActionPerformed(evt);
            }
        });

        btnCameraTruoc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCameraTruoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-phone-camera-64.png"))); // NOI18N
        btnCameraTruoc.setText("Camera trước");
        btnCameraTruoc.setBorder(null);
        btnCameraTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCameraTruocActionPerformed(evt);
            }
        });

        btnRam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-ram-64.png"))); // NOI18N
        btnRam.setText("     Ram");
        btnRam.setBorder(null);
        btnRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamActionPerformed(evt);
            }
        });

        btnManHinh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnManHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/custom/icon/icon_thuoctinh/icons8-mobile-phone-64.png"))); // NOI18N
        btnManHinh.setText("Màn hình");
        btnManHinh.setBorder(null);
        btnManHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManHinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChip, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCameraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCameraSau, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPin, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRam, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCameraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRam, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCameraSau, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPin, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHang, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215)
                        .addComponent(btnChip, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        // TODO add your handling code here:
        openThemCTTT("hãng", "Hang");
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnHeDieuHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeDieuHanhActionPerformed
        // TODO add your handling code here:
        openThemCTTT("hệ điều hành", "HeDieuHanh");
    }//GEN-LAST:event_btnHeDieuHanhActionPerformed

    private void btnChipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChipActionPerformed
        // TODO add your handling code here:
        openThemCTTT("chip", "Chip");
    }//GEN-LAST:event_btnChipActionPerformed

    private void btnManHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManHinhActionPerformed
        // TODO add your handling code here:
        openThemCTTT("màn hình", "ManHinh");
    }//GEN-LAST:event_btnManHinhActionPerformed

    private void btnCameraTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCameraTruocActionPerformed
        // TODO add your handling code here:
        openThemCTTT("camera trước", "CameraTruoc");
    }//GEN-LAST:event_btnCameraTruocActionPerformed

    private void btnCameraSauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCameraSauActionPerformed
        // TODO add your handling code here:
        openThemCTTT("camera sau", "CameraSau");
    }//GEN-LAST:event_btnCameraSauActionPerformed

    private void btnPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPinActionPerformed
        // TODO add your handling code here:
        openThemCTTT("pin", "Pin");
    }//GEN-LAST:event_btnPinActionPerformed

    private void btnRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamActionPerformed
        // TODO add your handling code here:
        openThemCTTT("ram", "Ram");
    }//GEN-LAST:event_btnRamActionPerformed

    private void btnBoNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoNhoActionPerformed
        // TODO add your handling code here:
        openThemCTTT("bộ nhớ", "BoNho");
    }//GEN-LAST:event_btnBoNhoActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        // TODO add your handling code here:
        openThemCTTT("màu sắc", "MauSac");
    }//GEN-LAST:event_btnMauSacActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBoNho;
    private javax.swing.JButton btnCameraSau;
    private javax.swing.JButton btnCameraTruoc;
    private javax.swing.JButton btnChip;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnHeDieuHanh;
    private javax.swing.JButton btnManHinh;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnPin;
    private javax.swing.JButton btnRam;
    // End of variables declaration//GEN-END:variables
}
