package com.phone.view.form.voucher;

import com.core.model.response.VouCherResponse;
import com.core.repository.VoucherRepository;
import com.phone.view.main.Main;
import com.util.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class FormVoucher extends javax.swing.JPanel {

    private Util util = new Util();
    DefaultTableModel model = new DefaultTableModel();
    List<VouCherResponse> list = new ArrayList<>();
    VoucherRepository vouCherRepository = new VoucherRepository();
    int index;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public FormVoucher() {
        initComponents();
//        setOpaque(false);
        init();
    }

    public void init() {
        showFromAll();
        showFromAll();
        showOngoingVouchers();
        showEndedVouchers();
    }

    public void showFromAll() {
        try {
            list = vouCherRepository.getShowVC();
            model = (DefaultTableModel) tblVoucher.getModel();
            model.setRowCount(0);
            for (VouCherResponse vouCherResponse : list) {
                model.addRow(new Object[]{
                    vouCherResponse.getSTT(),
                    vouCherResponse.getMaVouCher(),
                    vouCherResponse.getTenVouCher(),
                    util.convertFloatToMoney(vouCherResponse.getGiaTriToiThieuApDung()),
                    vouCherResponse.getPhanTramGiam(),
                    util.convertFloatToMoney(vouCherResponse.getSoTienDuocGiamToiDa()),
                    vouCherResponse.getNgayBatDau(),
                    vouCherResponse.getNgayKetThuc(),
                    vouCherResponse.getNgayTao(),
                    vouCherResponse.getNguoiTao(),
                    vouCherResponse.getSoLuong()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showOngoingVouchers() {
        try {
            list = vouCherRepository.getShowVC();
            model = (DefaultTableModel) tblVoucherdangdienra.getModel();
            model.setRowCount(0);
            for (VouCherResponse vouCherResponse : list) {
                if (isVoucherOngoing(vouCherResponse)) {
                    model.addRow(new Object[]{
                        vouCherResponse.getSTT(),
                        vouCherResponse.getMaVouCher(),
                        vouCherResponse.getTenVouCher(),
                        util.convertFloatToMoney(vouCherResponse.getGiaTriToiThieuApDung()),
                        vouCherResponse.getPhanTramGiam(),
                        util.convertFloatToMoney(vouCherResponse.getSoTienDuocGiamToiDa()),
                        vouCherResponse.getNgayBatDau(),
                        vouCherResponse.getNgayKetThuc(),
                        vouCherResponse.getNgayTao(),
                        vouCherResponse.getNguoiTao(),
                        vouCherResponse.getSoLuong()});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isVoucherOngoing(VouCherResponse voucher) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = convertToLocalDate(voucher.getNgayBatDau());
        LocalDate endDate = convertToLocalDate(voucher.getNgayKetThuc());
        return (today.isEqual(startDate) || today.isAfter(startDate)) && today.isBefore(endDate);
    }

    public void showEndedVouchers() {
        try {
            list = vouCherRepository.getShowVC();
            model = (DefaultTableModel) tblVoucherketthuc.getModel();
            model.setRowCount(0);
            for (VouCherResponse vouCherResponse : list) {
                if (isVoucherEnded(vouCherResponse)) {
                    model.addRow(new Object[]{
                        vouCherResponse.getSTT(),
                        vouCherResponse.getMaVouCher(),
                        vouCherResponse.getTenVouCher(),
                        util.convertFloatToMoney(vouCherResponse.getGiaTriToiThieuApDung()),
                        vouCherResponse.getPhanTramGiam(),
                        util.convertFloatToMoney(vouCherResponse.getSoTienDuocGiamToiDa()),
                        vouCherResponse.getNgayBatDau(),
                        vouCherResponse.getNgayKetThuc(),
                        vouCherResponse.getNgayTao(),
                        vouCherResponse.getNguoiTao(),
                        vouCherResponse.getSoLuong()});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isVoucherEnded(VouCherResponse voucher) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = convertToLocalDate(voucher.getNgayKetThuc());
        return today.isAfter(endDate);
    }

    private LocalDate convertToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }

    private void searchProducts(String ma) {
        try {
            list = vouCherRepository.searchByName(ma);
            model = (DefaultTableModel) tblVoucher.getModel();
            model.setRowCount(0);
            for (VouCherResponse vouCherResponse : list) {
                model.addRow(new Object[]{
                    vouCherResponse.getSTT(), 
                    vouCherResponse.getMaVouCher(), 
                    vouCherResponse.getTenVouCher(),
                    util.convertFloatToMoney(vouCherResponse.getGiaTriToiThieuApDung()), 
                    vouCherResponse.getPhanTramGiam(), 
                    util.convertFloatToMoney(vouCherResponse.getSoTienDuocGiamToiDa()),
                    vouCherResponse.getNgayBatDau(), 
                    vouCherResponse.getNgayKetThuc(), 
                    vouCherResponse.getNgayKetThuc(), 
                    vouCherResponse.getNgayTao(),
                    vouCherResponse.getNguoiTao(), 
                    vouCherResponse.getSoLuong()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateSearchForm() {
        String voucher = txtTimKiem.getText().trim();

        if (voucher.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào ô tìm kiếm.");
            return false;
        } 

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAdd = new com.phone.swing.ButtonPrimary();
        btnTimKiem = new com.phone.swing.ButtonPrimary();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblVoucherdangdienra = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVoucherketthuc = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(147, 158, 168));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Chương Trình Khuyến Mại");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Search");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tìm kiếm:");

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(147, 158, 168));

        jPanel4.setBackground(new java.awt.Color(147, 158, 168));

        tblVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phiếu Giảm Giá", "Tên Phiếu Giảm", "Giá Trị Tối Thiểu Được Áp Dụng", "Phần Trăm Giảm", "Số Tiền Được Giảm Tối Đa", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Ngày Tạo", "Người Tạo", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.setRowHeight(50);
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVoucher);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1470, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tất Cả", jPanel4);

        jPanel5.setBackground(new java.awt.Color(147, 158, 168));

        tblVoucherdangdienra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucherdangdienra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phiếu Giảm Giá", "Tên Phiếu Giảm", "Giá Trị Tối Thiểu Được Áp Dụng", "Phần Trăm Giảm", "Số Tiền Được Giảm Tối Đa", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Ngày Tạo", "Người Tạo", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucherdangdienra.setRowHeight(50);
        tblVoucherdangdienra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherdangdienraMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblVoucherdangdienra);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1470, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đang Diễn Ra", jPanel5);

        jPanel6.setBackground(new java.awt.Color(147, 158, 168));

        tblVoucherketthuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucherketthuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phiếu Giảm Giá", "Tên Phiếu Giảm", "Giá Trị Tối Thiểu Được Áp Dụng", "Phần Trăm Giảm", "Số Tiền Được Giảm Tối Đa", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Ngày Tạo", "Người Tạo", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucherketthuc.setRowHeight(50);
        tblVoucherketthuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherketthucMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblVoucherketthuc);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1470, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Kết Thúc", jPanel6);

        jPanel7.setBackground(new java.awt.Color(147, 158, 168));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1482, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(65, 65, 65)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(266, 266, 266))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        FormThemVoucher formThemVoucher = new FormThemVoucher();
        formThemVoucher.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        
        String ma = txtTimKiem.getText();
        if(ma.isEmpty()){
            showFromAll();
        } else {
          searchProducts(ma);  
        }
        

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        int row = tblVoucher.getSelectedRow();
        if (row >= 0) {
            String maVoucher = list.get(tblVoucher.getSelectedRow()).getMaVouCher();
            FormChiTietVouCher formChiTietVouCher = new FormChiTietVouCher(maVoucher, this);
            formChiTietVouCher.setVisible(true);
        }
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void tblVoucherdangdienraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherdangdienraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblVoucherdangdienraMouseClicked

    private void tblVoucherketthucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherketthucMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblVoucherketthucMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.phone.swing.ButtonPrimary btnAdd;
    private com.phone.swing.ButtonPrimary btnTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTable tblVoucherdangdienra;
    private javax.swing.JTable tblVoucherketthuc;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
