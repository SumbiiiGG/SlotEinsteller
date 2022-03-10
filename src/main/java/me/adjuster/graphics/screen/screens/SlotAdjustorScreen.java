package me.adjuster.graphics.screen.screens;

import me.adjuster.SlotAdjuster;
import me.adjuster.calculator.SlotCalculator;
import me.adjuster.graphics.screen.Screen;
import me.adjuster.slot.MathUtil;
import me.adjuster.slot.Slot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlotAdjustorScreen extends Screen {
    int lamps;
    JTextField[] prices;
    JTextField[] results;
    JTextField wantedAverageTextField = new JTextField("");

    public SlotAdjustorScreen(int lamps) {
        this.lamps = lamps;
        this.prices = new JTextField[lamps];
        this.results = new JTextField[lamps];
    }

    @Override
    public void paint(Graphics g) {
        SlotAdjuster.window.getContentPane().removeAll();
        Font buttonFont = new Font("buttom", Font.BOLD, getWindowHeight() / 28);
        int startOffset = getWindowHeight() / 7;
        int fontsize = (getWindowHeight() - startOffset * 5) / lamps;
        Font size = new Font("Size", Font.BOLD, fontsize);
        if (lamps < 8) {
            fontsize = (getWindowHeight() - startOffset * 5) / 8;
        }
        try {
            SlotAdjuster.window.revalidate();
            URL backgroundUrl = getClass().getClassLoader().getResource("sloteinstellen.png");
            Image background = ImageIO.read(backgroundUrl);
            g.drawImage(background, 0, 0, SlotAdjuster.window.getWidth(), SlotAdjuster.window.getHeight(), null);
        } catch (Exception e) {
        }

        wantedAverageTextField.setForeground(Color.WHITE);
        wantedAverageTextField.setBackground(Color.LIGHT_GRAY);
        if (getWindowHeight() > 1000) {
            wantedAverageTextField.setBounds(getWindowWidth() / 100, getWindowHeight() - (getWindowHeight() / 4) + 20, getWindowWidth() / 3, getWindowHeight() / 10);
        } else {
            wantedAverageTextField.setBounds(getWindowWidth() / 100, getWindowHeight() - (3 * getWindowHeight() / 11) + 5, getWindowWidth() / 3, getWindowHeight() / 10);
        }
        wantedAverageTextField.setFont(new Font("averadge", Font.BOLD, getWindowHeight() / 22));
        addElement(wantedAverageTextField);
        wantedAverageTextField.repaint();
        addButtons(buttonFont,g);
        addPriceFields(size);
        super.paint(g);
    }

    public void calculateResults(Font font, float target) {

        SlotCalculator slotCalculator = new SlotCalculator();
        List<Float> winnable = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            winnable.add(Float.parseFloat(prices[i].getText().split(":")[1].replace(" ", "")));
        }
        System.out.println("W:" + winnable);
        Float[] bestOption = slotCalculator.getClosedOption(new Slot(winnable), target);

        int startOffset = getWindowHeight() / 7 + 10;
        int dist = (getWindowHeight() - startOffset * 2) / (lamps + 4);
        for (int i = 0; i < lamps; i++) {

            results[i] = new JTextField("Dropper " + (i + 1) + ":  " + MathUtil.toDropper(bestOption[i]));
            results[i].setText("Dropper " + (i + 1) + ":  " + MathUtil.toDropper(bestOption[i]));

            results[i].setBounds(getWindowWidth() / 2, startOffset + i * dist, getWindowWidth() / 3, dist);

            addElement(results[i]);
            results[i].setBackground(Color.LIGHT_GRAY);
            results[i].setForeground(Color.WHITE);
            results[i].setFont(font);
            results[i].setEditable(false);
            results[i].repaint();
        }
        Slot slot = new Slot(winnable);
        JTextField averadge = new JTextField("" + (int) slot.getExpectedProfit(Arrays.asList(bestOption)) + "$");
        averadge.setEditable(false);
        if (getWindowHeight() > 1000) {
            averadge.setBounds(getWindowWidth() / 2, getWindowHeight() - (getWindowHeight() / 4) + 20, getWindowWidth() / 3, getWindowHeight() / 10);
        } else {
            averadge.setBounds(getWindowWidth() / 2, getWindowHeight() - (3 * getWindowHeight() / 11) + 5, getWindowWidth() / 3, getWindowHeight() / 10);
        }
        averadge.setFont(new Font("s", Font.BOLD, getWindowHeight() / 22));
        averadge.setForeground(Color.WHITE);
        averadge.setBackground(Color.LIGHT_GRAY);
        addElement(averadge);
        averadge.repaint();

    }

    public void addPriceFields(Font font) {
        int startOffset = getWindowHeight() / 7 + 10;
        int dist = (getWindowHeight() - startOffset * 2) / (lamps + 4);
        for (int i = 0; i < lamps; i++) {
            if (prices[i] == null) {
                prices[i] = new JTextField("Lampe " + (i + 1) + ":");
            }
            prices[i].setBounds(getWindowWidth() / 100, startOffset + i * dist, getWindowWidth() / 3, dist);
            addElement(prices[i]);
            prices[i].setBackground(Color.LIGHT_GRAY);
            prices[i].setForeground(Color.WHITE);
            prices[i].setFont(font);
            prices[i].repaint();
        }
    }

    public void addButtons(Font buttonFont,Graphics g) {
        JButton back = new JButton("Zur\u00fcck");
        back.setBackground(Color.GRAY);
        back.setForeground(Color.WHITE);
        back.setFont(buttonFont);
        back.setBounds(getWindowWidth() / 100, getWindowHeight() - (getWindowHeight() / 8), (int) (getWindowWidth() / 8 * 3 * 0.4), (int) ((getWindowWidth() / 12) * 0.4));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearWindow();
                setCurrentScreen(new StartScreen());
            }
        });
        addElement(back);
        back.repaint();
        JButton calculateButton = new JButton("Berechnen");
        calculateButton.setBackground(Color.GRAY);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(buttonFont);
        calculateButton.setBounds(getWindowWidth() / 100 + getWindowWidth() / 3 - (int) (getWindowWidth() / 8 * 3 * 0.4), getWindowHeight() - (getWindowHeight() / 8), (int) (getWindowWidth() / 8 * 3 * 0.4), (int) ((getWindowWidth() / 12) * 0.4));
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint(g);
                calculateResults(buttonFont, Float.parseFloat(wantedAverageTextField.getText()));
            }
        });
        addElement(calculateButton);
        calculateButton.repaint();
    }
}
