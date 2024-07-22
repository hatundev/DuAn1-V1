package com.phone.view.form.banhang;

import com.core.entity.HoaDon;
import com.core.entity.KetQua;
import com.core.entity.KhachHang;
import com.core.model.request.HoaDonRequest;
import com.core.model.request.VoucherBanHangResponse;
import com.core.model.response.ImeiResponse;
import com.core.model.response.TaiKhoanResponse;
import com.core.service.ImeiService;
import com.core.service.KhachHangService;
import com.core.service.VoucherService;
import com.phone.custom.component.Notification;
import com.phone.view.main.Main;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class FormBanHang extends javax.swing.JPanel {

    private VoucherService voucherService = new VoucherService();
    private KhachHangService khachHangService = new KhachHangService();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private List<ImeiResponse> listImei = new ArrayList<>();
    private List<VoucherBanHangResponse> listVoucher = new ArrayList<>();
    private Notification notiSuccess;
    private KhachHang kh;
    private Notification notiWarringl;
    private ImeiService imeiService = new ImeiService();
    private float tongTien = 0;
    private float giamGia = 0;
    private float tongTienSauGiam = 0;
    private float tienKhachDua = 0;
    private float tienChuyenKhoan = 0;
    private float tienDaThanhToan = 0;
    private float tienTraLai = 0;
    private TaiKhoanResponse account;
    
    public FormBanHang() {
        initComponents();
        setOpaque(false);
        defaultTableModel = (DefaultTableModel) tbListSP.getModel();
        init();
    }
    
    public void init() {
        reset();
    }
    
    private void reset() {
        txtChuyenKhoan.setText("");
        txtTienMat.setText("");
        lbTongTien.setText("0d");
        lbGiamGia.setText("0d");
        lbTienCuoiCung.setText("0d");
        lbDaThanhToan.setText("0d");
        lbTienThua.setText("0d");
    }
    
    public void setAccount(TaiKhoanResponse tk) {
        this.account = tk;
    }

    public void fillComboBoxByTotal(Float total) {
        cbVoucher.removeAllItems();
        listVoucher = voucherService.getCombo(total);
        for (VoucherBanHangResponse v : listVoucher) {
            cbVoucher.addItem(v.getTenVoucher());
        }
    }

    private void showWarring(String message) {
        Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
        mainFrame.showNotiWarring(message);
    }

    private void showSuccess(String message) {
        Main mainFrame = (Main) SwingUtilities.getWindowAncestor(this);
        mainFrame.showNotiSuccess(message);
    }

    private void fillTable() {
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < listImei.size(); i++) {
            defaultTableModel.addRow(new Object[]{
                i + 1, listImei.get(i).getTen(), listImei.get(i).getImei(), listImei.get(i).getGia()
            });
        }
    }

    private void loadData() {
        tongTien = 0;
        giamGia = 0;
        tongTienSauGiam = 0;
        for (ImeiResponse imeiResponse : listImei) {
            tongTien += imeiResponse.getGia();
        }
        lbTongTien.setText(String.valueOf(tongTien));
//        fillComboBoxByTotal(tongTien);
        tongTienSauGiam = tongTien - giamGia;
        lbTienCuoiCung.setText(String.valueOf(tongTienSauGiam));
    }

    private void loadMoney() {
        tienDaThanhToan = (tienChuyenKhoan + tienKhachDua);
        lbDaThanhToan.setText(String.valueOf(tienDaThanhToan));
        if (tienDaThanhToan > tongTienSauGiam) {
            tienTraLai = tienDaThanhToan - tongTienSauGiam;
            lbTienThua.setText(String.valueOf(tienTraLai));
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnDonMoi = new com.phone.swing.ButtonWarring();
        txtTimKiem = new com.phone.swing.TextField2();
        jLabel1 = new javax.swing.JLabel();
        btnThemSanPham = new com.phone.swing.ButtonPrimary();
        btnXoa = new com.phone.swing.ButtonDanger();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListSP = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbVoucher = new com.phone.swing.Combobox();
        txtChuyenKhoan = new com.phone.swing.TextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTienMat = new com.phone.swing.TextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        lbGiamGia = new javax.swing.JLabel();
        lbTienCuoiCung = new javax.swing.JLabel();
        lbDaThanhToan = new javax.swing.JLabel();
        lbTienThua = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        btnQR = new com.phone.swing.ButtonDanger();
        btnInHD = new com.phone.swing.ButtonPrimary();
        btnLuu = new com.phone.swing.ButtonPrimary();
        jPanel3 = new javax.swing.JPanel();
        jLabel146 = new javax.swing.JLabel();
        txtSDT = new com.phone.swing.TextField2();
        jLabel147 = new javax.swing.JLabel();
        txtTenKhachHang = new com.phone.swing.TextField2();
        jLabel148 = new javax.swing.JLabel();
        btnThemNhanhKhachHang = new com.phone.swing.ButtonPrimary();
        jLabel149 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        btnDonMoi.setText("Đơn mới");
        btnDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonMoiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel1.setText("Sản phẩm");

        btnThemSanPham.setText("Thêm sản phẩm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tbListSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm chi tiết", "Imei", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbListSP.setRowHeight(60);
        jScrollPane2.setViewportView(tbListSP);
        if (tbListSP.getColumnModel().getColumnCount() > 0) {
            tbListSP.getColumnModel().getColumn(0).setMinWidth(50);
            tbListSP.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbListSP.getColumnModel().getColumn(0).setMaxWidth(50);
            tbListSP.getColumnModel().getColumn(1).setResizable(false);
            tbListSP.getColumnModel().getColumn(2).setResizable(false);
            tbListSP.getColumnModel().getColumn(3).setMinWidth(150);
            tbListSP.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 102));
        jPanel2.setOpaque(false);

        jLabel2.setText("Phiếu giảm giá");

        cbVoucher.setLabeText("");
        cbVoucher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVoucherItemStateChanged(evt);
            }
        });

        txtChuyenKhoan.setLabelText("");
        txtChuyenKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChuyenKhoanKeyReleased(evt);
            }
        });

        jLabel3.setText("Chuyển khoản:");

        jLabel4.setText("Tiền khách đưa:");

        txtTienMat.setLabelText("");
        txtTienMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienMatKeyReleased(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Tổng tiền");

        jLabel134.setText("Giảm giá");

        jLabel136.setText("Sau giảm giá");

        jLabel137.setText("Đã thanh toán");

        jLabel139.setText("Tra lai khach");

        lbTongTien.setText("jLabel140");

        lbGiamGia.setText("jLabel141");

        lbTienCuoiCung.setText("jLabel142");

        lbDaThanhToan.setText("jLabel144");

        lbTienThua.setText("jLabel145");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(194, 194, 194)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTienThua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(lbGiamGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDaThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTienCuoiCung, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbTongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel134)
                    .addComponent(lbGiamGia))
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(lbTienCuoiCung))
                .addGap(54, 54, 54)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137)
                    .addComponent(lbDaThanhToan))
                .addGap(48, 48, 48)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel139)
                    .addComponent(lbTienThua))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel133.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel133.setText("Thanh toán");

        btnQR.setText("QR chuyển khoản");
        btnQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRActionPerformed(evt);
            }
        });

        btnInHD.setText("In hóa đơn");
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu hóa đơn");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTienMat, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jLabel133)
                    .addContainerGap(367, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(48, 48, 48)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jLabel133)
                    .addContainerGap(765, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        jLabel146.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel146.setText("Khách hàng");

        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSDTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        jLabel147.setText("Số điện thoại");

        jLabel148.setText("Tên khách hàng");

        btnThemNhanhKhachHang.setText("Thêm khách hàng");

        jLabel149.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel149.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane3.setViewportView(txtGhiChu);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel149))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel146)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                                        .addComponent(btnThemNhanhKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(64, 64, 64))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel148)
                                .addGap(345, 345, 345))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel147)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel146)
                    .addComponent(btnThemNhanhKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel149))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel147)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel148)
                        .addGap(8, 8, 8)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // TODO add your handling code here:
        if (txtTimKiem.getText().equals("")) {
            showWarring("Chua nhap Imei");
        } else {
            ImeiResponse imei = imeiService.findByImei(txtTimKiem.getText());
            if (imei == null) {
                System.out.println(txtTimKiem.getText());
                showWarring("Imei Khong ton tai");
            } else {
                listImei.add(imei);
                fillTable();
                fillComboBoxByTotal(tongTien);
                showSuccess("Them san pham thanh cong");
                //KetQua kq = imeiService.delete(txtTimKiem.getText());
                txtTimKiem.setText("");
                loadData();
            }
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQRActionPerformed

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInHDActionPerformed

    private void btnDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDonMoiActionPerformed

    private void cbVoucherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVoucherItemStateChanged
        // TODO add your handling code here:
        VoucherBanHangResponse vou = listVoucher.get(cbVoucher.getSelectedIndex());
        int phanTram = vou.getPhanTramGiam();
        float soTienDuocGiamToiDa = vou.getSoTienDuocGiamToiDa();
        giamGia = tongTien * (100 - phanTram) / 100;
        if (giamGia > soTienDuocGiamToiDa) {
            giamGia = soTienDuocGiamToiDa;
        } else {
            giamGia = (float) Math.ceil(giamGia);
        }
        lbGiamGia.setText(String.valueOf(giamGia));
        
    }//GEN-LAST:event_cbVoucherItemStateChanged

    private void txtChuyenKhoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChuyenKhoanKeyReleased
        // TODO add your handling code here:
        tienChuyenKhoan = Float.valueOf(txtChuyenKhoan.getText());
        loadMoney();
    }//GEN-LAST:event_txtChuyenKhoanKeyReleased

    private void txtTienMatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMatKeyReleased
        // TODO add your handling code here:
        tienKhachDua = Float.valueOf(txtTienMat.getText());
        loadMoney();
    }//GEN-LAST:event_txtTienMatKeyReleased

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        // TODO add your handling code here:
        kh = khachHangService.findBySDT(txtSDT.getText());
        if (kh != null) {
            txtTenKhachHang.setText(kh.getTenKhachHang());
        } else {
            txtTenKhachHang.setText("");
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int index = tbListSP.getSelectedRow();
        if(index < 0){
            showWarring("Chua chon san pham de xoa");
        } else {
            listImei.remove(index);
            if(listImei.size() == 0){
                fillTable();
                cbVoucher.removeAllItems();
                reset();
            } else{
                fillTable();
                loadData();
                loadMoney();
            }
            
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            txtSDT.setText(kh.getSoDienThoai());
        }
    }//GEN-LAST:event_txtSDTKeyPressed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        //Kh
        Integer idKhachHang= null;
        if(kh != null){
            KetQua kq = khachHangService.create(txtSDT.getText(), txtTenKhachHang.getText());
            idKhachHang = khachHangService.findBySDT(txtSDT.getText()).getId();
        }
        if(txtSDT.getText().equals("") && txtTenKhachHang.getText().equals("")){
            idKhachHang = null;
        }
        
        //hoadon
        HoaDonRequest hd = HoaDonRequest.builder()
                .idKhachHang(idKhachHang)
                
                .build();
        //hoaDon.
        
        System.out.println(account.toString());
    }//GEN-LAST:event_btnLuuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.phone.swing.ButtonWarring btnDonMoi;
    private com.phone.swing.ButtonPrimary btnInHD;
    private com.phone.swing.ButtonPrimary btnLuu;
    private com.phone.swing.ButtonDanger btnQR;
    private com.phone.swing.ButtonPrimary btnThemNhanhKhachHang;
    private com.phone.swing.ButtonPrimary btnThemSanPham;
    private com.phone.swing.ButtonDanger btnXoa;
    private com.phone.swing.Combobox cbVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbDaThanhToan;
    private javax.swing.JLabel lbGiamGia;
    private javax.swing.JLabel lbTienCuoiCung;
    private javax.swing.JLabel lbTienThua;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JTable tbListSP;
    private com.phone.swing.TextField txtChuyenKhoan;
    private javax.swing.JTextArea txtGhiChu;
    private com.phone.swing.TextField2 txtSDT;
    private com.phone.swing.TextField2 txtTenKhachHang;
    private com.phone.swing.TextField txtTienMat;
    private com.phone.swing.TextField2 txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
