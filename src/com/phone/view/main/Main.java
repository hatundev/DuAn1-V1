package com.phone.view.main;

import com.core.model.response.TaiKhoanResponse;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.phone.custom.component.Header;
import com.phone.custom.component.Menu;
import com.phone.custom.component.Notification;
import com.phone.custom.event.EventMenuSelected;
import com.phone.custom.event.EventShowPopupMenu;
import com.phone.custom.swing.MenuItem;
import com.phone.custom.swing.PopupMenu;
import com.phone.view.form.MainForm;
import com.phone.view.form.khachhang.FormKhachHang;
import java.awt.Component;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import com.phone.custom.swingacrylic.SwingAcrylic;
import com.phone.view.form.banhang.FormBanHang;
import com.phone.view.form.hoadon.FormHoaDon;
import com.phone.view.form.sanpham.ChiTietSanPham;
import com.phone.view.form.sanpham.FormSanPham;
import com.phone.view.form.thuoctinh.FormThuocTinh;
import com.phone.view.form.tongquan.DoiMatKhau;
import com.phone.view.form.tongquan.FormHome;
import com.phone.view.form.tongquan.FormQuanLyNhanVien;
import com.phone.view.form.voucher.FormVoucher;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private Notification notiSuccess;
    private Notification notiWarring;
    private TaiKhoanResponse account;

    public Main() {
        initComponents();
        init();
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatMacLightLaf());
                SwingUtilities.updateComponentTreeUI(this);
//                FlatLightLaf.install();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void showByRole() {
        if (account.getTenChucVu().equals("Quản lý")) {
            main.showForm(new FormHome());
        } else {
            FormBanHang formBanHang = new FormBanHang();
            formBanHang.setAccount(account);
            main.showForm(formBanHang);
        }
    }

    public void close() {
        this.dispose();
    }

    public MainForm getPanel() {
        return main;
    }

    public void setAccount(TaiKhoanResponse tk) {
        account = tk;
        lbUserName.setText(account.getTenNhanVien());
        lbRole.setText(account.getTenChucVu());
    }

    public TaiKhoanResponse pushAccount() {
        return account;
    }

    private void init() {
        layout = new MigLayout("fill", "10[]10[100%, fill]10", "10[fill, top]10");
        bg.setLayout(layout);
        menu = new Menu();
        //header = new Header();
        main = new MainForm();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                if (menuIndex == 0) {
                    if (lbRole.getText().equals("Quản lý")) {
                        lbView.setText("Thống kê");
                        main.showForm(new FormHome());
                        if (subMenuIndex == 0) {
                            lbView.setText("Thống kê");
                            main.showForm(new FormHome());
                        } else if (subMenuIndex == 1) {
                            lbView.setText("Quản lý nhân viên");
                            FormQuanLyNhanVien formQuanLyNhanVien = new FormQuanLyNhanVien();
                            formQuanLyNhanVien.setAccount(account);
                            main.showForm(formQuanLyNhanVien);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Bạn không có quyền xem phần này");
                        FormBanHang formBanHang = new FormBanHang();
                        formBanHang.setAccount(account);
                        lbView.setText("Bán hàng");
                        main.showForm(formBanHang);
                    }
                } else if (menuIndex == 1) {
                    FormBanHang formBanHang = new FormBanHang();
                    formBanHang.setAccount(account);
                    lbView.setText("Bán hàng");
                    main.showForm(formBanHang);
                } else if (menuIndex == 2) {
                    lbView.setText("Quản lý hóa đơn");
                    main.showForm(new FormHoaDon());
                } else if (menuIndex == 3) {
                    lbView.setText("Quản lý sản phẩm");
                    FormSanPham formSanPham = new FormSanPham();
                    formSanPham.setAccount(account);
                    main.showForm(formSanPham);
                } else if (menuIndex == 4) {
                    lbView.setText("Quản lý thuộc tính");
                    main.showForm(new FormThuocTinh());
                } else if (menuIndex == 5) {
                    lbView.setText("Quản lý khách hàng");
                    main.showForm(new FormKhachHang());
                } else if (menuIndex == 6) {
                    if (lbRole.getText().equals("Quản lý")) {
                        lbView.setText("Quản lý phiếu giảm giá");
                        main.showForm(new FormVoucher());
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Bạn không có quyền xem phần này");
                        FormBanHang formBanHang = new FormBanHang();
                        formBanHang.setAccount(account);
                        lbView.setText("Bán hàng");
                        main.showForm(formBanHang);
                    }
                }
            }
        });

        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 62;
                int y = Main.this.getY() + com.getY() + 95;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 240!, spany 2");    // Span Y 2cell
        bg.add(panelHeader, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);

        if (lbRole.getText().equals("Quản lý")) {
            main.showForm(new FormHome());
        } else {
            FormBanHang formBanHang = new FormBanHang();
            formBanHang.setAccount(account);
            lbView.setText("Bán hàng");
            main.showForm(formBanHang);
        }
    }

    public void viewDetailProduct(int id) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham(id);
        chiTietSanPham.setAccount(account);
        main.showForm(chiTietSanPham);
    }

    public void showNotiWarring(String message) {
        notiWarring = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, message);
        notiWarring.showNotification();
    }

    public void showNotiSuccess(String message) {
        notiSuccess = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, message);
        notiSuccess.showNotification();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        lbUserName = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogOut = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        lbView = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm bán điện thoại - DuAn1_Nhom5");
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(221, 224, 227));

        panelHeader.setBackground(new java.awt.Color(147, 158, 168));

        lbUserName.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(51, 51, 51));
        lbUserName.setText("User Name");

        lbRole.setForeground(new java.awt.Color(51, 51, 51));
        lbRole.setText("Admin");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/exit.png"))); // NOI18N
        btnLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogOutMouseClicked(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/cross-circle.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        lbView.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbView.setText("Form .....");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/resources/icons/key.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbView)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1277, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUserName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRole, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLogOut)
                .addGap(30, 30, 30)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(30, 30, 30))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbView))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHeaderLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogOut)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHeaderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(panelHeaderLayout.createSequentialGroup()
                                .addComponent(lbUserName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbRole))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 861, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogOutMouseClicked
        // TODO add your handling code here:
        this.dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_btnLogOutMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        DoiMatKhau form = new DoiMatKhau(this);
        form.setAccount(account);
        form.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingAcrylic.prepareSwing();
                Main frame = new Main();
                frame.setVisible(true);
                SwingAcrylic.processFrame(frame);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel btnLogOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbUserName;
    private javax.swing.JLabel lbView;
    private javax.swing.JPanel panelHeader;
    // End of variables declaration//GEN-END:variables
}
