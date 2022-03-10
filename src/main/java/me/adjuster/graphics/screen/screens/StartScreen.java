package me.adjuster.graphics.screen.screens;

import me.adjuster.SlotAdjuster;
import me.adjuster.graphics.screen.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class StartScreen extends Screen {
    public StartScreen() {
        SlotAdjuster.window.repaint();
    }

    @Override
    public void paint(Graphics g) {

        Font size = new Font("s", Font.BOLD, getWindowHeight() / 16);
        SlotAdjuster.window.getContentPane().removeAll();
        SlotAdjuster.window.revalidate();
        try {
            SlotAdjuster.window.revalidate();
            URL backgroundUrl = getClass().getClassLoader().getResource("background.png");
            Image background = ImageIO.read(backgroundUrl);
            g.drawImage(background, 0, 0, SlotAdjuster.window.getWidth(), SlotAdjuster.window.getHeight(), null);
        } catch (Exception e) {

        }

        //EinstellButton
        JButton einstell = new JButton("Slot Einstellen");
        einstell.setBounds(getWindowWidth() / 2 - getWindowWidth() / 4, getWindowHeight() / 2 - getWindowHeight() / 5, getWindowWidth() / 2, getWindowHeight() / 9);
        einstell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearWindow();
                setCurrentScreen(new ChooseAmountLampsScreen("slotchanceadjuster"));
            }
        });
        einstell.setFont(size);
        einstell.setBackground(Color.GRAY);
        einstell.setForeground(Color.WHITE);
        einstell.setBorderPainted(true);
        addElement(einstell);
        einstell.repaint();

        //Slot-Berechnen-Button

        JButton slotBerechnenButton = new JButton("Slot Chancen Berechnen");
        slotBerechnenButton.setBackground(Color.GRAY);
        slotBerechnenButton.setForeground(Color.WHITE);
        slotBerechnenButton.setFont(size);
        slotBerechnenButton.setBorderPainted(true);
        slotBerechnenButton.setBounds(getWindowWidth() / 2 - getWindowWidth() / 4, getWindowHeight() / 2 - (getWindowHeight() / 5) + (getWindowHeight() / 7), getWindowWidth() / 2, getWindowHeight() / 9);
        slotBerechnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearWindow();
                setCurrentScreen(new ChooseAmountLampsScreen("slotchance"));
            }
        });
        addElement(slotBerechnenButton);
        slotBerechnenButton.repaint();

        //megaSlot
        JButton megaslotBerechnenButton = new JButton("Mega Slot Berechnen");
        megaslotBerechnenButton.setBounds(getWindowWidth() / 2 - getWindowWidth() / 4, getWindowHeight() / 2 - (getWindowHeight() / 5) + (getWindowHeight() / 7) * 2, getWindowWidth() / 2, getWindowHeight() / 9);
        megaslotBerechnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearWindow();
                setCurrentScreen(new ChooseAmountLampsScreen("megaslotchance"));
            }
        });
        megaslotBerechnenButton.setFont(size);
        megaslotBerechnenButton.setBorderPainted(true);
        megaslotBerechnenButton.setBackground(Color.GRAY);
        megaslotBerechnenButton.setForeground(Color.WHITE);
        addElement(megaslotBerechnenButton);
        megaslotBerechnenButton.repaint();

        SlotAdjuster.window.revalidate();
        SlotAdjuster.window.revalidate();
        super.paint(g);
    }
}
