package me.adjuster.graphics.screen.screens;

import me.adjuster.SlotAdjuster;
import me.adjuster.graphics.screen.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends Screen {
    public StartScreen(){
        clearWindow();
        JLabel question = new JLabel("Wie viele Lampen hat deine Slot?");
        JTextField answer = new JTextField();
        JButton next = new JButton("Weiter");

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dropper = Integer.parseInt(answer.getText());
                    clearWindow();
                    SlotAdjuster.currentScreen = new SlotAdjustorScreen(dropper);
                }catch (Exception exception){}
            }
        });

        question.setBounds(getWindowWidth()/2 - 150,getWindowHeight()/2-150,200,20);
        answer.setBounds(getWindowWidth()/2 +40,getWindowHeight()/2-150,20,20);
        next.setBounds(getWindowWidth()/2 -150,getWindowHeight()/2-120,80,20);


        addElement(question);
        addElement(answer);
        addElement(next);


        SlotAdjuster.window.revalidate();
        SlotAdjuster.window.repaint();
    }
}
