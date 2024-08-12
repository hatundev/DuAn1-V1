package com.phone.view.form.hoadon;

import com.core.model.response.HoaDonChiTietResponse;
import com.core.model.response.HoaDonChiTietResponse1;
import com.core.model.response.HoaDonChiTietResponse2;
import com.core.model.response.HoaDonResponse;
import com.core.repository.HoaDonChiTietRepository;
import com.core.repository.HoaDonRepository;
import com.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FormHoaDon extends javax.swing.JPanel {

    private List<HoaDonResponse> list = new ArrayList<>();
    private List<HoaDonChiTietResponse2> listHDCT = new ArrayList<>();
    private DefaultTableModel model = new DefaultTableModel();
    private Util util = new Util();
    HoaDonRepository rp = new HoaDonRepository();
    private HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    
    public FormHoaDon() {
        initComponents();
        fillTable();
        reset();
    }

    public void reset() {
        txttenNV.setText("");
        txttenKH.setText("");
        txtTenVoucher.setText("");
        txtChuyenKhoan.setText("");
        txtTienMat.setText("");
        txtNgayTao.setText("");
    }

    public void fillTable() {
        list = rp.showHoaDon();
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (HoaDonResponse hd : list) {
            model.addRow(new Object[]{
                hd.getStt(),
                hd.getTenKhachHang() == null ? "Trống" : hd.getTenKhachHang(),
                hd.getSdt() == null ? "Trống" : hd.getSdt(),
                hd.getMaNhanVien(),
                hd.getNgayTao(),
                util.convertFloatToMoney(hd.getGiaTien()),
                hd.getMaVouver() == null ? "Trống" : hd.getMaVouver(),
                util.convertFloatToMoney(hd.getTongTienSauGiam()),
                hd.getHoatDong() == 1 ? "Đã hoàn thành" : "Hóa đơn chờ"
            });
        }
    }

    public void fillTable1() {
        list = rp.showHoaDon1();
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (HoaDonResponse hd : list) {
            model.addRow(new Object[]{
                hd.getStt(),
                hd.getTenKhachHang() == null ? "Trống" : hd.getTenKhachHang(),
                hd.getSdt() == null ? "Trống" : hd.getSdt(),
                hd.getMaNhanVien(),
                hd.getNgayTao(),
                util.convertFloatToMoney(hd.getGiaTien()),
                hd.getMaVouver() == null ? "Trống" : hd.getMaVouver(),
                util.convertFloatToMoney(hd.getTongTienSauGiam()),
                hd.getHoatDong() == 1 ? "Đã hoàn thành" : "Hóa đơn chờ"
            });
        }
    }

    public void fillTable2() {
        list = rp.showHoaDon2();
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (HoaDonResponse hd : list) {
            model.addRow(new Object[]{
                hd.getStt(),
                hd.getTenKhachHang() == null ? "Trống" : hd.getTenKhachHang(),
                hd.getSdt() == null ? "Trống" : hd.getSdt(),
                hd.getMaNhanVien(),
                hd.getNgayTao(),
                util.convertFloatToMoney(hd.getGiaTien()),
                hd.getMaVouver() == null ? "Trống" : hd.getMaVouver(),
                util.convertFloatToMoney(hd.getTongTienSauGiam()),
                hd.getHoatDong() == 1 ? "Đã hoàn thành" : "Hóa đơn chờ"
            });
        }
    }

    public void searchHoaDon(String searchTerm) {
        list = rp.searchHoaDon(searchTerm);
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (HoaDonResponse hd : list) {
            model.addRow(new Object[]{
                hd.getStt(),
                hd.getTenKhachHang(),
                hd.getSdt(),
                hd.getMaNhanVien(),
                hd.getNgayTao(),
                util.convertFloatToMoney(hd.getGiaTien()),
                hd.getMaVouver(),
                util.convertFloatToMoney(hd.getTongTienSauGiam()),
                hd.getHoatDong() == 1 ? "Đã hoàn thành" : "Hóa đơn chờ"
            });
        }

    }

    private void fillChiTietHoaDon(HoaDonChiTietResponse1 chiTiet) {
        txttenNV.setText(chiTiet.getTenNhanVien());
        txttenKH.setText(chiTiet.getTenKhachHang());
        txtTenVoucher.setText(chiTiet.getMaVoucher());
        txtChuyenKhoan.setText(util.convertFloatToMoney(chiTiet.getChuyenKhoan()));
        txtTienMat.setText(util.convertFloatToMoney(chiTiet.getTienMat()));
        txtNgayTao.setText(chiTiet.getNgayTao().toString());
        // Điền thêm các trường khác nếu cần
    }

    public void fillTable3() {
        HoaDonChiTietRepository rp = new HoaDonChiTietRepository();
        List<HoaDonChiTietResponse> list = new ArrayList<>();
        list = rp.getHoaDonChiTiet1();
        model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        for (HoaDonChiTietResponse hd : list) {
            model.addRow(new Object[]{
                hd.getIdHoaDonChiTiet(),
                hd.getTenChiTietSanPham(),
                hd.getMaImei(),
                util.convertFloatToMoney(hd.getGiaBan()),});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txttenNV = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txttenKH = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenVoucher = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtChuyenKhoan = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTienMat = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(147, 158, 168));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm theo SDT:");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Khách Hàng", "SĐT", "Mã NV", "Ngày tạo", "Tổng tiền hàng", "Mã voucher", "Tổng tiền hóa đơn", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(50);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(50);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(3).setMinWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        table1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Tên chi tiết sản phẩm", "Imei", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setRowHeight(50);
        jScrollPane2.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setMinWidth(50);
            table1.getColumnModel().getColumn(0).setPreferredWidth(50);
            table1.getColumnModel().getColumn(0).setMaxWidth(50);
            table1.getColumnModel().getColumn(2).setMinWidth(200);
            table1.getColumnModel().getColumn(2).setPreferredWidth(200);
            table1.getColumnModel().getColumn(2).setMaxWidth(200);
            table1.getColumnModel().getColumn(3).setMinWidth(150);
            table1.getColumnModel().getColumn(3).setPreferredWidth(150);
            table1.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Người tạo: ");

        txttenNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttenNV.setText("Nguyễn Thanh Tùng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Khách hàng:");

        txttenKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttenKH.setText("Lê Thị Thu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Mã Voucher:");

        txtTenVoucher.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenVoucher.setText("VOUCHER0001");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Chuyển khoản: ");

        txtChuyenKhoan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtChuyenKhoan.setText("20.000.000 VNĐ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Tiền mặt:");

        txtTienMat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTienMat.setText("20.000.000 VNĐ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Ngày tạo:");

        txtNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNgayTao.setText("2024-01-01");

        jButton2.setText("Đã hoàn thành");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hóa đơn chờ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttenKH)
                    .addComponent(txttenNV)
                    .addComponent(txtTenVoucher))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayTao)
                    .addComponent(txtTienMat)
                    .addComponent(txtChuyenKhoan))
                .addGap(112, 112, 112)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txttenNV))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txttenKH)
                                    .addComponent(jLabel12)
                                    .addComponent(txtTienMat)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtChuyenKhoan)))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenVoucher)
                            .addComponent(jLabel14)
                            .addComponent(txtNgayTao)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        Integer idHD = list.get(table.getSelectedRow()).getId();
        fillChiTietHoaDon(rp.getHoaDonChiTiet(idHD));
        listHDCT = hoaDonChiTietRepository.showHDCT(idHD);
        model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        for (HoaDonChiTietResponse2 data : listHDCT) {
            model.addRow(new Object[]{
                data.getStt(), data.getTenSPCT(), data.getImei(), util.convertFloatToMoney(data.getGiaBan())
            });
        }
    }//GEN-LAST:event_tableMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
        String searchTerm = txtTimKiem.getText().trim();
        if (!searchTerm.isEmpty()) {
            searchHoaDon(searchTerm);
        } else {
            fillTable(); // Nếu ô tìm kiếm trống, hiển thị tất cả hóa đơn
        }
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        fillTable1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        fillTable2();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JLabel txtChuyenKhoan;
    private javax.swing.JLabel txtNgayTao;
    private javax.swing.JLabel txtTenVoucher;
    private javax.swing.JLabel txtTienMat;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JLabel txttenKH;
    private javax.swing.JLabel txttenNV;
    // End of variables declaration//GEN-END:variables
}
