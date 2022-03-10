package me.adjuster.graphics;

import me.adjuster.SlotAdjuster;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Window extends JFrame {
    public Window() {
        this.setLayout(null);
        this.setVisible(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(d.width / 2 - 513, d.height / 2 - 289, 1026, 578);
        this.setTitle("Slot-Einsteller");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            URL imgUrl = getClass().getClassLoader().getResource("Redstone.png");
            Image icon = ImageIO.read(imgUrl);
            this.setIconImage(icon);
        }catch (Exception e){}
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        SlotAdjuster.currentScreen.paint(g);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        SlotAdjuster.currentScreen.paintComponentes(g);
    }
}
