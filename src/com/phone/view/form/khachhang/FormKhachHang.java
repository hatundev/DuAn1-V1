package com.phone.view.form.khachhang;

import com.core.model.response.KhachHangResponse;
import com.core.repository.KhachHangRepository;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormKhachHang extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel();
    List<KhachHangResponse> listHD = new ArrayList<>();
    List<KhachHangResponse> listKHD = new ArrayList<>();
    KhachHangRepository khachHangRepository = new KhachHangRepository();
    int index;

    public FormKhachHang() {
        initComponents();
//        setOpaque(false);
        init();
        lbID.setVisible(false);
    }

    private void init() {
        showFromAll();
        showInactiveCustomers();
    }

    public void showFromAll() {
        try {
            listHD = khachHangRepository.getShowKH();
            model = (DefaultTableModel) tblKhachHang.getModel();
            model.setRowCount(0);
            for (KhachHangResponse khachHangResponse : listHD) {
                model.addRow(new Object[]{
                    khachHangResponse.getStt(),
                    khachHangResponse.getTenKhachHang(),
                    khachHangResponse.getSoDienThoai(),
                    khachHangResponse.getGhiChu(),
                    khachHangResponse.getHoatDong() == 1 ? "Hoạt động" : "Dừng hoạt động"});
            }
            listKHD = khachHangRepository.getShowKHX();
            model = (DefaultTableModel) tblKhachHangxoatam.getModel();
            model.setRowCount(0);
            for (KhachHangResponse khachHangResponse : listKHD) {
                model.addRow(new Object[]{
                    khachHangResponse.getStt(),
                    khachHangResponse.getTenKhachHang(),
                    khachHangResponse.getSoDienThoai(),
                    khachHangResponse.getGhiChu(),
                    khachHangResponse.getHoatDong() == 1 ? "Hoạt động" : "Dừng hoạt động"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInactiveCustomers() {
        try {
            listKHD = khachHangRepository.getShowKH(false);
            model = (DefaultTableModel) tblKhachHangxoatam.getModel();
            model.setRowCount(0);
            for (KhachHangResponse khachHangResponse : listKHD) {
                model.addRow(new Object[]{
                    khachHangResponse.getStt(),
                    khachHangResponse.getTenKhachHang(),
                    khachHangResponse.getSoDienThoai(),
                    khachHangResponse.getGhiChu(),
                    khachHangResponse.getHoatDong()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchProducts(String ten) {
        try {
            listHD = khachHangRepository.searchByName(ten);
            model = (DefaultTableModel) tblKhachHang.getModel();
            model.setRowCount(0);
            for (KhachHangResponse khachHangResponse : listHD) {
                model.addRow(new Object[]{
                    khachHangResponse.getStt(),
                    khachHangResponse.getTenKhachHang(),
                    khachHangResponse.getSoDienThoai(),
                    khachHangResponse.getGhiChu(),
                    khachHangResponse.getHoatDong() == 1 ? "Hoat dong" : "Khong hoat dong"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private KhachHangResponse getKhachHangResponse() {
        KhachHangResponse kh = new KhachHangResponse();
        kh.setId(listHD.get(index).getId());
        kh.setStt(listHD.get(index).getStt());
        kh.setTenKhachHang(txtTen.getText());
        kh.setSoDienThoai(txtSDT.getText());
        kh.setGhiChu(txtGhiChu.getText());
        kh.setHoatDong(1);
        return kh;
    }
    
     private KhachHangResponse getKhachHangResponse2() {
        KhachHangResponse kh = new KhachHangResponse();
        kh.setId(Integer.valueOf(lbID.getText()));
        kh.setStt(kh.getId());
        kh.setTenKhachHang(txtTen.getText());
        kh.setSoDienThoai(txtSDT.getText());
        kh.setGhiChu(txtGhiChu.getText());
        kh.setHoatDong(1);
        return kh;
    }

    private KhachHangResponse getKhachHangResponse1() {
        KhachHangResponse kh = new KhachHangResponse();
        kh.setId(listKHD.get(index).getId());
        kh.setStt(listKHD.get(index).getStt());
        kh.setTenKhachHang(txtTen.getText());
        kh.setSoDienThoai(txtSDT.getText());
        kh.setGhiChu(txtGhiChu.getText());
        kh.setHoatDong(0);
        return kh;
    }

    private void showRow() {
        index = tblKhachHang.getSelectedRow();
        KhachHangResponse data = listHD.get(index);
        txtTen.setText(data.getTenKhachHang());
        txtSDT.setText(data.getSoDienThoai());
        txtGhiChu.setText(data.getGhiChu());
//        if (index >= 0 && index < tblKhachHang.getRowCount()) {
//            txtTen.setText(tblKhachHang.getValueAt(index, 1).toString());
//            txtSDT.setText(tblKhachHang.getValueAt(index, 2).toString());
//            txtGhiChu.setText(tblKhachHang.getValueAt(index, 3).toString());
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng hợp lệ.");
//        }
    }

    private void showRowForDeleted() {
        index = tblKhachHangxoatam.getSelectedRow();
        if (index >= 0 && index < tblKhachHangxoatam.getRowCount()) {
            txtTen.setText(tblKhachHangxoatam.getValueAt(index, 1).toString());
            txtSDT.setText(tblKhachHangxoatam.getValueAt(index, 2).toString());
            txtGhiChu.setText(tblKhachHangxoatam.getValueAt(index, 3).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng hợp lệ.");
        }
    }

    public void Clear() {
        txtGhiChu.setText("");
        txtSDT.setText("");
        txtTen.setText("");
        txtTimkiem.setText("");
    }

    private boolean validateForm() {
        String tenKhachHang = txtTen.getText().trim();
        String soDienThoai = txtSDT.getText().trim();

        if (tenKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống.");
            return false;
        }

        if (soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống.");
            return false;
        }

        if (!soDienThoai.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số và có 10 chữ số.");
            return false;
        }

        return true;
    }

    private boolean validateSearchForm() {
        String tenKhachHang = txtTimkiem.getText().trim();

        if (tenKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ô tìm kiếm.");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHangxoatam = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTimkiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnSuaKH1 = new javax.swing.JButton();
        btnXoaKH1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnkhoiphuc = new javax.swing.JButton();
        lbID = new javax.swing.JLabel();

        setBackground(new java.awt.Color(147, 158, 168));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Nhập Thông Tin Khách Hàng");

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(147, 158, 168));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên Khách Hàng", "Số Điện Thoại", "Ghi Chú", "TrangThai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setRowHeight(50);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setMinWidth(50);
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblKhachHang.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1444, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khách Hàng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(147, 158, 168));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblKhachHangxoatam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblKhachHangxoatam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên Khách Hàng", "Số Điện Thoại", "Ghi Chú", "TrangThai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHangxoatam.setRowHeight(50);
        tblKhachHangxoatam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangxoatamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHangxoatam);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1444, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đã Xóa", jPanel2);

        jPanel3.setBackground(new java.awt.Color(147, 158, 168));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Tên khách hàng:");

        txtTimkiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Tìm Kiếm");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ghi Chú");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Họ tên khách hàng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số điện thoại");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSuaKH1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSuaKH1.setText("Sửa khách hàng");
        btnSuaKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKH1ActionPerformed(evt);
            }
        });

        btnXoaKH1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoaKH1.setText("Xóa khách hàng");
        btnXoaKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKH1ActionPerformed(evt);
            }
        });

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane3.setViewportView(txtGhiChu);

        btnkhoiphuc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnkhoiphuc.setText("Khôi Phục");
        btnkhoiphuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkhoiphucActionPerformed(evt);
            }
        });

        lbID.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTen)
                                    .addComponent(txtSDT)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnkhoiphuc)
                                .addGap(50, 50, 50)
                                .addComponent(btnSuaKH1)
                                .addGap(58, 58, 58)
                                .addComponent(btnXoaKH1)
                                .addGap(55, 55, 55))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(615, 615, 615)
                .addComponent(lbID)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(138, 138, 138))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSuaKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnkhoiphuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addComponent(lbID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)))
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
//        showRow();
        KhachHangResponse data = listHD.get(tblKhachHang.getSelectedRow());
        txtTen.setText(data.getTenKhachHang());
        txtSDT.setText(data.getSoDienThoai());
        txtGhiChu.setText(data.getGhiChu());
        lbID.setText(String.valueOf(data.getId()));
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        if (txtTimkiem.getText().isEmpty()) {
            init();
        } else {
            String ten = txtTimkiem.getText();
            searchProducts(ten);
            Clear();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnSuaKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKH1ActionPerformed
        // TODO add your handling code here:
        Integer idKH = null;
        try {
            idKH = listHD.get(tblKhachHang.getSelectedRow()).getId();
        } catch (Exception e) {

        }
        Integer idKHXoa = null;
        try {
            idKHXoa = listKHD.get(tblKhachHangxoatam.getSelectedRow()).getId();
        } catch (Exception e) {

        }
        if (idKH != null) {
            if (!validateForm()) {
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "Cảnh Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (hoi == JOptionPane.YES_OPTION) {
                KhachHangResponse khachHang = getKhachHangResponse();
                if (khachHangRepository.updateKhachHangtets(khachHang)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    showFromAll();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        }
        if (idKHXoa != null) {
            if (!validateForm()) {
                return;
            }

            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "Cảnh Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (hoi == JOptionPane.YES_OPTION) {
                KhachHangResponse khachHang = getKhachHangResponse1();
                if (khachHangRepository.updateKhachHangtets(khachHang)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    showFromAll();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        }

        if(idKH== null && idKHXoa == null){
            KhachHangResponse khachHang = getKhachHangResponse2();
                if (khachHangRepository.updateKhachHangtets(khachHang)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    showFromAll();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
        }
    }//GEN-LAST:event_btnSuaKH1ActionPerformed

    private void btnXoaKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKH1ActionPerformed
        // TODO add your handling code here:
        //list = khachHangRepository.getShowKH();
        Integer idKhachHang = listHD.get(tblKhachHang.getSelectedRow()).getId();
        boolean isUpdated = khachHangRepository.updateStatusKhachHang(idKhachHang, txtGhiChu.getText(), 0);
//
        if (isUpdated) {
            JOptionPane.showMessageDialog(this, "Khách hàng đã được chuyển vào danh sách xóa tạm thời");
            init();
            Clear();
//            showFromAll();
//            showInactiveCustomers();
//            Clear();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật trạng thái khách hàng thất bại");
        }
    }//GEN-LAST:event_btnXoaKH1ActionPerformed

    private void tblKhachHangxoatamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangxoatamMouseClicked
        showRowForDeleted();
    }//GEN-LAST:event_tblKhachHangxoatamMouseClicked

    private void btnkhoiphucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhoiphucActionPerformed
        Integer idKH = null;
        try {
            idKH = listHD.get(tblKhachHang.getSelectedRow()).getId();
        } catch (Exception e) {

        }
        Integer idKHXoa = null;
        try {
            idKHXoa = listKHD.get(tblKhachHangxoatam.getSelectedRow()).getId();
        } catch (Exception e) {

        }
        if (idKH != null) {
            JOptionPane.showMessageDialog(this, "Khách hàng đã xóa mới khôi phục được", "Thông báo", 0);
        }
        if (idKHXoa != null) {
            boolean acctive = khachHangRepository.acctiveKH(idKHXoa);
            if (acctive) {
                JOptionPane.showMessageDialog(this, "Khôi phục thành công");
                showFromAll();
            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục thất bại");
            }
        }
//if (!validateForm()) {
//            return;
//        }
//        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn khôi phục khách hàng?", "Cảnh Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        if (hoi == JOptionPane.YES_OPTION) {
//            KhachHangResponse khachHang = getKhachHangResponse();
//            if (khachHangRepository.updateKhachHang(khachHang)) {
//                JOptionPane.showMessageDialog(this, "Khôi phục thành công!");
//                init();
//                Clear();
//            } else {
//                JOptionPane.showMessageDialog(this, "Khôi phục thất bại!");
//            }
//        }
    }//GEN-LAST:event_btnkhoiphucActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaKH1;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaKH1;
    private javax.swing.JButton btnkhoiphuc;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbID;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblKhachHangxoatam;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
