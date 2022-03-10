package me.adjuster.graphics.screen.screens;

import me.adjuster.SlotAdjuster;
import me.adjuster.graphics.screen.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ChooseAmountLampsScreen extends Screen {
    public String leadsTo;

    public ChooseAmountLampsScreen(String leadsTo) {
        this.leadsTo = leadsTo;
    }

    @Override
    public void paint(Graphics g) {
        SlotAdjuster.window.getContentPane().removeAll();
        super.paint(g);
        try {
            SlotAdjuster.window.revalidate();
            URL backgroundUrl = getClass().getClassLoader().getResource("slottech.png");
            Image background = ImageIO.read(backgroundUrl);
            g.drawImage(background, 0, 0, SlotAdjuster.window.getWidth(), SlotAdjuster.window.getHeight(), null);
        } catch (Exception e) {

        }
        JTextField answer = new JTextField();
        answer.setBounds(getWindowWidth() / 2 - getWindowWidth() / 16, getWindowHeight() / 5, getWindowWidth() / 8, getWindowWidth() / 8);
        Font size = new Font("s", Font.BOLD, getWindowWidth() / 12);
        answer.setFont(size);
        answer.setBackground(Color.LIGHT_GRAY);
        answer.setForeground(Color.WHITE);
        addElement(answer);
        answer.repaint();

        Font buttonFont = new Font("buttom", Font.BOLD, getWindowHeight() / 16);
        JButton next = new JButton("Weiter");
        next.setBounds(2 * getWindowWidth() / 4 + getWindowWidth() / 16, (int) (getWindowHeight() / 1.5), (int) (getWindowWidth() / 8 * 3 * 0.8), (int) (getWindowWidth() / 12 * 0.8));
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearWindow();
                if (leadsTo.equals("slotchance")) {
                    try {
                        int lamps = Integer.parseInt(answer.getText());
                        setCurrentScreen(new SlotChanceCalculatorScreen(lamps));
                    } catch (Exception exception) {
                    }
                }
                if (leadsTo.equals("megaslotchance")) {
                    int lamps = Integer.parseInt(answer.getText());
                    setCurrentScreen(new MegaSlotChanceCalculatorScreen(lamps));
                }
                if (leadsTo.equals("slotchanceadjuster")) {
                    int lamps = Integer.parseInt(answer.getText());
                    setCurrentScreen(new SlotAdjustorScreen(lamps));
                }
                if (leadsTo.equals("")) {
                    int lamps = Integer.parseInt(answer.getText());
                }
            }
        });
        next.setFont(buttonFont);
        next.setForeground(Color.WHITE);
        next.setBackground(Color.GRAY);
        addElement(next);
        next.repaint();

        JButton back = new JButton("Zur\u00fcck");
        back.setBounds(getWindowWidth() / 2 - (int) (getWindowWidth() / 8 * 3 * 0.8) - getWindowWidth() / 16, (int) (getWindowHeight() / 1.5), (int) (getWindowWidth() / 8 * 3 * 0.8), (int) (getWindowWidth() / 12 * 0.8));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearWindow();
                setCurrentScreen(new StartScreen());
            }
        });
        back.setBackground(Color.GRAY);
        back.setForeground(Color.WHITE);
        addElement(back);
        back.setFont(buttonFont);
        back.repaint();
    }
}
