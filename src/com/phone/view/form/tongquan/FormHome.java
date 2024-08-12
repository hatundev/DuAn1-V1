package com.phone.view.form.tongquan;

import com.core.model.response.TongQuanResponse;
import com.core.model.response.Top1Sale;
import com.core.repository.TongQuanRepository;
import com.util.Util;

public class FormHome extends javax.swing.JPanel {

    private TongQuanRepository tongQuanRepository = new TongQuanRepository();
    private Util util = new Util();

    public FormHome() {
        initComponents();
        init();
    }

    public void init() {
        fillDataToDay();
        fillDataToLastDay();
        fillDataToWeek();
        fillDataToLastWeek();
        fillDataToMonth();
        fillDataToLastMonth();
        fillTop1Sale();
    }

    private void fillDataToDay() {
    TongQuanResponse data = tongQuanRepository.getDay();
    if (data != null) {
        lbTongTienDay.setText(util.convertFloatToMoney(data.getTongTien()));
        lbDonHoanThanhDay.setText(String.valueOf(data.getSoDonHoanThanh()) + " Đơn");
        lbDonKhongHoanThanhDay.setText(String.valueOf(data.getSoDonChuaHoanThanh()) + " Đơn");
        lbSanPhamDay.setText(String.valueOf(data.getSoSanPhamDaBan()) + " Sản phẩm");
    } else {
        lbTongTienDay.setText(util.convertFloatToMoney(0));
        lbDonHoanThanhDay.setText("0 Đơn");
        lbDonKhongHoanThanhDay.setText("0 Đơn");
        lbSanPhamDay.setText("0 Sản phẩm");
    }
}

private void fillDataToLastDay() {
    TongQuanResponse data = tongQuanRepository.getLastDay();
    if (data != null) {
        lbTongTienLastDay.setText(util.convertFloatToMoney(data.getTongTien()));
        lbDonHoanThanhLastDay.setText(String.valueOf(data.getSoDonHoanThanh()) + " Đơn");
        lbDonKhongHoanThanhLastDay.setText(String.valueOf(data.getSoDonChuaHoanThanh()) + " Đơn");
        lbSanPhamLastDay.setText(String.valueOf(data.getSoSanPhamDaBan()) + " Sản phẩm");
    } else {
        lbTongTienLastDay.setText(util.convertFloatToMoney(0));
        lbDonHoanThanhLastDay.setText("0 Đơn");
        lbDonKhongHoanThanhLastDay.setText("0 Đơn");
        lbSanPhamLastDay.setText("0 Sản phẩm");
    }
}

private void fillDataToWeek() {
    TongQuanResponse data = tongQuanRepository.getWeek();
    if (data != null) {
        lbTongTienWeek.setText(util.convertFloatToMoney(data.getTongTien()));
        lbDonHoanThanhWeek.setText(String.valueOf(data.getSoDonHoanThanh()) + " Đơn");
    } else {
        lbTongTienWeek.setText(util.convertFloatToMoney(0));
        lbDonHoanThanhWeek.setText("0 Đơn");
    }
}

private void fillDataToLastWeek() {
    TongQuanResponse data = tongQuanRepository.getLastWeek();
    if (data != null) {
        lbTongTienLastWeek.setText(util.convertFloatToMoney(data.getTongTien()));
        lbDonHoanThanhLastWeek.setText(String.valueOf(data.getSoDonHoanThanh()) + " Đơn");
    } else {
        lbTongTienLastWeek.setText(util.convertFloatToMoney(0));
        lbDonHoanThanhLastWeek.setText("0 Đơn");
    }
}

private void fillDataToMonth() {
    TongQuanResponse data = tongQuanRepository.getMonth();
    if (data != null) {
        lbTongTienMonth.setText(util.convertFloatToMoney(data.getTongTien()));
        lbDonHoanThanhMonth.setText(String.valueOf(data.getSoDonHoanThanh()) + " Đơn");
    } else {
        lbTongTienMonth.setText(util.convertFloatToMoney(0));
        lbDonHoanThanhMonth.setText("0 Đơn");
    }
}

private void fillDataToLastMonth() {
    TongQuanResponse data = tongQuanRepository.getLastMonth();
    if (data != null) {
        lbTongTienLastMonth.setText(util.convertFloatToMoney(data.getTongTien()));
        lbDonHoanThanhLastMonth.setText(String.valueOf(data.getSoDonHoanThanh()) + " Đơn");
    } else {
        lbTongTienLastMonth.setText(util.convertFloatToMoney(0));
        lbDonHoanThanhLastMonth.setText("0 Đơn");
    }
}

private void fillTop1Sale() {
    Top1Sale data = tongQuanRepository.getTop1Sale();
    if (data != null) {
        lbMaNhanVien.setText(data.getMaNhanVien());
        lbHoTen.setText(data.getTenNhanVien());
        lbDoanhSoTop1.setText(util.convertFloatToMoney(data.getDoanhSo()));
        lbSoHoaDonTop1.setText(String.valueOf(data.getSoHoaDon()) + " Đơn");
        lbSoSanPhamTop1.setText(String.valueOf(data.getSoSanPhamBan()) + " Sản phẩm");
    } else {
        lbMaNhanVien.setText("");
        lbHoTen.setText("");
        lbDoanhSoTop1.setText(util.convertFloatToMoney(0));
        lbSoHoaDonTop1.setText("0 Đơn");
        lbSoSanPhamTop1.setText("0 Sản phẩm");
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbTongTienDay = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbDonKhongHoanThanhDay = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbSanPhamDay = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbDonHoanThanhDay = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lbTongTienWeek = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbDonHoanThanhWeek = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lbTongTienLastWeek = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lbDonHoanThanhLastWeek = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbMaNhanVien = new javax.swing.JLabel();
        lbHoTen = new javax.swing.JLabel();
        lbDoanhSoTop1 = new javax.swing.JLabel();
        lbSoHoaDonTop1 = new javax.swing.JLabel();
        lbSoSanPhamTop1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbTongTienLastDay = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbDonKhongHoanThanhLastDay = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbSanPhamLastDay = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbDonHoanThanhLastDay = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbDonHoanThanhMonth = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lbTongTienMonth = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lbDonHoanThanhLastMonth = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lbTongTienLastMonth = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();

        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(147, 158, 168));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Hôm nay");

        lbTongTienDay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbTongTienDay.setText("50.000.000 VNĐ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Tổng tiền");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Đơn hàng hoàn thành");

        lbDonKhongHoanThanhDay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDonKhongHoanThanhDay.setText("5 Đơn");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Đơn hàng không hoàn thành");

        lbSanPhamDay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbSanPhamDay.setText("10 Sản phẩm");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Đã bán");

        lbDonHoanThanhDay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDonHoanThanhDay.setText("5 Đơn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(153, 153, 153)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongTienDay)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(lbDonHoanThanhDay, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(lbDonKhongHoanThanhDay, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSanPhamDay, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(7, 7, 7)
                                .addComponent(lbDonKhongHoanThanhDay))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbSanPhamDay))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDonHoanThanhDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(5, 5, 5)
                                .addComponent(lbTongTienDay)))))
                .addGap(39, 39, 39))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1470, 140);

        jPanel7.setBackground(new java.awt.Color(147, 158, 168));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel30.setText("Tuần này");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("Tổng tiền");

        lbTongTienWeek.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbTongTienWeek.setText("50.000.000 VNĐ");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText("Đơn hàng hoàn thành");

        lbDonHoanThanhWeek.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDonHoanThanhWeek.setText("5 Đơn");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setText("Tổng tiền");

        lbTongTienLastWeek.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbTongTienLastWeek.setText("50.000.000 VNĐ");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel42.setText("Đơn hàng hoàn thành");

        lbDonHoanThanhLastWeek.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDonHoanThanhLastWeek.setText("5 Đơn");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel44.setText("Tuần trước");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel44))
                .addGap(141, 141, 141)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongTienLastWeek)
                    .addComponent(lbTongTienWeek)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel31))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDonHoanThanhWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDonHoanThanhLastWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(128, 128, 128))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(52, 52, 52))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDonHoanThanhWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(5, 5, 5)
                                .addComponent(lbTongTienWeek)))
                        .addGap(36, 36, 36)))
                .addGap(1, 1, 1)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel44)
                            .addGap(16, 16, 16))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel42)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbDonHoanThanhLastWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(5, 5, 5)
                        .addComponent(lbTongTienLastWeek)))
                .addGap(92, 92, 92))
        );

        add(jPanel7);
        jPanel7.setBounds(0, 320, 890, 200);

        jPanel8.setBackground(new java.awt.Color(147, 158, 168));
        jPanel8.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel19.setText("Nhân viên xuất sắc");
        jPanel8.add(jLabel19);
        jLabel19.setBounds(140, 30, 297, 48);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Doanh số");
        jPanel8.add(jLabel20);
        jLabel20.setBounds(107, 264, 77, 25);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Số hóa đơn");
        jPanel8.add(jLabel21);
        jLabel21.setBounds(107, 332, 93, 25);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Số sản phẩm");
        jPanel8.add(jLabel22);
        jLabel22.setBounds(107, 400, 104, 25);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Mã nhân viên");
        jPanel8.add(jLabel23);
        jLabel23.setBounds(73, 114, 106, 25);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("Họ tên");
        jPanel8.add(jLabel24);
        jLabel24.setBounds(370, 110, 54, 25);

        lbMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbMaNhanVien.setText("PH56356");
        jPanel8.add(lbMaNhanVien);
        lbMaNhanVien.setBounds(80, 150, 95, 32);

        lbHoTen.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbHoTen.setText("Nguyễn Thanh Tùng");
        jPanel8.add(lbHoTen);
        lbHoTen.setBounds(280, 150, 221, 32);

        lbDoanhSoTop1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbDoanhSoTop1.setText("10000000000000");
        jPanel8.add(lbDoanhSoTop1);
        lbDoanhSoTop1.setBounds(300, 260, 280, 25);

        lbSoHoaDonTop1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSoHoaDonTop1.setText("10 hóa đơn");
        jPanel8.add(lbSoHoaDonTop1);
        lbSoHoaDonTop1.setBounds(300, 330, 160, 25);

        lbSoSanPhamTop1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbSoSanPhamTop1.setText("10 sản phẩm");
        jPanel8.add(lbSoSanPhamTop1);
        lbSoSanPhamTop1.setBounds(300, 400, 240, 25);

        add(jPanel8);
        jPanel8.setBounds(910, 320, 560, 510);

        jPanel2.setBackground(new java.awt.Color(147, 158, 168));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Hôm qua");

        lbTongTienLastDay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbTongTienLastDay.setText("50.000.000 VNĐ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Tổng tiền");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Đơn hàng hoàn thành");

        lbDonKhongHoanThanhLastDay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDonKhongHoanThanhLastDay.setText("5 Đơn");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Đơn hàng không hoàn thành");

        lbSanPhamLastDay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbSanPhamLastDay.setText("10 Sản phẩm");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Đã bán");

        lbDonHoanThanhLastDay.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDonHoanThanhLastDay.setText("5 Đơn");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(153, 153, 153)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongTienLastDay)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel12)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(lbDonHoanThanhLastDay, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(lbDonKhongHoanThanhLastDay, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(123, 123, 123)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSanPhamLastDay, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addGap(52, 52, 52))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(7, 7, 7)
                                .addComponent(lbDonKhongHoanThanhLastDay))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbSanPhamLastDay))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDonHoanThanhLastDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(5, 5, 5)
                                .addComponent(lbTongTienLastDay)))))
                .addGap(39, 39, 39))
        );

        add(jPanel2);
        jPanel2.setBounds(0, 160, 1472, 140);

        jPanel9.setBackground(new java.awt.Color(147, 158, 168));

        lbDonHoanThanhMonth.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDonHoanThanhMonth.setText("5 Đơn");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Tổng tiền");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel39.setText("Tháng này");

        lbTongTienMonth.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbTongTienMonth.setText("50.000.000 VNĐ");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setText("Đơn hàng hoàn thành");

        lbDonHoanThanhLastMonth.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbDonHoanThanhLastMonth.setText("5 Đơn");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setText("Đơn hàng hoàn thành");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel47.setText("Tổng tiền");

        lbTongTienLastMonth.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbTongTienLastMonth.setText("50.000.000 VNĐ");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel49.setText("Tháng trước");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel49))
                .addGap(141, 141, 141)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongTienLastMonth)
                    .addComponent(lbTongTienMonth)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel37))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDonHoanThanhMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDonHoanThanhLastMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(128, 128, 128))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDonHoanThanhMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel39)
                            .addGap(16, 16, 16))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel37)
                            .addGap(5, 5, 5)
                            .addComponent(lbTongTienMonth))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel49)
                            .addGap(16, 16, 16))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel46)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbDonHoanThanhLastMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(5, 5, 5)
                        .addComponent(lbTongTienLastMonth)))
                .addGap(62, 62, 62))
        );

        add(jPanel9);
        jPanel9.setBounds(0, 550, 890, 280);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbDoanhSoTop1;
    private javax.swing.JLabel lbDonHoanThanhDay;
    private javax.swing.JLabel lbDonHoanThanhLastDay;
    private javax.swing.JLabel lbDonHoanThanhLastMonth;
    private javax.swing.JLabel lbDonHoanThanhLastWeek;
    private javax.swing.JLabel lbDonHoanThanhMonth;
    private javax.swing.JLabel lbDonHoanThanhWeek;
    private javax.swing.JLabel lbDonKhongHoanThanhDay;
    private javax.swing.JLabel lbDonKhongHoanThanhLastDay;
    private javax.swing.JLabel lbHoTen;
    private javax.swing.JLabel lbMaNhanVien;
    private javax.swing.JLabel lbSanPhamDay;
    private javax.swing.JLabel lbSanPhamLastDay;
    private javax.swing.JLabel lbSoHoaDonTop1;
    private javax.swing.JLabel lbSoSanPhamTop1;
    private javax.swing.JLabel lbTongTienDay;
    private javax.swing.JLabel lbTongTienLastDay;
    private javax.swing.JLabel lbTongTienLastMonth;
    private javax.swing.JLabel lbTongTienLastWeek;
    private javax.swing.JLabel lbTongTienMonth;
    private javax.swing.JLabel lbTongTienWeek;
    // End of variables declaration//GEN-END:variables
}
