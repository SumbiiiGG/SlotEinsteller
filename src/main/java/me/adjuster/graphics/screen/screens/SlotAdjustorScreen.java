package me.adjuster.graphics.screen.screens;

import me.adjuster.SlotAdjuster;
import me.adjuster.calculator.SlotCalculator;
import me.adjuster.graphics.screen.Screen;
import me.adjuster.slot.MathUtil;
import me.adjuster.slot.Slot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlotAdjustorScreen extends Screen {
    int dropper;
   public SlotAdjustorScreen(int dropper){
       this.dropper=dropper;
       JLabel question1 = new JLabel("Wie Hoch sind die Gewinne deiner Lampen?");
       JLabel question2 = new JLabel("Wie hoch sollte der durchschnittliche Gewinn sein?");
       JLabel filler = new JLabel("Antwort ");
       JButton calculate = new JButton("Berechnen");
       JScrollBar divider = new JScrollBar();
       JButton newslot = new JButton("Neue Slot");
       JLabel chances = new JLabel("Exacte Chancen dass diese Menge Lampen an gehen");
       JLabel settings = new JLabel("Deine Dropper muessen so eingestellt werden");
       JLabel average = new JLabel("");
       JTextField[] answers1 = new JTextField[dropper];
       JTextField answer2 = new JTextField();
       JLabel[] chanceResults = new JLabel[dropper];
       JLabel[] dropperContentResults = new JLabel[dropper];

       //Add action Listeners
       newslot.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               SlotAdjuster.currentScreen = new StartScreen();
           }
       });
       calculate.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   List<Float> prices = new ArrayList<>();
                   for(JTextField field : answers1){
                       prices.add(Float.parseFloat(field.getText()));
                   }
                   float target = Float.parseFloat(answer2.getText());
                   Slot slot = new Slot(prices);
                   SlotCalculator slotCalculator = new SlotCalculator();
                   Float[] bestOption = slotCalculator.getClosedOption(slot,target);
                   List<Float> exactChances = slot.getChancesExact(Arrays.asList(bestOption), slot.getAllCombinations());
                   for(int i=0;i<chanceResults.length;i++){
                      chanceResults[i].setText((exactChances.get(i+1)*100)+"%");
                   }
                   for(int i=0;i<dropperContentResults.length;i++){
                       dropperContentResults[i].setText(MathUtil.toDropper(bestOption[i]));
                   }
                   average.setText("Der Durchschnittliche Gewinn betraegt "+slot.getExpectedProfit(Arrays.asList(bestOption)) +"$ | Dies ist "+Math.abs(slot.getExpectedProfit(Arrays.asList(bestOption))-target)+"$ von deinem genauem Ziel entfernt");
               }catch (Exception exception){}
           }
       });

       //Set Positions and add to screen
       for(int i=1;i<=dropper;i++){
           JLabel lampNumber = new JLabel("Lampe  "+i);
           JLabel lampNumber1 = new JLabel("Lampe  "+i);
           JLabel dropperNumber = new JLabel("Dropper "+i);
           JLabel dropperSetting = new JLabel("");
           JLabel chanceResult = new JLabel("");
           JTextField lampAnswer = new JTextField();
           lampNumber.setBounds(5,i*20+10,100,20);
           lampNumber1.setBounds(320,i*20+10,100,20);
           chanceResult.setBounds(390,i*20+10,100,20);
           dropperNumber.setBounds(320,i*20+10+dropper*20+40,100,20);
           dropperSetting.setBounds(390,i*20+10+dropper*20+40,400,20);
           lampAnswer.setBounds(70,i*20+10,100,20);
           answers1[i-1] = lampAnswer;
           dropperContentResults[i-1] = dropperSetting;
           chanceResults[i-1] = chanceResult;


           addElement(lampAnswer);
           addElement(dropperNumber);
           addElement(lampNumber1);
           addElement(lampNumber);
           addElement(dropperSetting);
           addElement(chanceResult);
       }
       average.setBounds(320,dropper*20+10+dropper*20+70,1000,20);
       divider.setBounds(300,0,20,getWindowHeight());
       chances.setBounds(320,5,450,20);
       question1.setBounds(5,5,300,20);
       question2.setBounds(5,dropper*20+40,300,20);
       filler.setBounds(5,dropper*20+60,100,20);
       answer2.setBounds(70,dropper*20+60,100,20);
       calculate.setBounds(5,dropper*20+80,170,20);
       newslot.setBounds(5,dropper*20+110,170,20);
       settings.setBounds(320,dropper*20+40,300,20);

       addElement(divider);
       addElement(chances);
       addElement(question1);
       addElement(question2);
       addElement(answer2);
       addElement(filler);
       addElement(calculate);
       addElement(newslot);
       addElement(settings);
       addElement(average);

       SlotAdjuster.window.revalidate();
       SlotAdjuster.window.repaint();
   }

}
