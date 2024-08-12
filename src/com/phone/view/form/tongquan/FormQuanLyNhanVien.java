/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.phone.view.form.tongquan;

import com.core.entity.KetQua;
import com.core.entity.NhanVien;
import com.core.model.response.NhanVienResponse;
import com.core.model.response.TaiKhoanResponse;
import com.core.repository.NhanVienRepository;
import com.phone.custom.component.Notification;
import com.phone.view.main.Main;
import com.util.Util;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hatundev
 */
public class FormQuanLyNhanVien extends javax.swing.JPanel {

    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private Util util = new Util();
    private List<NhanVienResponse> listTable = new ArrayList<>();
    private Notification notiSuccess;
    private Notification notiWarringl;
    private TaiKhoanResponse account;
    private NhanVienResponse nhanVienResponse;

    /**
     * Creates new form FormQuanLyNhanVien
     */
    public FormQuanLyNhanVien() {
        initComponents();
        init();
    }

    private void init() {
        defaultTableModel = (DefaultTableModel) tbNhanVien.getModel();
        listTable = nhanVienRepository.findAll();
        fillTable(listTable);
        fillData(null);
        tbNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = tbNhanVien.getSelectedRow();
                    nhanVienResponse = listTable.get(index);
                    fillData(nhanVienResponse);
                    showSuccess("Chọn nhân viên thành công");
                }
            }
        });
    }

    public void setAccount(TaiKhoanResponse tk) {
        this.account = tk;
    }

    private void showWarring(String message) {
        Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
        mainFrame.showNotiWarring(message);
    }

    private void showSuccess(String message) {
        Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
        mainFrame.showNotiSuccess(message);
    }

    private void fillTable(List<NhanVienResponse> list) {
        defaultTableModel.setRowCount(0);
        for (NhanVienResponse nhanVienResponse : list) {
            defaultTableModel.addRow(new Object[]{
                nhanVienResponse.getStt(),
                nhanVienResponse.getMaNhanVien(),
                nhanVienResponse.getTenNhanVien(),
                nhanVienResponse.getSoDienThoai(),
                nhanVienResponse.getDiaChi(),
                nhanVienResponse.getCccd(),
                util.convertFloatToMoney(nhanVienResponse.getDoanhSo()),
                nhanVienResponse.getChucVu() == 1 ? "Quản lý" : "Nhân viên",
                nhanVienResponse.getHoatDong() == 1 ? "Còn hoạt động" : "Đã nghỉ"
            });
        }
    }

    private void fillData(NhanVienResponse data) {
        if (data != null) {
            txtMaNhanVien.setText(data.getMaNhanVien());
            txtHoTen.setText(data.getTenNhanVien());
            txtDiaChi.setText(data.getDiaChi());
            txtSDT.setText(data.getSoDienThoai());
            txtCCCD.setText(data.getCccd());
            cbChucVu.setSelectedIndex(data.getChucVu());
            lbNgayTao.setText(data.getNgayTao());
        } else {
            txtMaNhanVien.setText("");
            txtHoTen.setText("");
            txtDiaChi.setText("");
            txtSDT.setText("");
            txtCCCD.setText("");
            cbChucVu.setSelectedIndex(-1);
            lbNgayTao.setText("");
        }
    }

    private boolean validateSDT(String phoneNumber) {
        String regex = "0[1-9][0-9]{8}";
        return phoneNumber.matches(regex);
    }

    private boolean validateCCCD(String cccd) {
        String regex = "0[0-9]{11}";
        return cccd.matches(regex);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    private NhanVien readForm() {
        NhanVien nhanVien = null;
        String maNhanVien = txtMaNhanVien.getText();
        String tenNhanVien = txtHoTen.getText();
        String sdt = txtSDT.getText();
        String cccd = txtCCCD.getText();

        if (maNhanVien.isEmpty()) {
            showErrorDialog("Chưa nhập mã nhân viên. Vui lòng nhập lại.");
            return nhanVien;
        }

        if (tenNhanVien.isEmpty()) {
            showErrorDialog("Chưa nhập tên nhân viên. Vui lòng nhập lại.");
            return nhanVien;
        }

        if (sdt.isEmpty()) {
            showErrorDialog("Chưa nhập số điện thoại. Vui lòng nhập lại.");
            return nhanVien;
        }

        if (!validateSDT(sdt)) {
            showErrorDialog("Số điện thoại không hợp lệ. Vui lòng nhập lại. \n      Số điện thoại hợp lệ là dãy số gồm 10 chữ số và bắt đầu bằng số 0");
            return nhanVien;
        }

        if (cccd.isEmpty()) {
            showErrorDialog("Chưa nhập căn cước. Vui lòng nhập lại.");
            return nhanVien;
        }

        if (!validateCCCD(cccd)) {
            showErrorDialog("Căn cước không hợp lệ. Vui lòng nhập lại. \n       Căn cước hợp lệ là dãy số gồm 12 chữ số và bắt đầu bằng số 0");
            return nhanVien;
        }

        nhanVien = NhanVien.builder()
                .maNhanVien(maNhanVien)
                .tenNhanVien(tenNhanVien)
                .diaChi(txtDiaChi.getText())
                .soDienThoai(sdt)
                .cccd(cccd)
                .chucVu(cbChucVu.getSelectedIndex())
                .build();
        return nhanVien;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        txtSDT = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        cbChucVu = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lbNgayTao = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();

        setOpaque(false);

        tbNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên", "Họ và tên", "Số điện thoại", "Địa chỉ", "Căn cước", "Doanh số theo tháng", "Chức vụ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNhanVien.setRowHeight(50);
        jScrollPane2.setViewportView(tbNhanVien);
        if (tbNhanVien.getColumnModel().getColumnCount() > 0) {
            tbNhanVien.getColumnModel().getColumn(0).setMinWidth(50);
            tbNhanVien.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbNhanVien.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Chi tiết Nhân Viên");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Danh Sách Nhân Viên");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhân viên:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Họ và tên:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số điện thoại:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Căn cước công dân:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Ngày tạo:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Chức vụ:");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        txtCCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCCCDActionPerformed(evt);
            }
        });

        cbChucVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý" }));

        btnThem.setBackground(new java.awt.Color(13, 110, 253));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 193, 7));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(220, 53, 69));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lbNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnNew.setBackground(new java.awt.Color(56, 161, 105));
        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNew.setText("Làm mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaNhanVien)
                            .addComponent(txtHoTen)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txtCCCD)
                                        .addGap(1, 1, 1))
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(lbNgayTao))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(cbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(205, 205, 205)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(203, 203, 203))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(51, 1323, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(cbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lbNgayTao)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCCCDActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            if (nhanVienResponse != null) {
                KetQua kq = nhanVienRepository.delete(nhanVienResponse.getId());
                if (kq.getIdKetQua() == 1) {
                    showSuccess("Xóa nhân viên thành công");
                    init();
                } else {
                    showWarring("Xóa nhân viên thất bại");
                }
            } else {
                showWarring("Chưa chọn nhân viên");
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        nhanVienResponse = null;
        fillData(nhanVienResponse);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        NhanVien data = readForm();
        if (data != null) {
            if (data.getChucVu() == 1) {
                int input = JOptionPane.showConfirmDialog(null, "Chắc chắn thêm nhân viên quản lý?", "Thêm nhân viên",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                // 0=yes, 1=no, 2=cancel
                if (input == 0) {
                    boolean check = nhanVienRepository.create(data, account.getUsername());
                    if (check) {
                        showSuccess("Thêm thành công");
                        init();
                    } else {
                        showWarring("Thêm thất bại");
                    }
                }
            } else {
                boolean check = nhanVienRepository.create(data, account.getUsername());
                if (check) {
                    showSuccess("Thêm thành công");
                    init();
                } else {
                    showWarring("Thêm thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        NhanVien data = readForm();
        if (data != null) {
            if (data.getChucVu() == 1 && nhanVienResponse.getChucVu() == 0) {
                int input = JOptionPane.showConfirmDialog(null, "Chắc chắn chuyển nhân viên lên quản lý?", "Sửa nhân viên",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                // 0=yes, 1=no, 2=cancel
                if (input == 0) {
                    boolean check = nhanVienRepository.update(data, nhanVienResponse.getId());
                    if (check) {
                        showSuccess("Sửa thành công");
                        init();
                    } else {
                        showWarring("Sửa thất bại");
                    }
                }
            } else if (data.getChucVu() == 0 && nhanVienResponse.getChucVu() == 1) {
                JOptionPane.showMessageDialog(this, "Không được phép chuyển quản lý xuống nhân viên", "Thông báo", 2);
            } else {
                boolean check = nhanVienRepository.update(data, nhanVienResponse.getId());
                if (check) {
                    showSuccess("Sửa thành công");
                    init();
                } else {
                    showWarring("Sửa thất bại");
                }
            }

        }
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbChucVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNgayTao;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
