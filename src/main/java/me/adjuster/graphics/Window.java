package me.adjuster.graphics;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(){
        this.setLayout(null);
        this.setVisible(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(d.width/2-500,d.height/2-400,1000,800);
        this.setTitle("Slot-Einsteller");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
