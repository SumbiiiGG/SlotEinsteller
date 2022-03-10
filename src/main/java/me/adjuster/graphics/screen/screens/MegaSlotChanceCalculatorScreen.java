package me.adjuster.graphics.screen.screens;

import me.adjuster.SlotAdjuster;
import me.adjuster.graphics.screen.Screen;
import me.adjuster.slot.MegaSlot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MegaSlotChanceCalculatorScreen extends Screen {
    int lamps;
    JTextField[] unstackables;
    JTextField[] stackables;
    JTextField[] prices;

    public MegaSlotChanceCalculatorScreen(int lamps) {
        this.lamps = lamps;
        unstackables = new JTextField[lamps];
        stackables = new JTextField[lamps];
        prices = new JTextField[lamps];
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Font buttonFont = new Font("button", Font.BOLD, getWindowHeight() / 28);
        SlotAdjuster.window.getContentPane().removeAll();
        try {
            SlotAdjuster.window.revalidate();
            URL backgroundUrl = getClass().getClassLoader().getResource("slotberechnenbackground.png");
            Image background = ImageIO.read(backgroundUrl);
            g.drawImage(background, 0, 10, SlotAdjuster.window.getWidth(), SlotAdjuster.window.getHeight(), null);
        } catch (Exception e) {
        }
        int startOffset = getWindowHeight() / 7;
        int dist = (getWindowHeight() - startOffset * 2) / lamps;
        int fontsize = (getWindowHeight() - startOffset * 5) / lamps;
        if (lamps < 8) {
            fontsize = (getWindowHeight() - startOffset * 5) / 8;
        }
        Font size = new Font("s", Font.BOLD, fontsize);
        for (int i = 0; i < lamps; i++) {
            //initialize vars
            if (unstackables[i] == null) {
                unstackables[i] = new JTextField("Us:");
            }
            if (stackables[i] == null) {
                stackables[i] = new JTextField("S:");
            }
            if (prices[i] == null) {
                prices[i] = new JTextField("P:");
            }
            //set positions
            JTextField dropperNumber = new JTextField("Dropper " + (i + 1));

            dropperNumber.setBackground(Color.LIGHT_GRAY);
            dropperNumber.setForeground(Color.WHITE);
            dropperNumber.setBounds(10, startOffset + i * dist, getWindowWidth() / 7, dist);
            dropperNumber.setFont(size);
            addElement(dropperNumber);
            dropperNumber.repaint();

            unstackables[i].setBackground(Color.LIGHT_GRAY);
            unstackables[i].setForeground(Color.WHITE);
            unstackables[i].setBounds(getWindowWidth() / 7 + 20, startOffset + i * dist, getWindowWidth() / 12, dist);
            unstackables[i].setFont(size);
            addElement(unstackables[i]);
            unstackables[i].repaint();

            stackables[i].setBackground(Color.LIGHT_GRAY);
            stackables[i].setForeground(Color.WHITE);
            stackables[i].setBounds(getWindowWidth() / 7 + 30 + getWindowWidth() / 12, startOffset + i * dist, getWindowWidth() / 12, dist);
            stackables[i].setFont(size);
            addElement(stackables[i]);
            stackables[i].repaint();

            prices[i].setBackground(Color.LIGHT_GRAY);
            prices[i].setForeground(Color.WHITE);
            prices[i].setBounds(getWindowWidth() / 7 + 40 + getWindowWidth() / 12 + getWindowWidth() / 12, startOffset + i * dist, getWindowWidth() / 12, dist);
            prices[i].setFont(size);
            addElement(prices[i]);
            prices[i].repaint();
        }

        //add buttons


        JButton back = new JButton("Zur\u00fcck");
        back.setBackground(Color.GRAY);
        back.setForeground(Color.WHITE);
        back.setFont(buttonFont);
        back.setBounds(10, startOffset + lamps * dist + 10, (int) (getWindowWidth() / 8 * 3 * 0.4), (int) ((getWindowWidth() / 12) * 0.4));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearWindow();
                setCurrentScreen(new StartScreen());
            }
        });
        addElement(back);
        back.repaint();

        JButton calculate = new JButton("Berechnen");
        calculate.setBounds(19 + (int) (getWindowWidth() / 8 * 3 * 0.4), startOffset + lamps * dist + 9, (int) (getWindowWidth() / 8 * 3 * 0.4), (int) ((getWindowWidth() / 12) * 0.4));
        calculate.setFont(buttonFont);
        calculate.setForeground(Color.WHITE);
        calculate.setBackground(Color.GRAY);
        //Calculate and display Results
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint(g);
                java.util.List<Float> chances = new ArrayList<>();
                java.util.List<Float> winnables = new ArrayList<>();
                for (int i = 0; i < lamps; i++) {
                    //add prices
                    try {
                        winnables.add(Float.parseFloat(prices[i].getText().split(":")[1].replace(" ", "")));
                    } catch (Exception exception) {
                        winnables.add(0f);
                    }
                    //add chances
                    try {
                        float stackable = Float.parseFloat(stackables[i].getText().split(":")[1].replace(" ", ""));
                        float unstackable = Float.parseFloat(unstackables[i].getText().split(":")[1].replace(" ", ""));
                        float chance = unstackable / (unstackable + stackable);
                        chances.add(chance);
                    } catch (Exception exception) {
                        chances.add(0f);
                    }
                }
                MegaSlot megaSlot = new MegaSlot(chances, winnables);
                List<Float> winChances = new ArrayList<>();
                for (int i = 0; i <= lamps; i++) {
                    winChances.add(megaSlot.getChance(i));
                }
                float expectedProfit = megaSlot.getTotalExpectedValue();

                for (int i = 1; i < winChances.size(); i++) {
                    JTextField chanceLabel = new JTextField("Genau " + (i) + " Lampen:" + winChances.get(i) * 100 + "%");
                    chanceLabel.setBackground(Color.LIGHT_GRAY);
                    chanceLabel.setForeground(Color.WHITE);
                    chanceLabel.setEditable(false);
                    chanceLabel.setBounds(getWindowWidth() / 2 + getWindowWidth() / 24, (int) (startOffset) + (i - 1) * dist, dist * 10, dist);
                    chanceLabel.setFont(size);
                    addElement(chanceLabel);
                    chanceLabel.repaint();
                }
                JTextField expected = new JTextField("Durchschnittlicher Gewinn pro Spiel:" + (int) expectedProfit + "$");
                expected.setBackground(Color.LIGHT_GRAY);
                expected.setForeground(Color.WHITE);
                expected.setEditable(false);
                expected.setBounds(getWindowWidth() / 2 + getWindowWidth() / 24, startOffset + (lamps) * dist, dist * 10, dist);
                int fontsize2 = (getWindowHeight() - startOffset * 5) / lamps;
                if (lamps < 8) {
                    fontsize2 = (getWindowHeight() - startOffset * 5) / 8;
                }
                Font size2 = new Font("s", Font.BOLD, fontsize2);
                expected.setFont(size2);
                addElement(expected);
                expected.repaint();

            }
        });
        addElement(calculate);
        calculate.repaint();
    }
}
