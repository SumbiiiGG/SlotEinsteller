package me.adjuster.graphics.screen;

import me.adjuster.SlotAdjuster;

import java.awt.*;

public class Screen {
    public void clearWindow() {
        SlotAdjuster.window.getContentPane().removeAll();
        SlotAdjuster.window.revalidate();
        SlotAdjuster.window.repaint();
    }

    public int getWindowWidth() {
        return SlotAdjuster.window.getWidth();
    }

    public int getWindowHeight() {
        return SlotAdjuster.window.getHeight();
    }

    public void addElement(Component component) {
        SlotAdjuster.window.getContentPane().add(component);
    }

    public void paint(Graphics g) {

    }

    public void paintComponentes(Graphics g) {

    }

    public void setCurrentScreen(Screen screen) {
        SlotAdjuster.currentScreen = screen;
    }
}
