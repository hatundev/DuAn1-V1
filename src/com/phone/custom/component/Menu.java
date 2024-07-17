package com.phone.custom.component;

import com.phone.custom.event.EventMenu;
import com.phone.custom.event.EventMenuSelected;
import com.phone.custom.event.EventShowPopupMenu;
import com.phone.custom.model.ModelMenu;
import com.phone.custom.swing.MenuAnimation;
import com.phone.custom.swing.MenuItem;
import com.phone.custom.swing.PanelTransparent;
import com.phone.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;


public class Menu extends PanelTransparent {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
        setTransparent(0.5f);
    }

    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/phone/resources/icons/1.png")), "Tổng quan", "Thống kê", "Quản lý nhân viên"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/phone/resources/icons/2.png")), "Bán hàng", "Lên đơn hàng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/phone/resources/icons/3.png")), "Hóa đơn", "Danh sách hóa đơn", "Hóa đơn chi tiết"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/phone/resources/icons/4.png")), "Sản phẩm", "Quản lý sản phẩm", "Chi tiết sản phẩm", "Quản lý thuộc tính"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/phone/resources/icons/5.png")), "Thuộc tính", "Danh sách thuộc tính", "Chi tiết thuộc tính"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/phone/resources/icons/6.png")), "Khách hàng", "Danh sách khách hàng", "Chi tiết khách hàng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/phone/resources/icons/7.png")), "Khuyến mại", "Danh sách khuyến mại", "Chi tiết khuyến mại"));
    }

//    private void addMenu(ModelMenu menu) {
//        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
//    }
    private void addMenu(ModelMenu menu) {
        MenuItem menuItem = new MenuItem(menu, getEventMenu(), event, panel.getComponentCount());
        menuItem.setFont(new Font("Arial", Font.PLAIN, 40));
        panel.add(menuItem, "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile1 = new com.phone.custom.component.Profile();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setViewportBorder(null);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(1,0,236), getWidth(), 0, new Color(251,54,244));
                //GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));

        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private com.phone.custom.component.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
