/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.phone.view.form.banhang;

import com.core.entity.KetQua;
import com.core.entity.KhachHang;
import com.core.model.request.HoaDonChiTietRequest;
import com.core.model.request.VoucherBanHangResponse;
import com.core.model.response.ChiTietSanPhamResponse;
import com.core.model.response.HoaDonChiTietResponse;
import com.core.model.response.HoaDonResponse;
import com.core.model.response.TaiKhoanResponse;
import com.core.repository.ChiTietHinhThucThanhToanRepository;
import com.core.service.ChiTietSanPhamService;
import com.core.service.HoaDonChiTietService;
import com.core.service.HoaDonService;
import com.core.service.ImeiService;
import com.core.service.KhachHangService;
import com.core.service.SanPhamService;
import com.core.service.VoucherService;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.phone.custom.component.Notification;
import com.phone.view.main.Main;
import com.util.Util;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hatundev
 */
public class FormBanHang extends javax.swing.JPanel {

    private ChiTietHinhThucThanhToanRepository chiTietHinhThucThanhToanRepository = new ChiTietHinhThucThanhToanRepository();
    private TaiKhoanResponse account;
    private Notification notiSuccess;
    private Notification notiWarringl;
    private DefaultTableModel defaultTableModel;
    private Util util;
    private SanPhamService sanPhamService;
    private ChiTietSanPhamService chiTietSanPhamService;
    private HoaDonService hoaDonService;
    private ImeiService imeiService;
    private VoucherService voucherService;
    private HoaDonChiTietService hoaDonChiTietService;
    private KhachHang kh;
    private KhachHangService khachHangService;
    private List<ChiTietSanPhamResponse> listSanPham = new ArrayList<>();
    private List<HoaDonResponse> listHoaDon = new ArrayList<>();
    private List<HoaDonChiTietResponse> listGioHang = new ArrayList<>();
    private List<VoucherBanHangResponse> listVoucher = new ArrayList<>();
    private Integer idHD;
    private int idHDCT;
    private int idCTSP;
    private float tongTien = 0;
    private float giamGia = 0;
    private float tongTienSauGiamGia = 0;
    private float tienChuyenKhoan = 0;
    private float tienMat = 0;
    private float daThanhToan = 0;
    private float tienThua = 0;

    /**
     * Creates new form FormBanHangHatun
     */
    public FormBanHang() {
        initComponents();
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatMacLightLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        reset();
        init();
    }

    private void init() {
        reset();
        util = new Util();
        sanPhamService = new SanPhamService();
        chiTietSanPhamService = new ChiTietSanPhamService();
        hoaDonService = new HoaDonService();
        hoaDonChiTietService = new HoaDonChiTietService();
        imeiService = new ImeiService();
        khachHangService = new KhachHangService();
        voucherService = new VoucherService();
        defaultTableModel = new DefaultTableModel();
        fillTableSanPham();
        fillTableHoaDon();
        resetTableGioHang();
        tbSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    idCTSP = listSanPham.get(tbSanPham.getSelectedRow()).getIdChiTietSanPham();

                    float giaBan = listSanPham.get(tbSanPham.getSelectedRow()).getGiaBan();

                    String imei = JOptionPane.showInputDialog(null, "Nhập mã imei!",
                            "Thêm giỏ hàng", JOptionPane.INFORMATION_MESSAGE);
                    if (!imei.isEmpty()) {
                        KetQua kq = imeiService.checkImei(imei, idCTSP);
                        if (kq.getIdKetQua() == 0) {
                            showWarring("Imei không tồn tại");
                        } else {
                            HoaDonChiTietRequest request = HoaDonChiTietRequest.builder()
                                    .idChiTietSanPham(idCTSP)
                                    .idHoaDon(idHD)
                                    .giaBan(giaBan)
                                    .moTa(imei)
                                    .build();
                            KetQua kQua = hoaDonChiTietService.create(request);
                            if (kQua.getIdKetQua() == 1) {
                                if (imeiService.delete(imei).getIdKetQua() == 1) {
                                    showSuccess("Thêm vào giỏ hàng thành công");
                                    fillTableGioHang(idHD);

                                    fillTableGioHang(idHD);
                                    fillTableSanPham();
                                    float tongTien = 0;
                                    for (HoaDonChiTietResponse hoaDonChiTietResponse : listGioHang) {
                                        tongTien += hoaDonChiTietResponse.getGiaBan();
                                    }
                                    if (hoaDonService.updateMoney(idHD, tongTien).getIdKetQua() == 1) {
                                        fillTableHoaDon();
                                    } else {

                                    }
                                }
                            } else {
                                showWarring("Thêm vào giỏ hàng thất bại");
                            }
                        }
                    }
                }
            }
        });

        tbHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    idHD = listHoaDon.get(tbHoaDon.getSelectedRow()).getId();
                    lbHDCT.setText("Giỏ hàng của hóa đơn số: " + listHoaDon.get(tbHoaDon.getSelectedRow()).getStt());
                    showSuccess("Chọn hóa đơn thành công");
                    int idHoaDon = listHoaDon.get(tbHoaDon.getSelectedRow()).getId();
                    fillTableGioHang(idHoaDon);
                    txtTenKhachHang.setText(listHoaDon.get(tbHoaDon.getSelectedRow()).getTenKhachHang());
                    txtSDT.setText(listHoaDon.get(tbHoaDon.getSelectedRow()).getSdt());
                }
            }
        });
    }

    private void ressetGioHang() {
        lbHDCT.setText("Giỏ hàng");
        defaultTableModel = (DefaultTableModel) tbGioHang.getModel();
        defaultTableModel.setRowCount(0);
    }

//    private void fillData(int idHoaDon){
//        txtTenKhachHang.setText("");
//        txtSDT.setText("");
//    }
    private void reset() {
        txtSDT.setText("");
        txtTenKhachHang.setText("");
        cbVoucher.setSelectedIndex(-1);
        lbTongTien.setText("");
        lbGiamGia.setText("");
        lbCanThanhToan.setText("");
        txtChuyenKhoan.setText("");
        txtTienMat.setText("");
        txtDaThanhToan.setText("");
        txtTraLai.setText("");
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

    private void fillTableSanPham() {
        listSanPham = chiTietSanPhamService.findAll();
        defaultTableModel = (DefaultTableModel) tbSanPham.getModel();
        defaultTableModel.setRowCount(0);
        for (ChiTietSanPhamResponse chiTietSanPhamResponse : listSanPham) {
            defaultTableModel.addRow(new Object[]{
                chiTietSanPhamResponse.getStt(),
                chiTietSanPhamResponse.getTenSanPhamChiTiet(),
                chiTietSanPhamResponse.getSoLuong(),
                util.convertFloatToMoney(chiTietSanPhamResponse.getGiaBan())
            });
        }
    }

    private void fillTableHoaDon() {

        listHoaDon = hoaDonService.getAllHDCho();
        defaultTableModel = (DefaultTableModel) tbHoaDon.getModel();
        defaultTableModel.setRowCount(0);
        for (HoaDonResponse hoaDonResponse : listHoaDon) {
            defaultTableModel.addRow(new Object[]{
                hoaDonResponse.getStt(),
                hoaDonResponse.getId(),
                hoaDonResponse.getTenKhachHang(),
                util.convertFloatToMoney(hoaDonResponse.getGiaTien())
            });
        }
    }

    private void fillTableGioHang(int idHoaDon) {
        listGioHang = hoaDonChiTietService.getGioHang(idHoaDon);
        defaultTableModel = (DefaultTableModel) tbGioHang.getModel();
        defaultTableModel.setRowCount(0);
        if (listGioHang.isEmpty()) {
            defaultTableModel.addRow(new Object[]{0,
                "Trống",
                "Trống",
                util.convertFloatToMoney(0)
            });
        }
        tongTien = 0;
        for (HoaDonChiTietResponse hoaDonChiTietResponse : listGioHang) {
            defaultTableModel.addRow(new Object[]{
                hoaDonChiTietResponse.getStt(),
                hoaDonChiTietResponse.getTenChiTietSanPham(),
                hoaDonChiTietResponse.getMoTa(),
                util.convertFloatToMoney(hoaDonChiTietResponse.getGiaBan())
            });
            tongTien += hoaDonChiTietResponse.getGiaBan();
        }
        lbTongTien.setText(util.convertFloatToMoney(tongTien));
        fillComboBoxByTotal(tongTien);
    }

    public void resetTableGioHang() {
        defaultTableModel = (DefaultTableModel) tbGioHang.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.addRow(new Object[]{0,
            "Trống",
            "Trống",
            util.convertFloatToMoney(0)
        });
        lbHDCT.setText("Giỏ hàng");
    }

    public void fillComboBoxByTotal(Float total) {
        cbVoucher.removeAllItems();
        listVoucher = voucherService.getCombo(total);
        for (VoucherBanHangResponse v : listVoucher) {
            cbVoucher.addItem(v.getTenVoucher());
        }
    }

    private void loadMoney() {
        daThanhToan = tienChuyenKhoan + tienMat;
        txtDaThanhToan.setText(util.convertFloatToMoney(daThanhToan));
        if (daThanhToan > tongTienSauGiamGia) {
            tienThua = daThanhToan - tongTienSauGiamGia;
            txtTraLai.setText(util.convertFloatToMoney(tienThua));
        } else {
            txtTraLai.setText(util.convertFloatToMoney(0));
        }
    }

    private void inHD() {
        int selectedRow = idHD; // Lấy hóa đơn được chọn

        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }

        String directoryPath = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        j.setCurrentDirectory(new File("\\DuAn1-V1\\HoaDon"));

        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            directoryPath = j.getSelectedFile().getPath();
        } else {
        }

        String filePath = directoryPath + "/" + idHD + ".pdf";
        createPDF(filePath);
    }

    private void createPDF(String filePath) {
        Document document = new Document(PageSize.A6, 10, 10, 10, 10);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font defaultFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK);
            // Thêm tiêu đề hóa đơn
            Paragraph header = new Paragraph();
            header.add(new Paragraph("Group 5",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK)));
            header.add(new Paragraph("SD19307 - FPL Ha Noi", defaultFont));
            header.add(new Paragraph("Add: Kieu Mai - Phuc Dien - Tu Liem - Ha Noi", defaultFont));
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            // Dòng phân cách
            Paragraph separator = new Paragraph("--------------------***--------------------", defaultFont);
            separator.setAlignment(Element.ALIGN_CENTER);
            document.add(separator);

            // Thêm thông tin hóa đơn
            Paragraph title = new Paragraph("INVOICE",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Dòng phân cách
            document.add(separator);

            // Thông tin chi tiết hóa đơn
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String currentDateTime = dateFormat.format(new Date());
            Paragraph invoiceDetails = new Paragraph();
            invoiceDetails.add(new Paragraph("Ngay ban: " + currentDateTime, defaultFont));
            invoiceDetails.add(new Paragraph("HD: " + idHD, defaultFont));
            invoiceDetails.add(new Paragraph("NVBH: " + account.getUsername(), defaultFont));
            invoiceDetails.add(new Paragraph("KH: " + txtTenKhachHang.getText(), defaultFont));
            invoiceDetails.add(new Paragraph("SDT: " + txtSDT.getText(), defaultFont));
            document.add(invoiceDetails);
            document.add(separator);
            document.add(new Paragraph("", defaultFont));
            // Thêm bảng sản phẩm
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 2, 1});

            PdfPCell header1 = new PdfPCell(new Phrase("Product", defaultFont));
            header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(header1);

            PdfPCell header2 = new PdfPCell(new Phrase("Imei", defaultFont));
            header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(header2);

            PdfPCell header3 = new PdfPCell(new Phrase("Price", defaultFont));
            header3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(header3);
            // Thêm các dòng sản phẩm từ giỏ hàng
            listGioHang = hoaDonChiTietService.getGioHang(idHD);
            for (HoaDonChiTietResponse item : listGioHang) {
                table.addCell(new Phrase(item.getTenChiTietSanPham(), defaultFont));
                table.addCell(new Phrase(item.getMoTa(), defaultFont));
                table.addCell(new Phrase(util.convertFloatToMoney(item.getGiaBan()), defaultFont));
            }
            document.add(table);
            // Thêm thông tin tổng tiền
            document.add(new Paragraph("", defaultFont));
            document.add(new Paragraph("Tong tien:" + util.convertFloatToMoney(tongTien), defaultFont));
            document.add(new Paragraph("Giam gia:" + util.convertFloatToMoney(giamGia), defaultFont));
            document.add(new Paragraph("Can thanh toan:" + util.convertFloatToMoney(tongTienSauGiamGia), defaultFont));
            document.add(new Paragraph("Tien khach tra:" + util.convertFloatToMoney(daThanhToan), defaultFont));
            document.add(new Paragraph("Tien mat:" + util.convertFloatToMoney(tienMat), defaultFont));
            document.add(new Paragraph("Chuyen khoan:" + util.convertFloatToMoney(tienChuyenKhoan), defaultFont));
            document.add(new Paragraph("Tien thua:" + util.convertFloatToMoney(tienThua), defaultFont));
            document.add(separator);
            // Thêm lời cảm ơn
            Paragraph thankYou = new Paragraph("Cam on quy khach! Hen gap lai!", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK));
            thankYou.setAlignment(Element.ALIGN_CENTER);
            thankYou.setSpacingBefore(10);
            document.add(thankYou);

            document.close();
            showSuccess("Xuất hóa đơn thành công");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            showWarring("Xuất hóa đơn thất bại");
        }
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
        panelHoaDon = new javax.swing.JPanel();
        btnXoaHD = new javax.swing.JButton();
        btnThemHoaDon = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        panelHDCT = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        btnXoaGioHang = new javax.swing.JButton();
        lbHDCT = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelKhachhang = new javax.swing.JPanel();
        txtSDT = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbVoucher = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbGiamGia = new javax.swing.JLabel();
        lbCanThanhToan = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtDaThanhToan = new javax.swing.JTextField();
        txtChuyenKhoan = new javax.swing.JTextField();
        txtTienMat = new javax.swing.JTextField();
        txtTraLai = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnHoanThanh = new javax.swing.JButton();
        panelSanPham = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        setOpaque(false);
        setLayout(null);

        panelHoaDon.setBackground(new java.awt.Color(147, 158, 168));
        panelHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelHoaDon.setLayout(null);

        btnXoaHD.setBackground(new java.awt.Color(220, 53, 69));
        btnXoaHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaHD.setText("Xóa");
        btnXoaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDActionPerformed(evt);
            }
        });
        panelHoaDon.add(btnXoaHD);
        btnXoaHD.setBounds(570, 20, 72, 40);

        btnThemHoaDon.setBackground(new java.awt.Color(13, 110, 253));
        btnThemHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHoaDon.setText("Thêm hóa đơn");
        btnThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoaDonActionPerformed(evt);
            }
        });
        panelHoaDon.add(btnThemHoaDon);
        btnThemHoaDon.setBounds(390, 20, 160, 40);

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel4.setText("Hóa đơn chờ");
        panelHoaDon.add(jLabel4);
        jLabel4.setBounds(20, 30, 140, 22);

        tbHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Khách hàng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDon.setRowHeight(37);
        jScrollPane3.setViewportView(tbHoaDon);
        if (tbHoaDon.getColumnModel().getColumnCount() > 0) {
            tbHoaDon.getColumnModel().getColumn(0).setMinWidth(60);
            tbHoaDon.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbHoaDon.getColumnModel().getColumn(0).setMaxWidth(60);
            tbHoaDon.getColumnModel().getColumn(1).setMinWidth(150);
            tbHoaDon.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbHoaDon.getColumnModel().getColumn(1).setMaxWidth(150);
            tbHoaDon.getColumnModel().getColumn(3).setMinWidth(200);
            tbHoaDon.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbHoaDon.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        panelHoaDon.add(jScrollPane3);
        jScrollPane3.setBounds(10, 80, 650, 220);

        add(panelHoaDon);
        panelHoaDon.setBounds(0, 0, 670, 310);

        panelHDCT.setBackground(new java.awt.Color(147, 158, 168));
        panelHDCT.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelHDCT.setLayout(null);

        tbGioHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm chi tiết", "IMEI", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbGioHang);
        if (tbGioHang.getColumnModel().getColumnCount() > 0) {
            tbGioHang.getColumnModel().getColumn(0).setMinWidth(60);
            tbGioHang.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbGioHang.getColumnModel().getColumn(0).setMaxWidth(60);
            tbGioHang.getColumnModel().getColumn(2).setMinWidth(200);
            tbGioHang.getColumnModel().getColumn(2).setPreferredWidth(200);
            tbGioHang.getColumnModel().getColumn(2).setMaxWidth(200);
            tbGioHang.getColumnModel().getColumn(3).setMinWidth(150);
            tbGioHang.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbGioHang.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        panelHDCT.add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 770, 220);

        btnXoaGioHang.setBackground(new java.awt.Color(220, 53, 69));
        btnXoaGioHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaGioHang.setText("Xóa");
        btnXoaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGioHangActionPerformed(evt);
            }
        });
        panelHDCT.add(btnXoaGioHang);
        btnXoaGioHang.setBounds(700, 20, 72, 40);

        lbHDCT.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        lbHDCT.setText("Giỏ hàng");
        panelHDCT.add(lbHDCT);
        lbHDCT.setBounds(20, 30, 350, 22);

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel2.setText("Thông tin");
        panelHDCT.add(jLabel2);
        jLabel2.setBounds(20, 330, 140, 22);

        panelKhachhang.setBackground(new java.awt.Color(255, 255, 255));
        panelKhachhang.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách hàng"));
        panelKhachhang.setOpaque(false);

        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSDTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        jLabel6.setText("Họ tên");

        jLabel7.setText("Số điện thoại");

        javax.swing.GroupLayout panelKhachhangLayout = new javax.swing.GroupLayout(panelKhachhang);
        panelKhachhang.setLayout(panelKhachhangLayout);
        panelKhachhangLayout.setHorizontalGroup(
            panelKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhachhangLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKhachhangLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelKhachhangLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelKhachhangLayout.setVerticalGroup(
            panelKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhachhangLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKhachhangLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panelKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKhachhangLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6))
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelHDCT.add(panelKhachhang);
        panelKhachhang.setBounds(20, 380, 370, 190);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh toán"));
        jPanel6.setOpaque(false);

        jLabel5.setText("Voucher:");

        cbVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbVoucher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVoucherItemStateChanged(evt);
            }
        });

        jLabel8.setText("Tổng tiền:");

        lbTongTien.setText("jLabel9");

        jLabel10.setText("Giảm giá:");

        lbGiamGia.setText("jLabel11");

        lbCanThanhToan.setText("jLabel13");

        jLabel14.setText("------------------------------------------------------------------");

        jLabel15.setText("Cần thanh toán:");

        jLabel16.setText("Chuyển khoản:");

        jLabel17.setText("Tiền mặt:");

        txtChuyenKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChuyenKhoanKeyReleased(evt);
            }
        });

        txtTienMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienMatKeyReleased(evt);
            }
        });

        jLabel13.setText("Trả lại:");

        jLabel9.setText("Đã thanh toán:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel10)
                        .addGap(78, 78, 78)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCanThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel8)
                                .addGap(76, 76, 76)
                                .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel5)
                                .addGap(82, 82, 82)
                                .addComponent(cbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel15))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(79, 79, 79)
                                        .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(43, 43, 43)
                                        .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(96, 96, 96)
                                        .addComponent(txtTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCanThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel14)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelHDCT.add(jPanel6);
        jPanel6.setBounds(410, 380, 370, 420);

        btnLuu.setBackground(new java.awt.Color(56, 161, 105));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        panelHDCT.add(btnLuu);
        btnLuu.setBounds(680, 330, 72, 40);

        btnHoanThanh.setBackground(new java.awt.Color(13, 110, 253));
        btnHoanThanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHoanThanh.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanh.setText("Hoàn thành");
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });
        panelHDCT.add(btnHoanThanh);
        btnHoanThanh.setBounds(510, 330, 150, 40);

        add(panelHDCT);
        panelHDCT.setBounds(680, 0, 790, 820);

        panelSanPham.setBackground(new java.awt.Color(147, 158, 168));
        panelSanPham.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel3.setText("Danh sách sản phẩm");
        panelSanPham.add(jLabel3);
        jLabel3.setBounds(20, 30, 200, 22);

        tbSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm chi tiết", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSanPham.setRowHeight(48);
        jScrollPane2.setViewportView(tbSanPham);
        if (tbSanPham.getColumnModel().getColumnCount() > 0) {
            tbSanPham.getColumnModel().getColumn(0).setMinWidth(60);
            tbSanPham.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbSanPham.getColumnModel().getColumn(0).setMaxWidth(60);
            tbSanPham.getColumnModel().getColumn(2).setMinWidth(100);
            tbSanPham.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbSanPham.getColumnModel().getColumn(2).setMaxWidth(100);
            tbSanPham.getColumnModel().getColumn(3).setMinWidth(150);
            tbSanPham.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbSanPham.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        panelSanPham.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 650, 410);

        add(panelSanPham);
        panelSanPham.setBounds(0, 320, 670, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoaDonActionPerformed
        // TODO add your handling code here:
        KetQua kq = hoaDonService.create(account.getUsername());
        if (kq.getIdKetQua() == 0) {
            showWarring(kq.getThongBao());
        } else {
            showSuccess(kq.getThongBao());
            init();
//            fillTableHoaDon();
        }
    }//GEN-LAST:event_btnThemHoaDonActionPerformed

    private void btnXoaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDActionPerformed
        // TODO add your handling code here:
        try {
            if (idHD == null) {
                showWarring("Chưa chọn hóa đơn");
            } else {
                int result = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (listGioHang.isEmpty()) {
                        KetQua kq = hoaDonService.delete(idHD);
                        showSuccess("Xóa hóa đon chờ thành công");
                        fillTableHoaDon();
                        fillTableSanPham();
                        init();
                    } else {
                        for (HoaDonChiTietResponse hoaDonChiTietResponse : listGioHang) {
                            idHDCT = hoaDonChiTietResponse.getIdHoaDonChiTiet();
                            String imei = hoaDonChiTietResponse.getMoTa();
                            if (hoaDonChiTietService.delete(idHDCT).getIdKetQua() == 1) {
                                if (imeiService.unLock(imei).getIdKetQua() == 1) {
                                }
                            }
                        }
                        KetQua kq = hoaDonService.delete(idHD);
                        showSuccess("Xóa hóa đơn chờ thành công");
                        fillTableHoaDon();
                        fillTableSanPham();
                        init();
                    }
                }
            }
        } catch (HeadlessException headlessException) {
            showWarring("Lỗi");
        }
    }//GEN-LAST:event_btnXoaHDActionPerformed

    private void btnXoaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangActionPerformed
        // TODO add your handling code here:
        try {
            idHDCT = listGioHang.get(tbGioHang.getSelectedRow()).getIdHoaDonChiTiet();
            String imei = listGioHang.get(tbGioHang.getSelectedRow()).getMoTa();
            if (hoaDonChiTietService.delete(idHDCT).getIdKetQua() == 1) {
                showSuccess("Xóa thành công");
                fillTableGioHang(idHD);
                float tongTien = 0;
                for (HoaDonChiTietResponse hoaDonChiTietResponse : listGioHang) {
                    tongTien += hoaDonChiTietResponse.getGiaBan();
                }
                if (hoaDonService.updateMoney(idHD, tongTien).getIdKetQua() == 1) {
                    fillTableHoaDon();
                }
                if (imeiService.unLock(imei).getIdKetQua() == 1) {
                    fillTableSanPham();
                }
            }
        } catch (Exception e) {
            showWarring("Lỗi");
        }

    }//GEN-LAST:event_btnXoaGioHangActionPerformed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        // TODO add your handling code here:
        kh = khachHangService.findBySDT(txtSDT.getText());
        if (kh != null) {
            txtTenKhachHang.setText(kh.getTenKhachHang());
        } else {
            txtTenKhachHang.setText("");
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtSDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            txtSDT.setText(kh.getSoDienThoai());
        }
    }//GEN-LAST:event_txtSDTKeyPressed

    private void cbVoucherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVoucherItemStateChanged
        // TODO add your handling code here:
        try {
            VoucherBanHangResponse vou = listVoucher.get(cbVoucher.getSelectedIndex());
            int phanTram = vou.getPhanTramGiam();
            float soTienDuocGiamToiDa = vou.getSoTienDuocGiamToiDa();
            giamGia = tongTien * (100 - phanTram) / 100;
            if (giamGia > soTienDuocGiamToiDa) {
                giamGia = (float) Math.ceil(soTienDuocGiamToiDa);
            } else {
                giamGia = (float) Math.ceil(giamGia);
            }
            lbGiamGia.setText(util.convertFloatToMoney(giamGia));
            tongTienSauGiamGia = tongTien - giamGia;
            lbCanThanhToan.setText(util.convertFloatToMoney(tongTienSauGiamGia));
//            VoucherBanHangResponse vou = listVoucher.get(cbVoucher.getSelectedIndex());
//            int phanTram = vou.getPhanTramGiam();
//            BigDecimal soTienDuocGiamToiDa = new BigDecimal(vou.getSoTienDuocGiamToiDa());
//            //giamGia = tongTien * (100 - phanTram) / 100;
//            BigDecimal bGiamGia = new BigDecimal(tongTien * (100 - phanTram) / 100);
//            if (bGiamGia.compareTo(soTienDuocGiamToiDa) == 1) {
//                bGiamGia = soTienDuocGiamToiDa.setScale(0, RoundingMode.CEILING);
//            } else {
//                bGiamGia = bGiamGia.setScale(0, RoundingMode.CEILING);
//            }
//   
//            // lbGiamGia.setText(util.convertFloatToMoney(giamGia));
//            //tongTienSauGiamGia = tongTien - giamGia;
//            BigDecimal bTongTien = new BigDecimal(tongTien);
//            BigDecimal bTongTienSauGiamGia = new BigDecimal(tongTienSauGiamGia);
//            bTongTienSauGiamGia = bTongTien.subtract(bGiamGia);
//            lbCanThanhToan.setText(bTongTienSauGiamGia.toString());
            //lbCanThanhToan.setText(util.convertFloatToMoney(tongTienSauGiamGia));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbVoucherItemStateChanged

    private void txtChuyenKhoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChuyenKhoanKeyReleased
        // TODO add your handling code here:
        try {
            tienChuyenKhoan = Float.parseFloat(txtChuyenKhoan.getText());
            loadMoney();
        } catch (Exception e) {
            showWarring("Lỗi ký tự");
        }

    }//GEN-LAST:event_txtChuyenKhoanKeyReleased

    private void txtTienMatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMatKeyReleased
        // TODO add your handling code here:
        try {
            tienMat = Float.parseFloat(txtTienMat.getText());
            loadMoney();
        } catch (Exception e) {
            showWarring("Lỗi ký tự");
        }
    }//GEN-LAST:event_txtTienMatKeyReleased

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        Integer idKhachHang = null;
        if (txtSDT.getText().isEmpty() && txtTenKhachHang.getText().isEmpty()) {
            idKhachHang = null;
            KetQua kq = hoaDonService.updateKhachHang(idKhachHang, txtTenKhachHang.getText(), txtSDT.getText(), idHD);
            if (kq.getIdKetQua() == 1) {
                showSuccess("Lưu thành công");
                fillTableHoaDon();
            }
        } else {
            kh = khachHangService.findBySDT(txtSDT.getText());
            if (kh == null) {
                KetQua kq = khachHangService.create(txtSDT.getText(), txtTenKhachHang.getText());
                idKhachHang = khachHangService.findBySDT(txtSDT.getText()).getId();
                KetQua kq1 = hoaDonService.updateKhachHang(idKhachHang, txtTenKhachHang.getText(), txtSDT.getText(), idHD);
                if (kq.getIdKetQua() == 1) {
                    showSuccess("Lưu thành công");
                    fillTableHoaDon();
                }
            } else {
                idKhachHang = khachHangService.findBySDT(txtSDT.getText()).getId();
                KetQua kq = hoaDonService.updateKhachHang(idKhachHang, txtTenKhachHang.getText(), txtSDT.getText(), idHD);
                if (kq.getIdKetQua() == 1) {
                    showSuccess("Lưu thành công");
                    fillTableHoaDon();
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
        // TODO add your handling code here:
        try {
            if (listGioHang.isEmpty()) {
                showWarring("Giỏ hàng trống");
            } else {
                if (daThanhToan >= tongTienSauGiamGia) {
                    Integer idVoucher = listVoucher.get(cbVoucher.getSelectedIndex()).getId();
                    KetQua kq = hoaDonService.updateHoanThanh(idHD, tongTien, tongTienSauGiamGia, idVoucher);
                    if (kq.getIdKetQua() == 1) {
                        if (tienMat != 0) {
                            boolean kq1 = chiTietHinhThucThanhToanRepository.createTienMat(idHD, tienMat);
                        }
                        if (tienChuyenKhoan != 0) {
                            boolean kq1 = chiTietHinhThucThanhToanRepository.createChuyenKhoan(idHD, tienChuyenKhoan);
                        }
                        showSuccess("Hoàn thành đơn hàng");
                        inHD();
                        fillTableHoaDon();
                        reset();
                        resetTableGioHang();
                    }
                } else {
                    showWarring("Chưa thanh toán!");
                }
            }
        } catch (Exception e) {
            showWarring("Lỗi");
        }
    }//GEN-LAST:event_btnHoanThanhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThemHoaDon;
    private javax.swing.JButton btnXoaGioHang;
    private javax.swing.JButton btnXoaHD;
    private javax.swing.JComboBox<String> cbVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbCanThanhToan;
    private javax.swing.JLabel lbGiamGia;
    private javax.swing.JLabel lbHDCT;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JPanel panelHDCT;
    private javax.swing.JPanel panelHoaDon;
    private javax.swing.JPanel panelKhachhang;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtChuyenKhoan;
    private javax.swing.JTextField txtDaThanhToan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienMat;
    private javax.swing.JTextField txtTraLai;
    // End of variables declaration//GEN-END:variables
}
