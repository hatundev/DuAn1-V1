package com.phone.custom.swingacrylic;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.phone.custom.swingacrylic.jna.AccentPolicy;
import com.phone.custom.swingacrylic.jna.AccentState;
import com.phone.custom.swingacrylic.jna.SAUser32;
import com.phone.custom.swingacrylic.jna.WindowCompositionAttribute;
import com.phone.custom.swingacrylic.jna.WindowCompositionAttributeData;
import java.awt.*;
import javax.swing.*;

public class SwingAcrylic {

    public static final int MIN_BUILD = 17134;
    public static boolean SUPPORTED = Utility.isSupported();

    public static void prepareSwing() {
        if (!SUPPORTED) {
            return;
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public static void processFrame(JFrame frame) {
        if (!SUPPORTED) {
            return;
        }
        addTransparencyToBackground(frame);
        enableAcrylic(frame, 0, 0x990500);
    }

    public static void processFrame(JFrame frame, int opacity, int background) {
        addTransparencyToBackground(frame);
        enableAcrylic(frame, opacity, background);
    }

    public static void enableAcrylic(JFrame frame, int opacity, int background) {
        WinDef.HWND hwnd = new WinDef.HWND(Native.getWindowPointer(frame));
        AccentPolicy policy = new AccentPolicy();
        policy.AccentState = AccentState.ACCENT_ENABLE_BLURBEHIND;
        policy.GradientColor = (opacity << 24) | (background & 0xFFFFFF);
        policy.write();
        WindowCompositionAttributeData data = new WindowCompositionAttributeData();
        data.Attribute = WindowCompositionAttribute.WCA_ACCENT_POLICY;
        data.Data = policy.getPointer();
        data.SizeOfData = policy.size();
        data.write();
        boolean success = SAUser32.INSTANCE.SetWindowCompositionAttribute(hwnd, data.getPointer());
        if (!success) {
            print("Failed to set acrylic: native error %s", Native.getLastError());;
        }
    }

    public static void addTransparencyToBackground(Component component) {
        component.setBackground(new Color(255, 255, 255, 1));
    }

    protected static void print(String s, Object... args) {
        System.out.println("[SwingAcrylic] " + String.format(s, args));
    }

    public static String getVersion() {
        return "1.0";
    }
}
