/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phone.view.form.voucher;

import com.core.model.response.VouCherResponse;
import com.core.repository.VoucherRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FormChiTietVouCher extends javax.swing.JFrame {

    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    VoucherRepository vouCherRepository = new VoucherRepository();
    List<VouCherResponse> list = new ArrayList<>();
    private String maVoucher;
    int index;
    private VouCherResponse voucher;
    private FormVoucher parentForm;

    /**
     * Creates new form FormChiTietVouCher
     */
    public FormChiTietVouCher(String maVoucher1, FormVoucher form) {
        parentForm = form;
        maVoucher = maVoucher1;
        initComponents();
        setLocationRelativeTo(null);
        loadVoucherDetails();

    }

    private void loadVoucherDetails() {
        try {
            VouCherResponse voucher = vouCherRepository.getVoucherByMa(maVoucher);
            if (voucher != null) {
                txtID.setText(String.valueOf(voucher.getId()));
                txtMaVouCher.setText(voucher.getMaVouCher());
                txtTenVouCher.setText(voucher.getTenVouCher());
                txtSoLuong.setText(String.valueOf(voucher.getSoLuong()));
                txtGiaTriToiThieuApDung.setText(String.valueOf(voucher.getGiaTriToiThieuApDung()));
                txtPhanTramGiam.setText(String.valueOf(voucher.getPhanTramGiam()));
                txtSoTienDuocGiamToiDa.setText(String.valueOf(voucher.getSoTienDuocGiamToiDa()));
                txtNgayBatDau.setText(voucher.getNgayBatDau());
                txtNgayKetThuc.setText(voucher.getNgayKetThuc());
                txtMoTa.setText(voucher.getMoTa());
                txtNgayTao.setText(voucher.getNgayTao());
                txtNgaySua.setText(voucher.getNgaySua() != null ? voucher.getNgaySua() : "");
                txtNguoiTao.setText(voucher.getNguoiTao() != null ? voucher.getNguoiTao() : "");
                txtNguoiSua.setText(voucher.getNguoiSua() != null ? voucher.getNguoiSua() : "");
                if (rdoHD.isSelected()) {
                    voucher.setHoatDong(1);
                } else {
                    voucher.setHoatDong(0);
                }
                LocalDate startDate = LocalDate.parse(voucher.getNgayBatDau());
                LocalDate endDate = LocalDate.parse(voucher.getNgayKetThuc());
                LocalDate today = LocalDate.now();
                if (startDate.isBefore(today)) {
                    JOptionPane.showMessageDialog(rootPane, "Voucher đang diễn ra nên bạn không được phép update các thông tin quan trọng", "Thông báo", 1);
                    txtGiaTriToiThieuApDung.setEditable(false);
                    txtSoTienDuocGiamToiDa.setEditable(false);
                    txtNgayBatDau.setEditable(false);
                    txtNgayKetThuc.setEditable(false);
                    txtPhanTramGiam.setEditable(false);
                    txtSoLuong.setEditable(false);
                } else if (startDate.isEqual(today)) {
                    JOptionPane.showMessageDialog(rootPane, "Voucher đang diễn ra nên bạn không được phép update các thông tin quan trọng", "Thông báo", 1);
                    txtGiaTriToiThieuApDung.setEditable(false);
                    txtSoTienDuocGiamToiDa.setEditable(false);
                    txtNgayBatDau.setEditable(false);
                    txtNgayKetThuc.setEditable(false);
                    txtPhanTramGiam.setEditable(false);
                    txtSoLuong.setEditable(false);
                } else {
                    txtGiaTriToiThieuApDung.setEditable(true);
                    txtSoTienDuocGiamToiDa.setEditable(true);
                    txtNgayBatDau.setEditable(true);
                    txtNgayKetThuc.setEditable(true);
                    txtPhanTramGiam.setEditable(true);
                    txtSoLuong.setEditable(true);
                }

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Voucher Không tìm thấy.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private VouCherResponse getVoucherChiTiet() {
        VouCherResponse vc = new VouCherResponse();
        vc.setId(Integer.parseInt(txtID.getText()));
        vc.setMaVouCher(txtMaVouCher.getText());
        vc.setTenVouCher(txtTenVouCher.getText());
        vc.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        vc.setPhanTramGiam(Integer.parseInt(txtPhanTramGiam.getText()));
        vc.setSoTienDuocGiamToiDa(Float.parseFloat(txtSoTienDuocGiamToiDa.getText()));
        vc.setNgayBatDau(txtNgayBatDau.getText());
        vc.setNgayKetThuc(txtNgayKetThuc.getText());
        vc.setGiaTriToiThieuApDung(Float.parseFloat(txtGiaTriToiThieuApDung.getText()));
        vc.setNgaySua(txtNgaySua.getText());
        vc.setNguoiTao(txtNguoiTao.getText());
        vc.setNgayTao(txtNgayTao.getText());
        vc.setNguoiSua(txtNguoiSua.getText());
        vc.setMoTa(txtMoTa.getText());
        if (rdoHD.isSelected()) {
            vc.setHoatDong(1);
        } else {
            vc.setHoatDong(0);
        }
        LocalDate startDate = LocalDate.parse(vc.getNgayBatDau());
        LocalDate endDate = LocalDate.parse(vc.getNgayKetThuc());
        LocalDate today = LocalDate.now();
        if (startDate.isBefore(today)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải sau hôm nay", "Lỗi", 0);
            return null;
        }
        
        if (vc.getPhanTramGiam() >= 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm phải từ 0-100", "Lỗi", 0);
                return null;
            }

        if (startDate.isBefore(endDate)) {

        } else if (startDate.isEqual(endDate)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải khác ngày kết thúc", "Lỗi", 0);
            return null;
        } else {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc", "Lỗi", 0);
            return null;
        }
        return vc;
    }

    private boolean validateFields() {
        if (txtMaVouCher.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Voucher không được để trống.");
            return false;
        }
        if (txtTenVouCher.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên Voucher không được để trống.");
            return false;
        }
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Lượng không được để trống.");
            return false;
        }
        if (txtPhanTramGiam.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phần Trăm Giảm không được để trống.");
            return false;
        }
        if (txtSoTienDuocGiamToiDa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số Tiền Được Giảm Tối Đa không được để trống.");
            return false;
        }
        if (txtGiaTriToiThieuApDung.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá Trị Tối Thiểu Áp Dụng không được để trống.");
            return false;
        }
        if (txtNgayBatDau.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày Bắt Đầu không được để trống.");
            return false;
        }
        if (txtNgayKetThuc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày Kết Thúc không được để trống.");
            return false;
        }

        try {
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số Lượng phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số Lượng phải là số hợp lệ.");
            return false;
        }

        try {
            double phanTramGiam = Double.parseDouble(txtPhanTramGiam.getText().trim());
            if (phanTramGiam <= 0) {
                JOptionPane.showMessageDialog(this, "Phần Trăm Giảm phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần Trăm Giảm phải là số hợp lệ.");
            return false;
        }

        try {
            double soTienDuocGiamToiDa = Double.parseDouble(txtSoTienDuocGiamToiDa.getText().trim());
            if (soTienDuocGiamToiDa <= 0) {
                JOptionPane.showMessageDialog(this, "Số Tiền Được Giảm Tối Đa phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số Tiền Được Giảm Tối Đa phải là số hợp lệ.");
            return false;
        }

        try {
            double giaTriToiThieuApDung = Double.parseDouble(txtGiaTriToiThieuApDung.getText().trim());
            if (giaTriToiThieuApDung <= 0) {
                JOptionPane.showMessageDialog(this, "Giá Trị Tối Thiểu Áp Dụng phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá Trị Tối Thiểu Áp Dụng phải là số hợp lệ.");
            return false;
        }

        return true;
    }

    private void Clear() {
        txtMaVouCher.setText("");
        txtTenVouCher.setText("");
        txtSoLuong.setText("");
        txtPhanTramGiam.setText("");
        txtSoTienDuocGiamToiDa.setText("");
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtGiaTriToiThieuApDung.setText("");
        txtNgaySua.setText("");
        txtNguoiTao.setText("");
        txtNgayTao.setText("");
        txtNguoiSua.setText("");
        txtMoTa.setText("");
        buttonGroup1.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtPhanTramGiam = new com.phone.swing.TextField();
        txtTenVouCher = new com.phone.swing.TextField();
        txtSoLuong = new com.phone.swing.TextField();
        txtMaVouCher = new com.phone.swing.TextField();
        txtID = new com.phone.swing.TextField();
        txtSoTienDuocGiamToiDa = new com.phone.swing.TextField();
        txtNgayBatDau = new com.phone.swing.TextField();
        txtGiaTriToiThieuApDung = new com.phone.swing.TextField();
        txtNgaySua = new com.phone.swing.TextField();
        txtNguoiTao = new com.phone.swing.TextField();
        txtNgayKetThuc = new com.phone.swing.TextField();
        txtNgayTao = new com.phone.swing.TextField();
        txtNguoiSua = new com.phone.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        rdoHD = new javax.swing.JRadioButton();
        rdoKHD = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnUpdate = new com.phone.swing.ButtonPrimary();
        btnDelete = new com.phone.swing.ButtonPrimary();
        btnClear = new com.phone.swing.ButtonPrimary();
        btnClear1 = new com.phone.swing.ButtonPrimary();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Chi Tiết VouCher");

        txtPhanTramGiam.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtPhanTramGiam.setLabelText("Phần Trăm Giảm");

        txtTenVouCher.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtTenVouCher.setLabelText("Tên VouCher");

        txtSoLuong.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtSoLuong.setLabelText("Số Lượng");

        txtMaVouCher.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtMaVouCher.setLabelText("Mã VouCher");

        txtID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtID.setLabelText("ID");

        txtSoTienDuocGiamToiDa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtSoTienDuocGiamToiDa.setLabelText("Số Tiền Được Giảm Tối Đa");

        txtNgayBatDau.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNgayBatDau.setLabelText("Ngày Bắt Đầu");

        txtGiaTriToiThieuApDung.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtGiaTriToiThieuApDung.setLabelText("Giá Trị Tối Thiểu Áp Dụng");

        txtNgaySua.setEditable(false);
        txtNgaySua.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNgaySua.setLabelText("Ngày Sửa");

        txtNguoiTao.setEditable(false);
        txtNguoiTao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNguoiTao.setLabelText("Người Tạo");

        txtNgayKetThuc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNgayKetThuc.setLabelText("Ngày Kết Thúc");

        txtNgayTao.setEditable(false);
        txtNgayTao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNgayTao.setLabelText("Ngày Tạo");

        txtNguoiSua.setEditable(false);
        txtNguoiSua.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNguoiSua.setLabelText("Người Sửa");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Hoạt Động");

        buttonGroup1.add(rdoHD);
        rdoHD.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdoHD.setText("Hoạt Động");

        buttonGroup1.add(rdoKHD);
        rdoKHD.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdoKHD.setText("Không Hoạt Động");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnClear1.setText("back");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addComponent(txtMaVouCher, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTenVouCher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtGiaTriToiThieuApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtSoTienDuocGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoHD)
                                        .addGap(31, 31, 31)
                                        .addComponent(rdoKHD))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtNgaySua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNguoiTao, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane1))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(323, 323, 323))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaVouCher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenVouCher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaTriToiThieuApDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoTienDuocGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguoiSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoHD)
                            .addComponent(rdoKHD)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!validateFields()) {
            return;
        }
        try {
            VouCherResponse vc = getVoucherChiTiet();
            vouCherRepository.updateVouCher(vc);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
            parentForm.init();
            
            
            this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID không được để trống.");
            return;
        }
        try {
            int id = Integer.parseInt(txtID.getText());
            vouCherRepository.deleteVouCher(id);
            JOptionPane.showMessageDialog(this, "Xóa thành công.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.Clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        FormVoucher formVoucher = new FormVoucher();
        formVoucher.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClear1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormChiTietVouCher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVouCher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVouCher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVouCher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.phone.swing.ButtonPrimary btnClear;
    private com.phone.swing.ButtonPrimary btnClear1;
    private com.phone.swing.ButtonPrimary btnDelete;
    private com.phone.swing.ButtonPrimary btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoKHD;
    private com.phone.swing.TextField txtGiaTriToiThieuApDung;
    private com.phone.swing.TextField txtID;
    private com.phone.swing.TextField txtMaVouCher;
    private javax.swing.JTextArea txtMoTa;
    private com.phone.swing.TextField txtNgayBatDau;
    private com.phone.swing.TextField txtNgayKetThuc;
    private com.phone.swing.TextField txtNgaySua;
    private com.phone.swing.TextField txtNgayTao;
    private com.phone.swing.TextField txtNguoiSua;
    private com.phone.swing.TextField txtNguoiTao;
    private com.phone.swing.TextField txtPhanTramGiam;
    private com.phone.swing.TextField txtSoLuong;
    private com.phone.swing.TextField txtSoTienDuocGiamToiDa;
    private com.phone.swing.TextField txtTenVouCher;
    // End of variables declaration//GEN-END:variables

}
