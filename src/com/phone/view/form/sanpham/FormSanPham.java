/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.phone.view.form.sanpham;

import com.core.model.request.TimKiemRequest;
import com.core.model.response.SanPhamResponse;
import com.core.model.response.TaiKhoanResponse;
import com.core.model.response.ThuocTinhResponse;
import com.core.repository.SanPhamRepository;
import com.core.service.SanPhamService;
import com.core.service.impl.ThuocTinhServiceImpl;
import com.phone.custom.component.Notification;
import com.phone.view.main.Main;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hatun
 */
public class FormSanPham extends javax.swing.JPanel {

    private ThuocTinhServiceImpl service = new ThuocTinhServiceImpl();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private SanPhamService sanPhamService = new SanPhamService();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private List<SanPhamResponse> list = new ArrayList<>();
    private List<String> listGia = new ArrayList<>();
    private Frame parentForm;
    private Notification notiSuccess;
    private Notification notiWarring;
    private TaiKhoanResponse account;

    public void setAccount(TaiKhoanResponse tk) {
        this.account = tk;
    }

    /**
     * Creates new form FormSanPham
     */
    public FormSanPham() {
        initComponents();
        init();
        tbList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int idCTSP = list.get(tbList.getSelectedRow()).getId();
                    Main mainFrame = (Main) SwingUtilities.getWindowAncestor(FormSanPham.this);
                    mainFrame.viewDetailProduct(idCTSP);
                }
            }
        });
    }

    public void init() {
        fillDataToCombobox(cbHang, service.findAllHD("Hang"));
        fillDataToCombobox(cbRam, service.findAllHD("Ram"));
        fillDataToCombobox(cbBoNho, service.findAllHD("BoNho"));
        fillDataToCombobox(cbMauSac, service.findAllHD("MauSac"));
        fillComboGia(listGia);
        resetComboBox(cbRam);
        resetComboBox(cbBoNho);
        resetComboBox(cbMauSac);
        resetComboBox(cbHang);
        resetComboBox(cbGia);
        fillTabe();
    }

    public void fillTabe() {
        defaultTableModel.setRowCount(0);
        defaultTableModel = (DefaultTableModel) tbList.getModel();
        try {
            list = sanPhamService.getDataToTable(readForm());
        } catch (Exception e) {

        }
        for (SanPhamResponse sanPhamResponse : list) {
            defaultTableModel.addRow(sanPhamResponse.toRowTable());

        }
    }

    private void fillComboGia(List<String> list) {
        cbGia.removeAllItems();
        listGia.add("");
        listGia.add("0 - 5tr");
        listGia.add("5 - 10tr");
        listGia.add("10 - 15tr");
        listGia.add("15 - 20tr");
        listGia.add("20 - 25tr");
        listGia.add("trên 25tr");
        for (String string : list) {
            cbGia.addItem(string);
        }

    }

    private void fillDataToCombobox(JComboBox<String> combo, List<ThuocTinhResponse> listData) {
        combo.removeAllItems();
        combo.addItem("");
        for (ThuocTinhResponse thuocTinhResponse : listData) {
            combo.addItem(thuocTinhResponse.getName());
        }
    }

    public void resetComboBox(JComboBox<String> combo) {
        combo.setSelectedIndex(0);
    }

    private Integer readCombobox(JComboBox<String> combo, List<ThuocTinhResponse> listData) {
        Integer id = combo.getSelectedIndex();
        if (id == 0) {
            id = null;
        } else {
            id = listData.get(id - 1).getId();
        }
        return id;
    }

    private TimKiemRequest readForm() {
        TimKiemRequest data = new TimKiemRequest();
        String timKiem = txtTimKiem.getText();
        if (timKiem != null && !timKiem.isEmpty()) {
            data.setTimKiem(timKiem);
        } else {
            data.setTimKiem(null);
        }
        //Gia
        
        Integer idGia = cbGia.getSelectedIndex();
        switch (idGia) {
            case 0:
                data.setGiaBatDau((float) 1000);
                data.setGiaKetThuc((float) 100000000);
                break;
            case 1:
                data.setGiaBatDau((float) 1000);
                data.setGiaKetThuc((float) 5000000);
                break;
            case 2:
                data.setGiaBatDau((float) 5000000);
                data.setGiaKetThuc((float) 10000000);
                break;
            case 3:
                data.setGiaBatDau((float) 10000000);
                data.setGiaKetThuc((float) 15000000);
                break;
            case 4:
                data.setGiaBatDau((float) 15000000);
                data.setGiaKetThuc((float) 20000000);
                break;
            case 5:
                data.setGiaBatDau((float) 20000000);
                data.setGiaKetThuc((float) 25000000);
                break;
            case 6:
                data.setGiaBatDau((float) 25000000);
                data.setGiaKetThuc((float) 100000000);
                break;
            default:
                data.setGiaBatDau((float) 1000);
                data.setGiaKetThuc((float) 100000000);
                break;
        }

        data.setIdHang(readCombobox(cbHang, service.findAllHD("Hang")));
        data.setIdBoNho(readCombobox(cbBoNho, service.findAllHD("BoNho")));
        data.setIdRam(readCombobox(cbRam, service.findAllHD("Ram")));
        data.setIdMauSac(readCombobox(cbMauSac, service.findAllHD("MauSac")));

        return data;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbList = new javax.swing.JTable();
        btnThemSanPham = new com.phone.swing.ButtonPrimary();
        cbGia = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbHang = new javax.swing.JComboBox<>();
        cbRam = new javax.swing.JComboBox<>();
        cbBoNho = new javax.swing.JComboBox<>();
        cbMauSac = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(147, 158, 168));
        setLayout(null);

        tbList.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm", "Hãng", "Ram", "Bộ nhớ", "Màu sắc", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbList.setOpaque(false);
        tbList.setRowHeight(50);
        jScrollPane1.setViewportView(tbList);
        if (tbList.getColumnModel().getColumnCount() > 0) {
            tbList.getColumnModel().getColumn(0).setMinWidth(50);
            tbList.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbList.getColumnModel().getColumn(0).setMaxWidth(50);
            tbList.getColumnModel().getColumn(2).setMinWidth(120);
            tbList.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbList.getColumnModel().getColumn(2).setMaxWidth(120);
            tbList.getColumnModel().getColumn(3).setMinWidth(120);
            tbList.getColumnModel().getColumn(3).setPreferredWidth(120);
            tbList.getColumnModel().getColumn(3).setMaxWidth(120);
            tbList.getColumnModel().getColumn(4).setMinWidth(120);
            tbList.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbList.getColumnModel().getColumn(4).setMaxWidth(120);
            tbList.getColumnModel().getColumn(5).setMinWidth(120);
            tbList.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbList.getColumnModel().getColumn(5).setMaxWidth(120);
            tbList.getColumnModel().getColumn(6).setMinWidth(80);
            tbList.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbList.getColumnModel().getColumn(6).setMaxWidth(80);
            tbList.getColumnModel().getColumn(7).setMinWidth(200);
            tbList.getColumnModel().getColumn(7).setPreferredWidth(200);
            tbList.getColumnModel().getColumn(7).setMaxWidth(200);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(6, 165, 1458, 647);

        btnThemSanPham.setText("Thêm sản phẩm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });
        add(btnThemSanPham);
        btnThemSanPham.setBounds(1254, 25, 210, 54);

        cbGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbGiaItemStateChanged(evt);
            }
        });
        add(cbGia);
        cbGia.setBounds(30, 90, 200, 40);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Giá:");
        add(jLabel1);
        jLabel1.setBounds(30, 70, 46, 17);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Hãng:");
        add(jLabel2);
        jLabel2.setBounds(260, 70, 50, 17);

        cbHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbHangItemStateChanged(evt);
            }
        });
        add(cbHang);
        cbHang.setBounds(250, 90, 200, 40);

        cbRam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbRam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbRamItemStateChanged(evt);
            }
        });
        add(cbRam);
        cbRam.setBounds(470, 90, 200, 40);

        cbBoNho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbBoNho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbBoNhoItemStateChanged(evt);
            }
        });
        add(cbBoNho);
        cbBoNho.setBounds(690, 90, 200, 40);

        cbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbMauSac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMauSacItemStateChanged(evt);
            }
        });
        add(cbMauSac);
        cbMauSac.setBounds(910, 90, 200, 40);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ram:");
        add(jLabel3);
        jLabel3.setBounds(480, 70, 40, 17);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Bộ nhớ:");
        add(jLabel4);
        jLabel4.setBounds(700, 70, 70, 17);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Màu sắc:");
        add(jLabel5);
        jLabel5.setBounds(920, 70, 90, 17);

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        add(txtTimKiem);
        txtTimKiem.setBounds(110, 10, 190, 40);

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel6.setText("Tìm kiếm:");
        add(jLabel6);
        jLabel6.setBounds(30, 20, 70, 14);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // TODO add your handling code here:
        FormThemSanPham formThemSanPham = new FormThemSanPham(this);
        formThemSanPham.setAccount(account);
        formThemSanPham.setVisible(true);
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void cbGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbGiaItemStateChanged
        // TODO add your handling code here:
        fillTabe();
    }//GEN-LAST:event_cbGiaItemStateChanged

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        fillTabe();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cbHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbHangItemStateChanged
        // TODO add your handling code here:
        fillTabe();
    }//GEN-LAST:event_cbHangItemStateChanged

    private void cbRamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbRamItemStateChanged
        // TODO add your handling code here:
        fillTabe();
    }//GEN-LAST:event_cbRamItemStateChanged

    private void cbBoNhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBoNhoItemStateChanged
        // TODO add your handling code here:
        fillTabe();        
    }//GEN-LAST:event_cbBoNhoItemStateChanged

    private void cbMauSacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMauSacItemStateChanged
        // TODO add your handling code here:
        fillTabe();
    }//GEN-LAST:event_cbMauSacItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.phone.swing.ButtonPrimary btnThemSanPham;
    private javax.swing.JComboBox<String> cbBoNho;
    private javax.swing.JComboBox<String> cbGia;
    private javax.swing.JComboBox<String> cbHang;
    private javax.swing.JComboBox<String> cbMauSac;
    private javax.swing.JComboBox<String> cbRam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbList;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
